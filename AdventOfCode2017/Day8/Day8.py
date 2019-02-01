import operator


def main():
    with open("input.txt") as f:
        inp = f.readlines()

    ops = {
        "inc": operator.add,
        "dec": operator.sub,
        "==": operator.eq,
        "!=": operator.ne,
        ">=": operator.ge,
        ">": operator.gt,
        "<": operator.lt,
        "<=": operator.le
    }

    register = dict()
    operations = [line.split(" if ")[0].split() for line in inp]
    conditions = [line.split(" if ")[1].split() for line in inp]
    max_ever = 0

    for i in range(len(operations)):
        if operations[i][0] not in register:
            register[operations[i][0]] = 0
        if conditions[i][0] not in register:
            register[conditions[i][0]] = 0
        if ops[str(conditions[i][1])](register[conditions[i][0]], int(conditions[i][2])):
            register[operations[i][0]] = ops[operations[i][1]](register[operations[i][0]], int(operations[i][2]))
        if max(register.values()) > max_ever:
            max_ever = max(register.values())

    # Part 1
    print(max(register.values()))
    # Part 2
    print(max_ever)


if __name__ == '__main__':
    main()
