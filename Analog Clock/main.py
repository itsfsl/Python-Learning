from tkinter import *
from time import *

def update_time():
    time_string = strftime('%I:%M:%S %p')
    timeLabel.config(text=time_string)
    timeLabel.after(1000, update_time)

def update_day():
    day_string = strftime('%A').upper()
    dayLabel.config(text=day_string)
    dayLabel.after(1000, update_day)

def update_date():
    date_string = strftime('%d | %B | %Y').upper()
    dateLabel.config(text=date_string)
    dateLabel.after(1000, update_date)

bgColor = '#070012'

window = Tk()
window.title('Clockie')
window.config(bg=bgColor)
window.resizable(False, False)

clock_size = 27
padx = 20
pady = 0

timeLabel = Label(window, font=('Bahnschrift', clock_size), fg='#9cd17d', bg=bgColor,
                  padx=padx,
                  pady=pady)
timeLabel.pack()

dayLabel = Label(window, font=('Bahnschrift', clock_size + 30, 'bold'), fg='#7dcce8', bg=bgColor,
                 padx=padx,
                 pady=pady)
dayLabel.pack()

dateLabel = Label(window, font=('Bahnschrift', clock_size), fg='#917dd1', bg=bgColor,
                  padx=padx,
                  pady=pady)
dateLabel.pack()

update_time()
update_day()
update_date()

window.mainloop()
