#!/usr/bin/env python3

"""
Path: lab10.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-03-23
Description: Script to detect if a word is a palyndrome

This script will:
  1. get the first parameter from the command line
  2. check if the word is a palyndrome
  3. print the result
"""

import sys

# Function to check if a word is a palyndrome
def is_palyndrome(word):
  word = word.lower()
  return word == word[::-1]

# Function to check if a word is a palindrome using loops
def is_palyndrome_loops(word):
  word = word.lower()
  for i in range(len(word)):
    if word[i] != word[-i-1]:
      return False
  return True

# main
if __name__ == '__main__':
  if len(sys.argv) > 1:
    word = sys.argv[1]
    
    # Easyest way
    #if is_palyndrome(word):
    
    # Using loops
    if is_palyndrome_loops(word):
      print("{} is a palyndrome".format(word))
    else:
      print("{} is not a palyndrome".format(word))
  else:
    print("Please provide a word to check if it is a palyndrome")