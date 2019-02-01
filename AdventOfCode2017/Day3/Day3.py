import math


def main():
    for i in range(1, 100):
        print(str(i) + " - " + str(lowest_contained_odd_square(i)) + " - " + str(side_length(i)) + " - " + str(
            find_coordinate(i)))


def lowest_contained_odd_square(num):
    x = int(math.floor(math.sqrt(num)))
    return x if x % 2 else x - 1


def find_coordinate(num):
    if math.sqrt(num).is_integer():
        return math.sqrt(num) // 2, -(math.sqrt(num) // 2)



if __name__ == '__main__':
    main()
