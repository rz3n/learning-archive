"""
Path: project2/main.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-02-18
Description: Project 2 - Main

This script will:
  1. Provide a menu to the user to select the desired operation.
  2. Call the appropriate function to perform the operation.
  3. The functions will be:
    3.1. Get a list of users and their last login date.
      3.1.1. Have the option to export the data to a CSV file.
    3.2. Get a list of users with administrative privileges.
      3.2.1. Have the option to export the data to a CSV file.
    3.3. Build a report of disk usage by drive.
      3.3.1. Have the option to export the data to a CSV file.
    3.4. A simple backup tool that receives a YAML file with a list of sources and destinations.
    3.5. A tool that receive a YAML file and check the integrity of sources and destinations.
"""

# Required modules.
from ctypes import sizeof
import argparse
import datetime
import filecmp
import os
import shutil
from unicodedata import name
import win32api, win32net, win32netcon
import yaml


# Parse the arguments
def parse_args():
  parser = argparse.ArgumentParser(description='SysAdmin Tools')
  parser.add_argument('-a', '--admins', action='store_true', help='Get a list of users with administrative privileges.')
  parser.add_argument('-b', '--backup', action='store_true', help='Backup a list of sources and destinations.')
  parser.add_argument('-c', '--check', action='store_true', help='Check the integrity of sources and destinations.')
  parser.add_argument('-d', '--diskusage', action='store_true', help='Build a report of disk usage by drive.')
  parser.add_argument('-l', '--lastlogon', action='store_true', help='Get a list of users and their last logon date.')
  parser.add_argument('-e', '--export', action='store_true', help='Export the data to a CSV file. Can be used with -a, -d and -l')
  parser.add_argument('-f', '--file', help='YAML file with the list of sources and destinations. Must be used with -b and -c')
  return parser.parse_args()


# Open a file and return the contents.
def open_yaml_file(filename):
  with open(filename, 'r') as yaml_file:
    try:
      data = yaml.load(yaml_file)
    except yaml.YAMLError as exc:
      print(exc)
  return data


# Get a list of users with administrative privileges.
def get_admins(export, group='Administrators'):
  accounts = []
  filename = group + '.csv'
  members =  win32net.NetLocalGroupGetMembers('127.0.0.1',group,3)
  for rows in members[0]:
    accounts.append(str(rows).split(":")[1][3:-2])
  
  if export==True:
    print('Exporting the list of users with administrative privileges to a CSV file...')
    with open(filename, 'w') as csvfile:
      csvfile.write('Users in group {}:\n'.format(group))
      for account in accounts:
        csvfile.write('{}\n'.format(account))
    print('Exported to {}'.format(filename))
  
  else:
    print('Administrators:')
    for account in accounts:
      print('  {}'.format(account))


# Build a list of users and their last login date.
def get_last_logon(export):
  names=[]; resumeHandle=0
  while True:
    data,_,resumeHandle=win32net.NetUserEnum(None,0,
          win32netcon.FILTER_NORMAL_ACCOUNT,resumeHandle)
    names.extend(e["name"] for e in data)
    if not resumeHandle: break
  del data,resumeHandle
  print('Users:')
  
  # Get the last logon date of each user.
  lastLogonList = []
  for name in names:
    try:
      # Get the last logon date of the user.
      lastLogonTime = win32net.NetUserGetInfo('127.0.0.1', 'opc', 3)['last_logon']
      
      # Convert the last logon date to a datetime object.
      lastLogonTime = datetime.datetime.fromtimestamp(lastLogonTime)

      lastLogonList.append({'name': name, 'last_logon': lastLogonTime})
    except:
      pass
  
  # Print the list of users and their last logon date.
  if export==True:
    print('Exporting the list of users and their last logon date to a CSV file...')
    with open('last_logon.csv', 'w') as csvfile:
      csvfile.write('Users and their last logon date:\n')
      for user in lastLogonList:
        csvfile.write('{},{}\n'.format(user['name'], user['last_logon']))
    print('Exported to last_logon.csv')
  else:
    for user in lastLogonList:
      print('  {} - {}'.format(user['name'], user['last_logon']))


# Build a report of disk usage by driver.
def get_disk_usage(export):
  # Get the list of drives.
  drives = win32api.GetLogicalDriveStrings().split('\000')
  
  # Get the info of each drive.
  drive_info = []
  for drive in drives:
    sectPerCluster, bytesPerSector, freeClusters, totalClusters = win32file.GetDiskFreeSpace(drive + '\\')

    # Calculate the total size of the drive in bytes.
    total_bytes = totalClusters * sectPerCluster * bytesPerSector
    free_bytes = freeClusters * sectPerCluster * bytesPerSector
    used_bytes = total_bytes - free_bytes

    # Calculate the total size of the drive in GB.
    total_gb = total_bytes / (1024 * 1024 * 1024)
    free_gb = free_bytes / (1024 * 1024 * 1024)
    used_gb = used_bytes / (1024 * 1024 * 1024)

    # Round the values to 2 decimal places.
    total_gb = round(total_gb, 2)
    free_gb = round(free_gb, 2)
    used_gb = round(used_gb, 2)

    drive_info.append({'drive': drive, 'total_gb': total_gb, 'free_gb': free_gb, 'used_gb': used_gb})

  # Cleaning the last element of the list.
  del drive_info[-1]

  # Print the list of drives and their usage.
  if export==True:
    print('Exporting the list of drives and their usage to a CSV file...')
    with open('disk_usage.csv', 'w') as csvfile:
      csvfile.write('Drives and their usage:\n')

      csvfile.write('Drive Letter,Total GB,Free GB,Used GB\n')
      for drive in drive_info:
        csvfile.write('{},{},{},{}\n'.format(drive['drive'], drive['total_gb'], drive['free_gb'], drive['used_gb']))

    print('Exported to disk_usage.csv')
  else:
    for drive in drive_info:
      print('  {}'.format(drive['drive']))
      print('    Total: {} GB'.format(drive['total_gb']))
      print('    Free: {} GB'.format(drive['free_gb']))
      print('    Used: {} GB'.format(drive['used_gb']))


# Backup a list of sources and destinations.
def backup(file):
  # Open the yaml file.
  data = open_yaml_file(file)
  
  # Get the list of backups.
  backups = data['backup']

  # Ask the user to confirm the backup.
  confirm = input('Are you sure you want to run the backup? Files in the destination folder may be overwritten. (y/n)\n')

  if confirm == 'y':
    print('Starting backup...')

    for backup in backups:
      # Get the informatin of each backup unit.
      name = backup['name']
      source = backup['source']
      destination = backup['destination']

      # Create the backup folder if it doesn't exist.
      if not os.path.exists(destination):
        os.makedirs(destination)
      
      # Copy and overwrite the files.
      print('Backing up {}...'.format(name))
      shutil.copytree(source, destination, dirs_exist_ok=True)

    print('Backup complete.')
  else:
    print('Backup cancelled.')


# Check the integrity of sources and destinations.
def check(file):
  # Open the yaml file.
  data = open_yaml_file(file)
  
  # Get the list of backups.
  backups = data['backup']

  # Check each backup.
  for backup in backups:
    # Get the informatin of each backup unit.
    name = backup['name']
    source = backup['source']
    destination = backup['destination']

    # Check the integrity of folders and files.
    print('Checking integrity of {}...'.format(name))
    if not os.path.exists(destination):
      print('  Destination folder does not exist.')
    else:
      if not os.path.exists(source):
        print('  Source folder does not exist.')
      else:
        # Get the list of files and folders in the source.
        source_files = os.listdir(source)

        # Get the list of files and folders in the destination.
        destination_files = os.listdir(destination)

        # Check if the source and destination are the same.
        if source_files == destination_files:
          print('  Source and destination are the same.')
        else:
          print('  Source and destination are different. Please, run the backup again.')


# main
if __name__ == '__main__':
  args = parse_args()

  if args.admins:
    get_admins(export=args.export)

  elif args.backup:
    if args.file is not None:
      backup(args.file)
    else:
      print('Error: Missing argument -f')

  elif args.check:
    if args.file is not None:
      check(args.file)
    else:
      print('Error: Missing argument -f')

  elif args.diskusage:
    get_disk_usage(args.export)

  elif args.lastlogon:
    get_last_logon(args.export)

  else:
    args.name()