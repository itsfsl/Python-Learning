import time
seconds = int(input("Enter the number of seconds to countdown from: "))

for second in range(seconds, 0, -1):
    print(second)
    time.sleep(1)

print("Time's Up!!")