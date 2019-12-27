import { INPUT } from './input'

class Day2 {
    public solvePart1() {
        return this.executeIntcode([...INPUT], 12, 2)
    }

    public solvePart2() {
        const requiredOutput = 19690720
        for (let x = 0; x < 100; x++) {
            for (let y = 0; y < 100; y++) {
                let result = this.executeIntcode([...INPUT], x, y)
                if (result == requiredOutput) return 100 * x + y
            }
        }
    }

    public executeIntcode(program: number[], noun: number, verb: number) {
        program[1] = noun
        program[2] = verb

        for (let i = 0; i < program.length; i += 4) {
            switch (program[i]) {
                case 1:
                    program[program[i + 3]] = program[program[i + 1]] + program[program[i + 2]]
                    break
                case 2:
                    program[program[i + 3]] = program[program[i + 1]] * program[program[i + 2]]
                    break
                case 99:
                    break
            }
        }

        return program[0]
    }
}

const day2 = new Day2()

console.log('Part 1:', day2.solvePart1())
console.log('Part 2:', day2.solvePart2())
