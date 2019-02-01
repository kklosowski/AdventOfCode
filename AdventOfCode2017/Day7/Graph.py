class Graph:
    nodes = []

    def append(self, node):
        self.nodes.append(node)

    def get(self, name):
        return next((node for node in self.nodes if node.name == name), None)

    def get_parent(self, node):
        for n in self.nodes:
            if node in n.child_nodes:
                return n
        return None
