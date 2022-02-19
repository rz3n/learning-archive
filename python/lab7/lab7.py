#!/usr/bin/python3

"""
Path: lab7.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-22
Description: Client/Server application to transfer files.

This script will:
  1. first parameter defines if the script is running on the client or server
  2. server mode will:
    2.1. -f or --file to specify the destination file
    2.2. -p or --port to specify the port to listen on
  3. client mode will:
    3.1. -p or --port defines the port to be used
    3.2. -i or --ip defines the ip to be used
    3.3. -f or --file defines the file to be used
"""

import socket
import tqdm
import os
import argparse

# Global variables
SERVER_IP = "0.0.0.0"
SERVER_PORT = 5001
BUFFER_SIZE = 1024*4 #4kb
SEPARATOR = "<SEPARATOR>"

def serverMode(file, port):
  # create the server socket
  # TCP socket
  s = socket.socket()
  # bind the socket to our local address
  s.bind((SERVER_IP, SERVER_PORT))
  # enabling our server to accept connections
  # 5 here is the number of unaccepted connections that
  # the system will allow before refusing new connections
  s.listen(5)
  print(f"[*] Listening as {SERVER_IP}:{port}")
  # accept connection if there is any
  clientSocket, address = s.accept()
  # if below code is executed, that means the sender is connected
  print(f"[+] {address} is connected.")

  # receive the file infos
  # receive using client socket, not server socket
  received = clientSocket.recv(BUFFER_SIZE).decode()
  filename, filesize = received.split(SEPARATOR)
  # remove absolute path if there is
  filename = os.path.basename(filename)
  # convert to integer
  filesize = int(filesize)
  # start receiving the file from the socket
  # and writing to the file stream
  progress = tqdm.tqdm(range(filesize), f"Receiving {filename}", unit="B", unit_scale=True, unit_divisor=1024)
  with open(file, "wb") as f:
    while True:
      # read 1024 bytes from the socket (receive)
      bytesRead = clientSocket.recv(BUFFER_SIZE)
      if not bytesRead:    
        # nothing is received
        # file transmitting is done
        break
      # write to the file the bytes we just received
      f.write(bytesRead)
      # update the progress bar
      progress.update(len(bytesRead))
  
  # close the client socket
  clientSocket.close()
  # close the server socket
  s.close()


def clientMode(file, ip, port):
  # create the client socket
  # TCP socket
  s = socket.socket()
  # connect to the server
  print(ip + ":" + str(port))
  s.connect((ip, port))
  # sending the file infos
  # sending using client socket, not server socket
  s.send(f"{file}{SEPARATOR}{os.path.getsize(file)}".encode())
  # start sending the file from the file stream
  # and reading from the socket
  progress = tqdm.tqdm(range(os.path.getsize(file)), f"Sending {file}", unit="B", unit_scale=True, unit_divisor=1024)
  with open(file, "rb") as f:
    while True:
      bytesRead = f.read(BUFFER_SIZE)
      if not bytesRead:
        break
      s.send(bytesRead)
      progress.update(len(bytesRead))
  # close the client socket
  s.close()


if __name__ == "__main__":
  parser = argparse.ArgumentParser(description="Simple File Sender")
  parser.add_argument("mode", help="Mode of execution", choices=["server", "client"])
  parser.add_argument("-f", "--file", help="Destination file")
  parser.add_argument("-p", "--port", help="Port to listen on", type=int)
  parser.add_argument("-i", "--ip", help="IP to connect to")
  args = parser.parse_args()

  if args.mode == "server":
    serverMode(args.file, args.port)
  elif args.mode == "client":
    clientMode(args.file, args.ip, args.port)

