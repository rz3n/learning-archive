#!/usr/bin/python3

"""
Path: project3/client.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-03-15
Description: Client side that will connect to a server and send a file
             from a specific directory.

This script will:
  1. Generate a md5sum hash of the file
  2. Generate a JSON with the hostname, md5sum and the file creation date
  3. Connect to the server
  4. Send the JSON to the server
  5. Send the file to the server
  4. Wait for the server to send a confirmation
"""

from sys import platform
import json
import hashlib
import os
import socket
import time

REPORT_FILE = "/tmp/report.txt"
SERVER_PORT = 50000
SERVER_IP = "172.16.51.129"

# Function to get hostname
def get_hostname():
  return socket.gethostname()

# Function to generate md5sum hash of a file
def gen_md5sum(file_name):
  file = open(file_name, "rb")
  content = file.read()
  hashlib.md5().update(content)
  return hashlib.md5().hexdigest()

# Function to connect to server
def connect_to_server(hostname, port):
  s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  s.connect((hostname, port))
  return s

# Function to build json with hostname and hash md5sum
def build_json(hostname, md5sum, date):
  json_data = '{"hostname": "' + hostname + '", "md5sum": "' + md5sum + '", "date": "' + date + '"}'
  json_data = json.loads(json_data)
  return json_data

# Functino to send json to server
def send_json(s, json_data):
  s.send(json.dumps(json_data).encode())

# Function to wait for confirmation from server
def wait_for_confirmation(s):
  confirmation = s.recv(1024).decode()
  if confirmation == "OK":
    return True
  else:
    return False

# Function to send file to server
def send_file(s, file_name):
  file = open(file_name, "rb")
  content = file.read()
  s.send(content)


# main
if __name__ == '__main__':

  # Check if the OS is a Linux OS
  if platform == "linux" or platform == "linux2":
    # Check if the report file exists
    if os.path.exists(REPORT_FILE):
      
      # Generate md5sum hash of the report file
      md5sum = gen_md5sum(REPORT_FILE)
      
      # Connect to server
      s = connect_to_server(SERVER_IP, SERVER_PORT)
      
      # get the date in a human readable format yyyy-mm-dd_hh-mm-ss
      date = os.path.getmtime(REPORT_FILE)
      date = time.strftime("%Y-%m-%d_%H-%M-%S", time.localtime(date))

      # Build json with hostname and md5sum hash
      json_data = build_json(get_hostname(), md5sum, date)

      while True:
        # Send json to server
        send_json(s, json_data)

        # Send file to server
        send_file(s, REPORT_FILE)

        # Wait for confirmation from server
        if wait_for_confirmation(s):
          print("File sent successfully!", end='\n')
          break
        else:
          print("Error: md5sum hash does not match the received file", end='\n')
          break
      
      # Close connection
      s.close()

    else:
      print("Report file not found!", end='\n')

  else:
    print("This script is only compatible with Linux OS.", end='\n')
