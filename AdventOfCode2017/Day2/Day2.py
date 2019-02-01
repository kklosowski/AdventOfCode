def main():
    with open("input.txt") as f:
        inp = f.readlines()
        lines = [list(map(int, line.split())) for line in inp]
        print(minmax_checksum(lines))
        print(evenly_divisible_checksum(lines))


def minmax_checksum(lines):
    return sum([max(line) - min(line) for line in lines])


def evenly_divisible_checksum(lines):
    divided = [[[x // y for x in line if (x != y and not (x % y))] for y in line] for line in lines]
    return sum(flatten(divided))


def flatten(L):
    if L == []:
        return L
    if isinstance(L[0], list):
        return flatten(L[0]) + flatten(L[1:])
    return L[:1] + flatten(L[1:])


if __name__ == '__main__':
    main()
