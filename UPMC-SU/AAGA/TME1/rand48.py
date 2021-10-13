import math

iter = None
X = None


def rand48(seed, iter):
    xn = seed
    res = []
    for i in range(0, iter):
        xn = (25214903917 * xn + 11) % (2 ** 48)

        tmp = xn >> 16
        if tmp >> 31 == 1:
            tmp = abs(tmp) - (2 ** 32)
        res.append(tmp)

    return res


def bit_suivant():
    global X
    global iter
    if X is None:
        X = rand48(1 + (2 ** 40), 1)[0]

    if iter is None:
        iter = 1

    if iter >= 48:
        X = rand48(X + (2 ** 40), 1)[0]

    digits = int(math.log10(X))+1
    x_wr = X >> (iter - 1)
    x_wr = x_wr << 48 - (48 - iter + 1)

    print(X)
    print(x_wr)



if __name__ == '__main__':
    # print(rand48((2 ** 40) + 4309 * 4, 10))
    bit_suivant()
