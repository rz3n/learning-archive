#!/usr/bin/python3

"""
Path: lab1.py
Author: Ricardo Franzen - rfranzen@gmail.com
Date: 2022-01-15
Description: lab #1

This script will:
  1. Print a menu with all groceries and prices in a list
  2. User select an item from list and inform the quantity
  3. The program applies the multiplier
  4. When user inform to calculate the final price, program
      applies GST and shows the final value
"""

liquors = [("Beer pack",17), ("Red Wine",20), ("Rum",35), ("Vodka",27), ("Whisky",50), ("White Wine",21)]
myList = [] # [product name, product price, quantity]
GST = 1.15

# using "liquors" list, print the menu
def printMenu():
  print("0 - Finish and show the final value")
  for item in range(len(liquors)):
    print(item+1,"-",liquors[item][0]," - $" + str(liquors[item][1]))

# check "myList" list and display how many items user has in cart
def printCart():
  total = 0
  for count in range(len(myList)):
    total += myList[count][2]
  print("\n> You have " + str(total) + " items in your cart\n\n")

# calculate items and quantities and apply the GST to final price
def processOrder():
  finalPrice = 0

  print("\n**** Receipt ****")
  for item in range(len(myList)):
    productName = myList[item][0]
    productPrice = myList[item][1]
    productQuantity = myList[item][2]

    print(productName + " - $" + str(productPrice) + " x " + str(productQuantity))

    finalPrice += productPrice * productQuantity
  
  priceGST = finalPrice*GST
  print("\n> Final price: $" + str(round(finalPrice, 2)))
  print("> Final price + GST: $" + str(round(priceGST, 2)))
  print("*****************\n")

# main
if __name__ == '__main__':
  while True:
    printMenu()

    while True:
      try:
        userChoice = int(input("\nType your option or 0 (zero) to exit: "))
        if userChoice > len(liquors) or userChoice < 0:
          print("\n> Invalid option, try again")
        else:
          break
      except:
        print("\n!! Value must be an integer !!")

    if userChoice == 0:
      processOrder()
      break
    else:
      userChoice = int(userChoice)

      while True:
        try:
          quantity = int(input("Please, inform the quantity of this item: "))
          myList.append([liquors[userChoice-1][0], liquors[userChoice-1][1], int(quantity)])
          break
        except:
          print("\n!! Value must be an integer !!")

    printCart()
