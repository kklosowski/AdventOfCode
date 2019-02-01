def main():
    with open("input.txt") as f:
        inp = f.read()

    digits = [int(d) for d in inp]

    print(sum_matched_offset(digits, 1))
    print(sum_matched_offset(digits, len(digits)//2))


def sum_matched_offset(digits, offset):
    matched_sum = 0
    for i in range(len(digits)):
        if digits[i] == digits[(i + offset) % len(digits)]:
            matched_sum += digits[i]

    return matched_sum


if __name__ == '__main__':
    main()
