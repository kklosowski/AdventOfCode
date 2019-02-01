from copy import deepcopy


def main():
    with open("input.txt") as f:
        inp = f.readlines()
    instructions = list(map(int, inp))
    print(execute_instructions_1(deepcopy(instructions)))
    print(execute_instructions_2(deepcopy(instructions)))


def execute_instructions_1(instructions):
    steps = 0
    i = 0
    while i < len(instructions):
        instructions[i] += 1
        i += instructions[i] - 1
        steps += 1
    return steps


def execute_instructions_2(instructions):
    steps = 0
    i = 0
    while i < len(instructions):
        offset = -1 if instructions[i] >= 3 else 1
        instructions[i] += offset
        i += instructions[i] - offset
        steps += 1
    return steps

if __name__ == '__main__':
    main()
