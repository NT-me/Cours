import math

inputNb = input("Entrez un nombre de 10 digits:\n")
inputIter = input("Combien d'iteration souhaitez-vous ?:\n")

# if len(inputNb) != 10:
#     raise ValueError("Not valid length")

try:
    inputNb = int(inputNb)
    inputIter = int(inputIter)
except:
    raise ValueError("Not a int")


cursor = inputNb
for i in range(0, inputIter):
    print("Iteration nb : {0} | {1}".format(i, cursor))
    squareCursor = cursor**2
    squareCursor_p1 = squareCursor // 10 ** 5
    cursor = squareCursor_p1 % (10 ** 10)
