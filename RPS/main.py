#Rock, paper & scissors game :)

import random

score = 0

while True:
    choices = ['rock', 'paper', 'scissors']
    player = None
    computerChoice = random.choice(choices)

    while player not in choices:
        player = input('rock, paper or scissors?: ').lower()

    if player == computerChoice:
        print('Computer: ' + computerChoice)
        print('Player: ' + player)
        print('Score: ' + str(score))
        print('It is a draw.')

    elif player == 'rock':
        if computerChoice == 'paper':
            print('Computer: ' + computerChoice)
            print('Player: ' + player)
            score -= 1
            print('You lose.')
            print('Score: ' + str(score))
        else:
            print('Computer: ' + computerChoice)
            print('Player: ' + player)
            score += 1
            print('You win.')
            print('Score: ' + str(score))

    elif player == 'paper':
        if computerChoice == 'scissors':
            print('Computer: ' + computerChoice)
            print('Player: ' + player)
            score -= 1
            print('You lose.')
            print('Score: ' + str(score))
        else:
            print('Computer: ' + computerChoice)
            print('Player: ' + player)
            score += 1
            print('You win.')
            print('Score: ' + str(score))

    elif player == 'scissors':
        if computerChoice == 'rock':
            print('Computer: ' + computerChoice)
            print('Player: ' + player)
            score -= 1
            print('You lose.')
            print('Score: ' + str(score))
        else:
            print('Computer: ' + computerChoice)
            print('Player: ' + player)
            score += 1
            print('You win.')
            print('Score: ' + str(score))

    choice = input('Play again(y/n)?: ')
    if choice == 'y':
        continue
    else:
        break