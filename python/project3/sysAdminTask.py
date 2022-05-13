#!/usr/bin/python3

"""
Path: project3/sysAdminTask.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-03-09
Description: Creates a file that contains:
 - current users logged in
 - hostname
 - date/time (HH:MM:SS DD/MM/YYYY)
 - number of processes running
 - top 10 process names/command, id and memory usage sorted from most memory % to least memory %

This script will:
  1. Get a list of current users logged in
  2. Get the hostname
  3. Get the current date/time in the proper format
  4. Get the number of processes running
  5. Get the top 10 process names/command, id and memory usage sorted from most memory % to least memory %
  6. Write the information to a file in /tmp/report.txt
"""

from datetime import date
from sys import platform
import subprocess

OUTPUT_FILE = "/tmp/report.txt"

# Function to get current logged in users, tty and login_time
# returns a list of tuples: [(user, tty, login_time), ...]
def get_logged_users():
  users = subprocess.check_output(["who", "-s"])
  users = users.decode("utf-8").split("\n")
  users = [user.split() for user in users]
  return users

# Function to get hostname
def get_hostname():
  return subprocess.check_output(["hostname"]).decode("utf-8").strip()

# Function to get current date/time
def get_date_time():
  return subprocess.check_output(["date", "+%H:%M:%S %d/%m/%Y"]).decode("utf-8").strip()

# Function to get number of processes running
def get_process_count():
  return subprocess.check_output(["ps", "-e", "--no-headers"]).decode("utf-8").count("\n")

# Function to get top 10 process names/command, id and memory usage sorted from most memory % to least memory %
def get_top_processes():
  top_processes = subprocess.check_output(["ps", "-e", "--no-headers", "-o", "pid,command,%mem", "--sort", "-%mem"]).decode("utf-8").split("\n")
  top_processes = [process.split() for process in top_processes]
  top_processes = top_processes[:10]
  return top_processes

# Function to write the information to a file
def write_to_file(file_path, logged_users, hostname, date_time, process_count, top_processes):
  try:
    with open(file_path, "w") as file:
      file.write("Hostname: {}\n".format(hostname))
      file.write("Date/Time: {}\n".format(date_time))
      file.write("\n")
      file.write("Current users logged in:\n")
      for user in logged_users:
        # check the array length to display the correct information
        if len(user) == 4:
          file.write("  - {} on {} since {} at {}\n".format(user[0], user[1], user[2], user[3]))
        elif len(user) == 5:
          file.write("  - {} on {} since {} at {} from {}\n".format(user[0], user[1], user[2], user[3], user[4]))
      file.write("\n")
      file.write("Number of processes running: {}\n".format(process_count))
      file.write("\n")
      file.write("Top 10 process names/command, id and memory usage sorted from most memory % to least memory %:\n")
      for process in top_processes:
        file.write("  - PID: {}    %MEM: {}        COMMAND: {}\n".format(process[0], process[2], process[1]))
  except Exception as e:
    print("Error writing to file {}: {}".format(file_path, e))


# main
if __name__ == '__main__':

  # Check if the OS is a Linux OS
  if platform == "linux" or platform == "linux2":
    # Get current logged in users
    users = get_logged_users()

    # Get hostname
    hostname = get_hostname()

    # Get current date/time
    date_time = get_date_time()
    
    # Get number of processes running
    process_count = get_process_count()

    # Get the top 10 process
    top_processes = get_top_processes()

    # Write the information to a file
    write_to_file(OUTPUT_FILE, users, hostname, date_time, process_count, top_processes)

  else:
    print("Sorry, this script is only for Linux OS")
