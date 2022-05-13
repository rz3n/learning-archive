#!/usr/bin/env python3

"""
Path: projectscript.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-04-08
Description: Exercise to read a ini file and deal with it.

This script will:
  1. Check if the ini file exists and if it has errors
  2. Display a menu with the options
    2.1. System Accounts
      - Prints to screen all accounts and their group assotiations 
        sorted alphabetically by account name
    2.2. System logs
      - Prints to screen system logs from /var/log/syslog based ont he criteria
        specified in the ini file
    2.3. Generate Report
      - Overrites the previous report and generates a new one with:
        - Computer's name
        - Date and time (fomatted according to the ini file)
        - Results from option 2.1
        - Results from option 2.2
    2.4. Press Enter to exit
  3. Execute the selected option
  4. Exit the script
"""

# Imports
from datetime import datetime
import configparser
import grp
import os


# Constants
INI_FILE = "projectini.ini"


# Function to display the menu |-----------------------------------------------
def display_menu():
  print("\n")
  print("----------------------------------------")
  print("1. System Accounts")
  print("2. System logs")
  print("3. Generate Report")
  print("\n")
  print("Press Enter to exit")
  print("----------------------------------------")
  print("\n")


# Function to check if the ini file has errors |-------------------------------
def check_ini_file():
  # Check if the ini file exists
  if not os.path.isfile(INI_FILE):
    print("ERROR: The ini file does not exist.")
    exit()

  # Required variables
  required_variables = ({"BASIC": {"outFile"},
                        "FILTER_ACCOUNTS": {"sortAccountsReverse", "sortAccountsCriteria", "linesOfAccountsData"},
                        "FILTER_LOGS": {"sortLogsReverse", "logTimeFrom", "logTimeTo", "logCriteria"},
                        "NETWORKING": {"hostIP"}
                        })

  # Check if the ini file has all the required variables
  try:
    config = configparser.ConfigParser()
    config.read(INI_FILE)
    for section in required_variables:
      for variable in required_variables[section]:
        if not config.has_option(section, variable):
          print("ERROR: The variable '{}' is missing in the ini file.".format(variable))
  except:
    print("ERROR: The ini file has errors.")
    return False

  return True

# Get a list of all users and groups they are associated with |----------------
def getUsersAndGroups(sortReverse=False, sortCriteria="name"):
  users = {}

  with open('/etc/passwd') as f:
    # Sort and reverse case if needed
    f = sorted(f.readlines(), reverse=sortReverse)

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

# Get a list of all system logs and print them |-------------------------------
def getSystemLogs(sortReverse=False, logTimeFrom="", logTimeTo="", logCriteria=""):
  logs = []

  # Convert date and time to a format that can be used in the command
  '''
  I've got the idea of using the datetime module to convert the date and time from:
    https://stackoverflow.com/questions/20365854/comparing-two-date-strings-in-python
    Answer from Dec 4, 2013 at 2:51 from user: user2555451
  '''
  logTimeFrom = datetime.strptime(logTimeFrom, "%b %d %H:%M:%S")
  logTimeTo = datetime.strptime(logTimeTo, "%b %d %H:%M:%S")

  # Check if the logTimeFrom and logTimeTo are valid
  if logTimeFrom != "" and logTimeTo != "":
    if logTimeFrom > logTimeTo:
      print("ERROR: The 'logTimeFrom' variable is greater than the 'logTimeTo' variable.")
      return

  # Open the log file
  with open('/var/log/syslog') as f: 
    # Sort and reverse case if needed
    f = sorted(f.readlines(), reverse=sortReverse)

    for line in f:
      # Get the date in a proper format
      date = datetime.strptime(line[:15], "%b %d %H:%M:%S")
      
      # Check if the date is between the two dates specified
      if logTimeFrom != "" and logTimeTo != "":
        if date < logTimeFrom or date > logTimeTo:
          continue
      
      # Check if the line contains the criteria specified ignoring the case
      if logCriteria != "":
        if logCriteria.lower() not in line.lower():
          continue

      logs.append(line)

  # return the lines
  return logs


# Generate a report |----------------------------------------------------------    
def generateReport(outFile="", sortAccountsReverse=False, sortAccountsCriteria="name", sortLogsReverse=False, linesOfData="All", logTimeFrom="", logTimeTo="", logCriteria=""):
  if outFile == "":
    print("ERROR: The 'outFile' variable is empty.")
    return

  # Check if the outFile exists
  if os.path.isfile(outFile):
    print("ERROR: The outFile already exists and will be overwritten.")

  # Open the outFile
  with open(outFile, 'w') as f:
    # Write the computer's name
    f.write("Computer's name: " + os.uname()[1] + "\n")

    # Write the date and time
    f.write("Date and time: " + datetime.now().strftime("%b %d of %Y - %H:%M:%S") + "\n")

    # Write the results from option 2.1
    f.write("\n")
    f.write("System Accounts:\n")
    usersAndGroups = getUsersAndGroups(sortReverse=sortAccountsReverse, sortCriteria=sortAccountsCriteria)

    linesTotal = 0
    for user in usersAndGroups:
      if linesOfData == "All":
        f.write("  " + user + ": " + str(usersAndGroups[user]) + "\n")
      else:
        linesTotal += 1
        if linesTotal <= int(linesOfData):
          f.write("  " + user + ": " + str(usersAndGroups[user]) + "\n")
        else:
          break

    # Write the results from option 2.2
    f.write("\n")
    f.write("System Logs:\n")
    logs = getSystemLogs(sortReverse=sortLogsReverse, logTimeFrom=logTimeFrom, logTimeTo=logTimeTo, logCriteria=logCriteria)
    for log in logs:
      f.write(log)

  print("\nReport generated successfully.")


# main |-----------------------------------------------------------------------
if __name__ == '__main__':
  
  # Check if the ini file exists and if it has errors
  if not check_ini_file():
    exit()
  
  # Load the ini file
  config = configparser.ConfigParser()
  config.read(INI_FILE)

  # Execution loop
  while True:
    # Display the menu
    display_menu()

    # Get the user's option
    option = input("Enter your option: ")

    # Converting boolean values from strings to boolean
    if config["FILTER_ACCOUNTS"]["sortAccountsReverse"] == "True":
      sortAccountsReverse = True
    else:
      sortAccountsReverse = False
    
    if config["FILTER_LOGS"]["sortLogsReverse"] == "True":
      sortLogsReverse = True
    else:
      sortLogsReverse = False


    # Option 1: List users and groups
    if option == "1":
      usersAndGroups = getUsersAndGroups(sortReverse=sortAccountsReverse,
                        sortCriteria = config["FILTER_ACCOUNTS"]["sortAccountsCriteria"])
    
      # control vars for the loop
      linesTotal = 0
      linesOfData = config["FILTER_ACCOUNTS"]["linesOfAccountsData"]
      
      # print user information and groups
      print('\n-> Users and Groups:\n')
      for user, groups in usersAndGroups.items():
        if linesOfData == "All":
          print('\t' + user + ': ' + ', '.join(groups))
        else:
          linesTotal += 1
          if linesTotal <= int(linesOfData):
            print('\t' + user + ': ' + ', '.join(groups))
          else:
            break

    # Option 2: Get system logs
    elif option == "2":
      logs = getSystemLogs(sortReverse=sortLogsReverse,
                    logTimeFrom=config["FILTER_LOGS"]["logTimeFrom"],
                    logTimeTo=config["FILTER_LOGS"]["logTimeTo"],
                    logCriteria=config["FILTER_LOGS"]["logCriteria"])
      
      if logs == []:
        print("\nNo logs found.")
      else:
        print("\n-> Logs found:")
        for line in logs:
          print("  " + line)

    # Option 3: Generate a report with everything
    elif option == "3":
      generateReport(outFile=config["BASIC"]["outFile"],
                      sortAccountsReverse=sortAccountsReverse,
                      sortAccountsCriteria=config["FILTER_ACCOUNTS"]["sortAccountsCriteria"],
                      sortLogsReverse=sortLogsReverse,
                      linesOfData=config["FILTER_ACCOUNTS"]["linesOfAccountsData"],
                      logTimeFrom=config["FILTER_LOGS"]["logTimeFrom"],
                      logTimeTo=config["FILTER_LOGS"]["logTimeTo"],
                      logCriteria=config["FILTER_LOGS"]["logCriteria"])
    
    # Exit
    elif option == "":
      print("-> Exiting the script")
      exit()

