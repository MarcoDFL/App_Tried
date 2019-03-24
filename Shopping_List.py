import os
from colorama import Fore, Back, Style
from colorama import init
init()


# Adds a Welcome Message
def greeting():
    print(Fore.LIGHTMAGENTA_EX)
    print("========================================")  # Magenta
    print(Style.RESET_ALL)
    print(Fore.CYAN)
    print("Welcome to Marco's Shopping List System!")  # Light-blue
    print(Style.RESET_ALL)
    print(Fore.LIGHTMAGENTA_EX)
    print("========================================")  # Magenta
    print(Style.RESET_ALL)

    menu()


# Presents the user with their option of functions
def menu():
    choice = None
    while choice is None:
        print(Fore.GREEN)
        print("""1. Add items to Shopping List \n2. Display Shopping List \n3. Delete item in Shopping List""")
        print("")
        choice = int(input("What would you like to do?: "))
        print(Style.RESET_ALL)

    if choice == 1:
        print("")
        shopping_list()

    elif choice == 2:
        print("")
        choice_two()

    elif choice == 3:
        choice_three()
    # if options do not match the above, User gets an warning message letting them know if the lack of implementation
    else:
        # Sets the area red until stopped by line 44
        print(Fore.RED)
        print("======================================")
        print('Warning: Function not implemented yet!')
        print("======================================")
        print(Style.RESET_ALL)
        menu()


# Below is where most of the Shopping List occurs
def shopping_list():
    shopping_list_one = []  # Creates an empty shopping list

    item = input("Please enter your first item: ")  # Asks user for their first item
    shopping_list_one.append(item)  # Adds item into the shopping list
    print("")
    print(Fore.WHITE + Back.GREEN)
    print(item + " has been added to your Shopping List.")  # informs user their item has been added to the lists
    print(Style.RESET_ALL)
    print("")
    while item not in "done":  # Checks that user is not done adding items
        item = input("Enter your next item: ")  # Asks user for their next item
        shopping_list_one.append(item)  # Adds item to the list
        print("")  # Whitespace
        print(Fore.WHITE + Back.GREEN)
        print(item + " has been added to your Shopping List.")  # informs user their item has been added to the lists
        print(Style.RESET_ALL)
        print("")  # Whitespace

    if "done" in shopping_list_one:  # checks if done was added to the list
        shopping_list_one.remove("done")  # Removes "done" from the lists

    print("Your Shopping List: ")
    print(shopping_list_one)  # Prints Shopping List
    item_print = open("test.txt", "a")
    # adds spaces after every item
    for line in shopping_list_one:
        item_print.write(line)
        item_print.write("\n")
    item_print.close()


# Opens the file on read and prints the file
def choice_two():
    if os.stat("test.txt").st_size == 0:
        print(Fore.WHITE + Back.RED)
        print("Shopping List is empty!")
        print(Style.RESET_ALL)
        exit()
    else:
        f = open('test.txt', 'r')
        print(f.read())
        f.close()


# Functions to remove an item from the shopping list
def choice_three():

    f = open('test.txt', 'r')
    if os.stat("test.txt").st_size == 0:
        print(Fore.WHITE + Back.RED)
        print("Shopping List is empty!")
        print(Style.RESET_ALL)
        greeting()
    else:
        print(f.read())
        f.close()
        remove: str = input("Which item would you like to remove?: ")
        if remove != "done":
            with open("test.txt", "r") as f:
                lines = f.readlines()

                with open("test.txt", "w") as f:
                    for line in lines:
                        if line.rstrip("\n") != remove:
                            f.write(line)
                    print(Fore.WHITE + Back.GREEN)
                    print(remove + " has been removed from your shopping list!")
                    print(Style.RESET_ALL)
                    greeting()
        else:
            greeting()


greeting()  # Calls function "greeting"

if __name__ == '__main__':
    pass
