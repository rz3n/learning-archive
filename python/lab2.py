#!/usr/bin/python3

"""
Path: lab2.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-16
Description: Reads passwd file and offers a menu to select the user
  to get some information about

This script will:
  1. open /etc/passwd
  2. extract from this file: username, user id, group id and user shell
  3. create a function that opens the file and creates a list,
      incorporate error handling for your file handling
  4. create a while loop that stays open util the user types exit
  5. while the while loop is running, the user will see on the screen all
      the available users on the system. The user can select a specific user 
      and will be prompted which value they want to see.
  User Name, User ID, Group ID, User Shell
"""

passwd_file = "/etc/passwd"
l = [] # [username, userid, groupid, usershell]


# loads the file to a list (l)
def load_file(file):
  with open(file, 'r') as f:
    try:
      line = f.readline()
      while line:
        a = line.strip().split(":")
        l.append([a[0], a[2], a[3], a[6]])

        line = f.readline()

    except:
      print("Error: Unable to open file")


# main
if __name__ == '__main__':
  load_file(passwd_file)
  
  while True:
    print("\nAvailable users:")
    for i in range(len(l)):
      print(i, " -", l[i][0])

    user_choice = input("\nSelect a user (type 'exit' to exit): ")
  
    if user_choice == "exit":
      break
    else:
      user_choice = int(user_choice)
      value_choice = input("\nWhat value do you want to see? (uid, gid, shell): ")

      if value_choice == "uid":
        print(l[user_choice][0], " -", l[user_choice][1])
      elif value_choice == "gid":
        print(l[user_choice][0], " -", l[user_choice][2])
      elif value_choice == "shell":
        print(l[user_choice][0], " -", l[user_choice][3])
      else:
        print("Error: Invalid value")
