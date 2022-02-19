"""
Path: lab4_libs.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-17
Description: Functions to be used in lab4_main.py
"""

import re

# open a file and returns its content
def get_file_content(file):
  content = ""

  with open(file, 'r') as f:
    try:
      line = f.readline()
      while line:
        content += line

        line = f.readline()

    except:
      print("Error: Unable to open file")

  return content


# open and process a file and returns a list of all hosts in the file
def get_hosts_from(file):
  new_hosts = []

  file_content = get_file_content(file)

  for line in file_content.splitlines():
    a = re.split(r'\t+', line) # split on tabs
    new_hosts.append([a[0], a[1].rstrip("\n")]) # using rstrip to remove the newline character

  return new_hosts


# write content to a file
def write_to_file(file, content):
  with open(file, 'w') as f:
    try:
      f.write(content)

    except:
      print("Error: Unable to write to file")