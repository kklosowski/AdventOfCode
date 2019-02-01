class Node:

    name = ""
    value = ""
    child_nodes = []

    def __init__(self, name, value, nodes):
        self.name = name
        self.value = value
        self.child_nodes = nodes
