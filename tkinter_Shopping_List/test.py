from tkinter import *
window = Tk()
window.title("Marco's Shopping List")
window.geometry('600x400')

w = Label(text="Marco's Shopping List", font=("Courier", 20)).grid(row=0, column=2)

todo = Label(text="What would you like to do? ").grid(row=1, column=2)
#todo.config(font=("Courier", 12))

oneLabel = Label(text="A test").grid(row=3, column=2)
oneButton = Button(text="First button").grid(row=3, column=3)



window.mainloop()