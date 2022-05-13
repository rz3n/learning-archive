#!/usr/bin/python3

"""
Path: project3/server.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-03-15
Description: Server side that will stay listening for incoming 
              connections, receive a file and save it to disk.

This script will:
  1. Listen for incoming connections
  2. Receive a JSON with the hostname, md5sum and the file creation date
  3. Receive a file
  4. Save the file to disk with the name: {hostname}_{date}.txt
  5. Check if the md5sum hash matches the received file
  6. Send a confirmation to the client.
"""

from sys import platform
import json
import os
import socket
import hashlib

LISTENING_PORT = 50000

# Function to start listening for incoming connections
def start_listening(port):
  s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  s.bind(("", port))
  s.listen(1)
  return s

# Funntion to receive json from client
def receive_json(s):
  json_data = s.recv(1024).decode()
  json_data = json.loads(json_data)
  return json_data

# Function to decode json
def decode_json(json_data):
  hostname = json_data["hostname"]
  md5sum = json_data["md5sum"]
  date = json_data["date"]
  return hostname, md5sum, date

# Function to save file to disk
def save_file(file_name, content):
  file = open(file_name, "wb")
  file.write(content)
  file.close()

# Function to check if the md5sum hash matches the received file
def check_md5sum(md5sum, file_name):
  file = open(file_name, "rb")
  content = file.read()
  hashlib.md5().update(content)
  return hashlib.md5().hexdigest() == md5sum



# Function to send confirmation to client
def send_confirmation(s):
  s.send("OK".encode())
  s.close()

# main
if __name__ == '__main__':
  # Check if the OS is a Linux OS
  if platform == "linux" or platform == "linux2":
    
    # Start listening for incoming connections
    print("Listening for incoming connections...")
    print("Port: {}".format(LISTENING_PORT))
    s = start_listening(LISTENING_PORT)
    
    # Wait for incoming connections
    while True:
      # Accept incoming connection
      conn, addr = s.accept()
      print("Connection from: {}".format(addr))
      
      # Receive json from client
      json_data = receive_json(conn)
      print(" - JSON Received")

      # Receive file from client
      content = conn.recv(1024)
      print(" - File Received")

      # Decode json
      hostname, md5sum, date = decode_json(json_data)

      # Generate file name
      file_name = "{}_{}.txt".format(hostname, date)

      # Save file to disk
      save_file(file_name, content)

      # Check if the md5sum hash matches the received file
      if check_md5sum(md5sum, file_name):
        print(" [OK] Hash matches", end='\n\n')
      else:
        print(" [x] Hash does not match", end='\n\n')
        # Delete the file
        os.remove(file_name)

      # Send confirmation to client
      send_confirmation(conn)

  else:
    print("This script is only compatible with Linux OS.")
