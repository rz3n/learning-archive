#!/usr/bin/python3

"""
Path: lab5.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-19
Description: This script will retrieve info about
  users in a windows operating system.

This script will:
  1. Using the module win32api and win32get create 
    methods GetUserName() and NetUserGetInfo()
  2. exports current logged on user info like:
    dict_items(['name', 'name of the user', ('password', None),
     ('password_age', 123456), ('priv', 2), ('home_dir', ''), 
     ('comment', ''), ('flags', 66081), ('script_path', '')])
"""

import win32api, win32net

def GetUserName():
  return win32api.GetUserName()

def NetUserGetInfo(username):
  return win32net.NetUserGetInfo(None, username, 1)


# main
if __name__ == '__main__':
  print("\n> Current logged on user:", GetUserName())
  #print("\n> Administrator info:", NetUserGetInfo('Administrator'))
  #print("\n> Current logged on user info:", NetUserGetInfo(GetUserName()))
  
  userInfo = NetUserGetInfo(GetUserName())
  
  for item in userInfo:
    print("{0:<15}{1:<10}".format(str(item), str(userInfo[item])))

