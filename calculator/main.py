num1 = int(input("Enter A: "))
num2 = int(input("Enter B: "))
choice = ''

while len(choice) == 0:
    choice = input('Enter choice(+, -, /, *) or q to exit: ')

if choice == '+':
    print(str(num1) + ' + ' + str(num2) + ' is ' + str(num1 + num2))
elif choice == '-':
    print(str(num1) + ' - ' + str(num2) + ' is ' + str(num1 - num2))
elif choice == '/':
    print(str(num1) + ' / ' + str(num2) + ' is ' + str(num1 / num2))
elif choice == '*':
    print(str(num1) + ' * ' + str(num2) + ' is ' + str(num1 * num2))
elif choice == 'q':
    print('Thank you for using the calculator.')
    exit()
else:
    print('Incorrect input, try again.')
    exit()

print('Thank you for using the calculator.')
exit()
