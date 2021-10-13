import math

DEFAULT = 6065038420

inputNb = input("Entrez un nombre de 10 digits:\n")
# inputIter = input("Combien d'iteration souhaitez-vous ?:\n")

if len(inputNb) == 0:
    print("Valeur par defaut : {}".format(DEFAULT))
    inputNb = DEFAULT
elif len(inputNb) != 10:
    raise ValueError("Not valid length")

try:
    inputNb = int(inputNb)
    # inputIter = int(inputIter)
except:
    raise ValueError("Not a int")


def decreaseEveryDigit(inputNb):
    digits = int(math.log10(inputNb))+1
    for i in range(0, digits):
        tmp = inputNb
        tmp -= 10 ** i
        if i != 0:
            if ((inputNb % (10 ** (i+1))) //(10 ** i)) != 0:
                inputNb = tmp
        else:
            if inputNb % (10 ** (i+1)) != 0:
                inputNb = tmp
    return inputNb


def processKnuth(X):
    Z = (X // (10 ** 8) ) % 10
    neededLine = Z + 3

    if neededLine == 3:
        if X < 5*(10**9):
            X += 5*(10**9)
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 4:
        X = ((X ** 2)//(10**5)) % (10**10)
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 5:
        X = (1001001001 * X) % (10**10)
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 6:
        if X < (10**8):
            X += 9814055677
        else:
            X = (10**10) - X
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 7:
        X = (10 ** 5) * (X % (10 ** 5)) + X // (10**5)
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 8:
        X = (1001001001 * X) % (10 ** 10)
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 9:
        X = decreaseEveryDigit(X)
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 10:
        if X < (10 ** 5):
            X = (X ** 2) + 99999
        else:
            X = X - 99999
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 11:
        while X < 10 ** 9:
            X *= 10
        print("Etape:{0}\nX:{1}".format(neededLine, X))
        neededLine += 1

    if neededLine == 12:
        X = ((X * (X - 1))// (10 ** 5)) % (10 ** 10)
        print("Etape:{0}\nX:{1}".format(neededLine, X))

    return X


def knuth(seed):
    X = seed
    Y = X // 10 ** 9
    while Y >= 0:
        print("\n\n==========\nY: {0}".format(Y))
        X = processKnuth(X)
        print("X: {}".format(X))
        Y -= 1
    return X


print(knuth(inputNb))
