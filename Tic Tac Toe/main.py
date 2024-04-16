import random
from tkinter import *

def restart():
    global player

    player = random.choice(player_choices)
    label.config(text=player + "'s turn")
    for row in range(3):
        for col in range(3):
            buttons[row][col]['text'] = ""

def next_turn(row, col):

    global player

    if buttons[row][col]['text'] == "" and check_winner() is False:

        if player == player_choices[0]:
            buttons[row][col]['text'] = player

            if check_winner() is False:
                player = player_choices[1]
                label.config(text=(player + "'s turn"))
            elif check_winner() is True:
                label.config(text=(player + ' wins'))
            elif check_winner() == "tie":
                label.config(text="Tie")

        else:
            buttons[row][col]['text'] = player

            if check_winner() is False:
                player = player_choices[0]
                label.config(text=(player + "'s turn"))
            elif check_winner() is True:
                label.config(text=(player + ' wins'))
            elif check_winner() == "tie":
                label.config(text="Tie")

def check_winner():

    for row in range(3):
        if buttons[row][0]['text'] == buttons[row][1]['text'] == buttons[row][2]['text'] != "":
            buttons[row][0].config(bg='Green')
            buttons[row][1].config(bg='Green')
            buttons[row][2].config(bg='Green')
            return True
    for col in range(3):
        if buttons[0][col]['text'] == buttons[1][col]['text'] == buttons[2][col]['text'] != "":
            buttons[0][col].config(bg='Green')
            buttons[1][col].config(bg='Green')
            buttons[2][col].config(bg='Green')
            return True
    if buttons[0][0]['text'] == buttons[1][1]['text'] == buttons[2][2]['text'] != "":
        return True
    elif buttons[0][2]['text'] == buttons[1][1]['text'] == buttons[2][0]['text'] != "":
        return True
    elif empty_spaces() is False:
        return "tie"
    return False

def empty_spaces():

    spaces = 9

    for row in range(3):
        for col in range(3):
            if buttons[row][col]['text'] != "":
                spaces -= 1

    if spaces == 0:
        return False
    else:
        return True

window = Tk()
window.title("Tic Tac Toe")

player_choices = ["X", "O"]

player = random.choice(player_choices)

buttons = [[0, 0, 0],
            [0, 0, 0],
            [0, 0, 0]]

label = Label(text=player + "'s turn", font=('SF Pro', 25))
label.pack(side="top")

restart_button = Button(text="Restart", font=('SF Pro', 25), command=restart)
restart_button.pack(side="top")

frame = Frame(window)
frame.pack()

for row in range(3):
    for col in range(3):
        buttons[row][col] = Button(frame, text="", font=('SF Pro', 25),
                                   width=5, height=3, command=lambda row=row, col=col: next_turn(row, col))
        buttons[row][col].grid(row=row, column=col)

window.mainloop()
