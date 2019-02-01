from copy import deepcopy


def main():
    with open("input.txt") as f:
        memory = list(map(int, f.read().split()))

    x = len(reallocate_memory(deepcopy(memory), has_repeated))
    y = len(reallocate_memory(deepcopy(memory), has_repeated_twice))
    print(x)
    print(y - x)


def reallocate_memory(memory, repetition_predicate):
    encountered = []
    while not repetition_predicate(encountered, memory):
        encountered.append(deepcopy(memory))
        curr_max = max(memory)
        max_index = memory.index(curr_max)
        memory[max_index] = 0

        for i in range(1, curr_max + 1):
            memory[(i + max_index) % len(memory)] += 1
    return encountered


def has_repeated(encountered, current):
    return True if current in encountered else False


def has_repeated_twice(encountered, current):
    return True if encountered.count(current) == 2 else False

if __name__ == '__main__':
    main()
