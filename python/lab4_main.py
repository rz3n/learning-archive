#!/usr/bin/python3

"""
Path: lab4_main.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-17
Description: Update the hosts file with new hosts from a file
**** CAUTION ****
  This script will overwrite the hosts file without check if 
    the hosts are already in the file

This script will:
  1. open the file specified in "new_hosts_file" variable
  2. create a list of all hosts in this file
  3. import this list from a different script (lab4_libs.py)
  4. add the content under ipv4 section to the /etc/hosts file
"""

import lab4_libs
import re

new_hosts = "./files/dns_list.txt"
hosts_file = "/etc/hosts"

# main
if __name__ == '__main__':
  new_hosts_list = lab4_libs.get_hosts_from(new_hosts)
  hosts_content = lab4_libs.get_file_content(hosts_file)

  new_hosts_content = "" # new content for the hosts file

  for line in hosts_content.splitlines():
    new_hosts_content += line + "\n"

    line_check = re.split(r'\t+', line)
    if line_check[0] == "127.0.0.1" and line_check[1] == "localhost":
      for host in new_hosts_list:
        new_hosts_content += host[0] + "\t" + host[1] + "\n"
  
  lab4_libs.write_to_file(hosts_file, new_hosts_content)
  print("Done!")
