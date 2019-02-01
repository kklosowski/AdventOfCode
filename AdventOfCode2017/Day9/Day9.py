import re


def main():
    with open("input.txt") as f:
        line = f.read()

    print(count_garbage(line))
    print(count_score(remove_garbage(line)))


def count_score(line):
    depth = 0
    score = 0
    for c in line:
        if c == '{':
            depth += 1
            score += depth
        if c == '}':
            depth -= 1
    return score


def count_garbage(line):
    # Remove cancelled characters (!*)
    line = re.sub(r'!.', "", line)
    return len(line) - len(re.findall(r'<[^>]*>', line)) * 2 - len(remove_garbage(line))


def remove_garbage(line):
    # Remove cancelled characters (!*)
    line = re.sub(r'!.', "", line)
    # Remove garbage groups and commas (<*>)
    line = re.sub(r'<[^>]*>', "", line)
    return line


if __name__ == '__main__':
    main()
