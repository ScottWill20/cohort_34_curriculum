// Scope is isolate per test file.
// planet doesn't leak into global scope.
let planet;

// Set up before each test
beforeEach(() => {
    planet = {
        name: "Jupiter",
        moons: ["Io", "Europa", "Ganymede"],
        getDescription: function () {
            return "Planet: " + this.name + ", Has " + this.moons.length + " moons.";
        }
    };
});

// Solo test
test("planet name should be Jupiter", () => {
    expect(planet.name).toBe("Jupiter");
});

// Solo test
test("Jupiter should have the correct description", () => {
    expect(planet.getDescription()).toBe("Planet: Jupiter, Has 3 moons.");
});

// Create test groups with `describe`.
describe("Jupiter moons", () => {

    const callisto = "Callisto";

    test("should contain Io", () => {
        expect(planet.moons.length).toBe(3);
        expect(planet.moons).toContain("Io");
    })

    test("should add", () => {
        planet.moons.push(callisto);
        expect(planet.moons.length).toBe(4);
        expect(planet.moons).toContain(callisto);
    })

    test("should remove", () => {
        planet.moons.splice(1, 1);
        expect(planet.moons.length).toBe(2);
        expect(planet.moons).not.toContain("Europa");
    })
});