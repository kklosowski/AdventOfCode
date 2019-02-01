def main():
    with open("input.txt") as f:
        inp = f.readlines()
    lines = [line.split() for line in inp]
    print(count_valid_passphrases_1(lines))
    print(count_valid_passphrases_2(lines))


def count_valid_passphrases_1(phrases):
    return sum([is_valid_passphrase(phrase) for phrase in phrases])


def count_valid_passphrases_2(phrases):
    return sum([is_valid_passphrase(phrase) and not has_anagrams(phrase) for phrase in phrases])


def is_valid_passphrase(phrase):
    return len(phrase) == len(set(phrase))


def has_anagrams(phrase):
    return not len(set(["".join(sorted(word)) for word in phrase])) == len(phrase)


if __name__ == '__main__':
    main()
