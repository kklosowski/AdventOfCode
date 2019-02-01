from collections import Counter

from Graph import Graph
from Node import Node


def main():
    with open("input.txt") as f:
        inp = f.read().splitlines()
    # inp.sort(key = len)
    nodes = [parse_line(line) for line in inp]
    graph = Graph()

    for node in nodes:
        graph.append(Node(node[0], node[1], node[2] if node[2] else []))

    # Root of the graph(tree)
    print(find_root(graph).name)
    # Unbalanced node (Manually calculate the difference)
    print(find_unbalanced(graph, find_root(graph).name))


def find_root(graph):
    for node in graph.nodes:
        if graph.get_parent(node.name) is None and node.child_nodes:
            return node
    return []


def get_stack_weight(graph, base_node):
    base = graph.get(base_node)
    weight = base.value
    for node in base.child_nodes:
        weight += get_stack_weight(graph, node)

    return weight


def find_unbalanced(graph, base_node):
    base = graph.get(base_node)
    if False not in [balanced(graph, node) for node in base.child_nodes]:
        weights = dict(Counter([get_stack_weight(graph, node) for node in base.child_nodes]))
        x = list(weights.keys())[list(weights.values()).index(1)]
        return [node for node in base.child_nodes if get_stack_weight(graph, node) == x][0]

    for node in base.child_nodes:
        if not balanced(graph, node):
            return find_unbalanced(graph, node)


def balanced(graph, base_node):
    base = graph.get(base_node)

    child_weights = [get_stack_weight(graph, child) for child in base.child_nodes]
    x = set(child_weights)
    if len(set(child_weights)) == 1:
        return True
    else:
        return False


def parse_line(line):
    x = line.split(" -> ")
    name = x[0].split()[0]
    weight = int(x[0].split()[1].strip("(").strip(")"))
    children = None
    if len(x) > 1:
        children = x[1].split(", ")
    return [name, weight, children]


if __name__ == '__main__':
    main()
