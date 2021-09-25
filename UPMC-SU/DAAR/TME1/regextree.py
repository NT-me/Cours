class RegExTree:

    def __init__(self, root, subTrees=None):
        # Root value embed into this node
        self.root = root

        # RegExTrees children list
        self.subTrees = subTrees

    def __str__(self):
        if not self.subTrees:
            return str(self.root)
        res = str(self.root) + "(" + str(self.subTrees[0])

        for i in range(1, len(self.subTrees)):
            res += "," + str(self.subTrees[i])

        return res + ")"
