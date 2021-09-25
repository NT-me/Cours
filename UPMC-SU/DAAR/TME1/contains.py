from regextree import RegExTree as Ret


def containAltern(trees):
    for t in trees:
        if t.root == '|' and not t.subTrees:
            return True
    return False


def containParenthese(trees):
    for t in trees:
        if t.root == '(' or t.root == ')':
            return True
    return False


def containEtoile(trees):
    for t in trees:
        if t.root == "*" and not t.subTrees:
            return True
    return False


def containPlus(trees):
    for t in trees:
        if t.root == "+" and not t.subTrees:
            return True
    return False


def containConcat(trees):
    flagFound = False
    # print(trees)
    for t in trees:
        if not flagFound and t.root != '|':
            flagFound = True
            continue
        if flagFound:
            if t.root != '|':
                return True
            else:
                flagFound = False
    return False
