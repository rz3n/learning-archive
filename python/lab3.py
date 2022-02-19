#!/usr/bin/python3

"""
Path: lab3.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-16
Description: Create a new user and group (if not exists) and add it to a group
Usage: ./lab3.py <user_name> <group_name>

This script will:
  1. read input from the user with username and group name
  2. create a function that checks if the group exists and if not, creates it
  3. create a function that checks if the user exists and if not, creates it
  4. add the user 
"""

import os, sys

groups_file = "/etc/group"
users_file = "/etc/passwd"

# open a specific file and returns the file object
def open_file(file):
  try:
    f = open(file, 'r')
    return f.read()
  except:
    print("Error: Unable to open file")
    return
  return f

# check if a group exists and if not, create it
def create_group(group_name):
  file = open_file(groups_file)
  if file.find(group_name) == -1:
    cmd = "groupadd " + group_name
    os.system(cmd)
    print("Group", group_name, "created")
  else:
    print("Group", group_name, "already exists")

# check if a user exists and if not, create it
def create_user(user_name):
  file = open_file(users_file)
  if file.find(user_name) == -1:
    cmd = "useradd " + user_name
    os.system(cmd)
    print("User", user_name, "created")
  else:
    print("User", user_name, "already exists")

# add a user to a group
def add_user_to_group(user_name, group_name):
  cmd = "usermod -a -G " + group_name + " " + user_name
  os.system(cmd)
  print("User", user_name, "added to group", group_name)

# main
if __name__ == '__main__':
  if len(sys.argv) == 3:
    group_name = sys.argv[1]
    user_name = sys.argv[2]
    create_group(group_name)
    create_user(user_name)
    add_user_to_group(user_name, group_name)
  else:
    print("Error: Invalid number of arguments")