import sys
import mmap
from colorama import Fore, Back, Style
from colorama import init
init()


# Adds a Welcome Message
def greeting():
    print("========================================")
    print("Welcome to Marco's Shopping List System!")
    print("========================================")
    print("")
    menu()


def menu():
    choice = None
    while choice is None:
        print("")
        print("""1. Add items to Shopping List \n2. Display Shopping List \n3. Delete item in Shopping List""")
        print("")
        choice = int(input("What would you like to do?: "))

    if choice == 1:
        print("")
        shopping_list()

    elif choice == 2:
        print("")
        choice_two()

    elif choice == 3:
        print("")
        choice_three()
    else:
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
    print(item + " has been added to your Shopping List.")  # informs user their item has been added to the lists
    print("")
    while item not in "done":  # Checks that user is not done adding items
        item = input("Enter your next item: ")  # Asks user for their next item
        shopping_list_one.append(item)  # Adds item to the list
        print("")  # Whitespace
        print(item + " has been added to your Shopping List.")  # informs user their item has been added to the lists
        print("")  # Whitespace

    if "done" in shopping_list_one:  # checks if done was added to the list
        shopping_list_one.remove("done")  # Removes "done" from the lists

    print("Your Shopping List: ")
    print(shopping_list_one)  # Prints Shopping List
    outf = open("test.txt", "a")

    for line in shopping_list_one:
        outf.write(line)
        outf.write("\n")
    outf.close()


def choice_two():
    f = open('test.txt', 'r')
    message = f.read()
    print(message)
    f.close()


def choice_three():
    f = open('test.txt', 'r')
    print(f.read())
    f.close()
    remove: str = input("Which item would you like to remove?: ")

    with open("test.txt", "r") as f:
        lines = f.readlines()

        with open("test.txt", "w") as f:
            for line in lines:
                if line.rstrip("\n") != remove:
                    f.write(line)
                    print(remove + " has been removed from your shopping list!")


greeting()  # Calls function "greeting"

if __name__ == '__main__':
    pass
