question_number = 0
user_answer = None
score = 0

questions = {'What country has the highest life expectancy?':'A',
             'Where would you be if you were standing on the Spanish Steps?':'B',
             'What car manufacturer had the highest revenue in 2020?':'C',
             'What year was the United Nations established?':'D'}

options = [['A. Hong Kong', 'B. USA', 'C. Germany', 'D. India'],
           ['A. Mexico', 'B. Spain', 'C. Argentina', 'D. Brazil'],
           ['A. Germany', 'B. BMW', 'C. Volkswagen', 'D. Skoda'],
           ['A. 1955', 'B. 1935', 'C. 1925', 'D. 1945']]

for question, answer in questions.items():
    print(question)
    for option in options[question_number]:
        print(option)

    user_answer = input("Enter your choice (A, B, C, D): ").upper()

    if user_answer == answer:
        score += 1
        print('CORRECT!')

    question_number += 1

print('Result: ' + str(int(score / len(questions) * 100)) + '%')
