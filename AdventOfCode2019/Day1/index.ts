import { INPUT } from './input'

class Day1 {
    public solvePart1() {
        return this.calculateRequiredFuel(INPUT)
    }

    public solvePart2() {
        return INPUT.map(_module => {
            let fuelTotal = this.calculateRequiredFuel([_module])
            let lastWeight = fuelTotal
            let currentRequirement = 0

            while ((currentRequirement = this.calculateRequiredFuel([lastWeight])) > 0) {
                fuelTotal += currentRequirement
                lastWeight = currentRequirement
            }

            return fuelTotal + (currentRequirement > 0 ? currentRequirement : 0)
        }).reduce((x, y) => x + y, 0)
    }

    private calculateRequiredFuel(moduleWeights: number[]) {
        return moduleWeights
            .map(x => Math.floor(x / 3) - 2)
            .filter(x => x > 0)
            .reduce((x, y) => x + y, 0)
    }
}

const day1 = new Day1()

console.log('Part 1:', day1.solvePart1())
console.log('Part 2:', day1.solvePart2())
