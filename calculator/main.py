def add(*nums):
    total = 0
    for num in nums:
        total += num
    print('Total is ' + str(total))

def sub(x, y):
    print(str(x) + ' - ' + str(y) + ' is ' + str(x - y))

def divide(x, y):
    print(str(x) + ' / ' + str(y) + ' is ' + str(x / y))

def multiply(x, y):
    #print(str(num1) + ' * ' + str(num2) + ' is ' + str(num1 * num2))
    print('{} * {} is {}'.format(x, y, (x * y)))

num1 = int(input("Enter A: "))
num2 = int(input("Enter B: "))
choice = None

while not choice:
    choice = input('Enter choice(+, -, /, *) or q to exit: ')

if choice == '+':
    add(num1, num2)
elif choice == '-':
    sub(num1, num2)
elif choice == '/':
    divide(num1, num2)
elif choice == '*':
    multiply(num1, num2)
elif choice == 'q':
    print('Thank you for using the calculator.')
    exit()
else:
    print('Incorrect input, try again.')
    exit()

print('Thank you for using the calculator.')
exit()
