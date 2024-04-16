from tkinter import *

def button_press(num):
    global equation_text
    if num in ["+", "-", "*", "/"]:
        if equation_text != "" and equation_text[-1] not in ["+", "-", "*", "/"]:
            equation_text += str(num)
            equation_label.set(equation_text)
    else:
        equation_text += str(num)
        equation_label.set(equation_text)


def equals():
    global equation_text
    try:
        if equation_text != "":
            total = str(eval(equation_text))
            equation_label.set(total)
            equation_text = total
    except ZeroDivisionError:
        equation_label.set("Arithmetic Error")
        equation_text = ""
    except SyntaxError:
        equation_label.set("Syntax Error")
        equation_text = ""

def clear():
    global equation_text
    equation_text = ""
    equation_label.set("")

def key_press(event):
    global equation_text
    if event.char.isdigit() or event.char in ['+', '-', '*', '/', '.', '=']:
        button_press(event.char)
    elif event.keysym == 'Return':
        if equation_text != "":
            equals()
    elif event.keysym == 'BackSpace':
        equation_text = equation_text[:-1]
        equation_label.set(equation_text)

window = Tk()
window.title("Modern Calculator")
window.geometry("400x600")
window.config(bg="#fafafa")  # Change background color

equation_text = ""
equation_label = StringVar()

label = Label(window, textvariable=equation_label, bg="#fafafa", fg="#333", font=('SF Pro', 30), anchor="e", padx=20, pady=20)
label.pack(fill="both", expand=True)

frame = Frame(window, bg="#fafafa")  # Change frame background color
frame.pack(expand=True, fill="both")

buttons = [
    ("7", 1, 0), ("8", 1, 1), ("9", 1, 2), ("/", 1, 3),
    ("4", 2, 0), ("5", 2, 1), ("6", 2, 2), ("*", 2, 3),
    ("1", 3, 0), ("2", 3, 1), ("3", 3, 2), ("-", 3, 3),
    ("0", 4, 0), (".", 4, 1), ("=", 4, 2), ("+", 4, 3)
]

for (text, row, col) in buttons:
    if text == "=":
        button = Button(frame, text=text, font=('SF Pro', 24), padx=20, pady=20, bg="#2ea8ff", fg="#333", borderwidth=0,  # Change button color
                        command=equals)
        button.grid(row=row, column=col, sticky="nsew")
    else:
        button = Button(frame, text=text, font=('SF Pro', 24), padx=20, pady=20, bg="#f0f0f0", fg="#333", borderwidth=0,  # Change button color
                        command=lambda t=text: button_press(t))
        button.grid(row=row, column=col, sticky="nsew")

# Stretch empty columns and rows
for i in range(5):
    frame.grid_rowconfigure(i, weight=1)
for i in range(4):
    frame.grid_columnconfigure(i, weight=1)

buttonClr = Button(window, text='CLR', font=('SF Pro', 20), padx=20, pady=20, bg='#ff5733', fg='#fff', borderwidth=0, command=clear)  # Change button color
buttonClr.pack(fill="x")

# Bind keyboard events
window.bind("<Key>", key_press)

window.mainloop()
