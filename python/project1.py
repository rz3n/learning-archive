#!/usr/bin/python3

"""
Path: project1.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-24
Description: Script to extract information from a linux system to a file.

This script will:
  1. Get the machine name
  2. Get a list of all the users and groups they are associated with
  3. From /proc/cpuinfo get:
    3.1. Processor
    3.2. Vendor_id
    3.3. Model
    3.4. Model name
    3.5. Cache
  4. All services on machine and their current status
  5. Write the information to an external file named system-info-<machine name>.txt
"""

import os
import grp
import subprocess


# Get the machine name
def getMachineName():
  return os.uname()[1]


# Get a list of all users and groups they are associated with
def getUsersAndGroups():
  users = {}

  with open('/etc/passwd') as f:
    for line in f:

      if ':' in line:
        user, password, uid, gid, gecos, home, shell = line.split(':')

        # get a list of all groups the user is associated with
        groupIDs = os.getgrouplist(user, int(gid))
        
        groups = []
        # using gid to get the group name
        for groupID in groupIDs:
          groups.append(grp.getgrgid(groupID)[0])

        users[user] = groups
  
  return users


# Get CPU information
def getCpuInfo():
  cpuInfo = {}
  RequiredCpuInfo = ['processor', 'vendor_id', 'model', 'model name', 'cache size']

  with open('/proc/cpuinfo') as f:
    for line in f:
      if ':' in line:
        key, value = line.split(':')
        if key.strip() in RequiredCpuInfo:
          cpuInfo[key.strip()] = value.strip()
  return cpuInfo


# Get services and their status
def getServices():
  serviceList = []

  runningServices = subprocess.check_output(['systemctl', 'list-units', '--type=service', '--plain', '--no-legend'])
  runningServices = runningServices.decode('utf-8')
  runningServices = runningServices.split('\n')
  
  # Split services and status to a list
  for service in runningServices:
    serviceList.append(service.split('\n'))

  return serviceList
  

# Write the information to a file for easy readability
def writeToFile(machineName, users, cpuInfo, serviceList):
  with open('./system_info_'+machineName+'.txt', 'w') as f:
    # write machine name
    f.write('Machine name:\n')
    f.write('\t'+machineName+'\n')
    
    # write user information and groups
    f.write('\nUsers and Groups:\n')
    for user, groups in users.items():
      f.write('\t{}:\n'.format(user))
      for group in groups:
        f.write('\t    - {}\n'.format(group))

    # write cpu information    
    f.write('\nCPU Info:\n')
    for key, value in cpuInfo.items():
      f.write('\t{}: {}\n'.format(key, value))

    # write service information
    f.write('\nServices:\n')
    f.write("\t{:<54}{:<30}\n".format('UNIT NAME', 'LOAD   ACTIVE SUB STATE DESCRIPTION'))
    for service in serviceList:
      f.write("\t"+service[0]+"\n")


# main
if __name__ == '__main__':
  machineName = getMachineName()
  users = getUsersAndGroups()
  cpuInfo = getCpuInfo()
  services = getServices()
  writeToFile(machineName, users, cpuInfo, services)

  print('Done!')
