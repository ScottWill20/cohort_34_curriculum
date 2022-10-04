// AD HOC OBJECTS

// An ad hoc object isn't contract-based. It's a collection of name/value properties.

// Create object with object literal notation.
let planet = {};
// Dynamically add 3 properties. Properties can have any type, including `function`.
planet.name = "Jupiter";
planet.moons = ["Io", "Europa", "Ganymede"];
planet.getDescription = function () {
    return `Planet: ${this.name}, Has ${this.moons.length} moons.`;
};

// - If a property is an array, resolve its elements with square bracket array notation.
// - If a property is an object, use the dot operator to access the child object's members.
// - If a property is a method (function), call it with parentheses and arguments.
// - If the named property doesn't exist, it resolves to undefined.

console.log(planet.name);             // Jupiter
console.log(planet.moons[2]);         // Ganymede
console.log(planet.moons.length);     // 3
console.log(planet.getDescription()); // Planet: Jupiter, Has 3 moons.
console.log(planet.diameter);         // undefined

// Objects can be created with the Object constructor and new operator: let o = new Object();. 
// Most JavaScript style guides discourage object constructors.


// Bracket and Name Notation

// Object properties can also be written and read using square bracket notation. Use the property's name as a 
// string inside square brackets. The advantage of this approach is that names are not required to be valid 
// JavaScript identifiers. In the example below, "is a planet with rings" is not a valid identifier. It contains 
// spaces.

let planet = {};
planet["name"] = "Jupiter";
planet["moons"] = ["Io", "Europa", "Ganymede"];
planet["getDescription"] = function () {
    return `Planet: ${this.name}, Has ${this.moons.length} moons.`;
};
planet["is a planet with rings"] = true;

console.log(planet["name"]);                   // Jupiter
console.log(planet["moons"][2]);               // Ganymede
console.log(planet["moons"].length);           // 3
console.log(planet["getDescription"]());       // Planet: Jupiter, Has 3 moons.
console.log(planet["is a planet with rings"]); // true
console.log(planet["diameter"]);               // undefined

// Use this approach very sparingly. We should strive for valid identifiers. Don't use bracket notation to get 
// around naming rules and conventions. It's useful when we have named data that we don't control.


// Explicit Initialization

// If we know an object's properties ahead of time, there's no need to use the dot operator or bracket/name notation. 
// JavaScript's object literal can initialize everything in one statement. Object literal notation is name/value 
// pairs with name and value separated by a colon, :. Each pair is separated by a comma. The value can be any 
// JavaScript value. Literals can be nested.

// // an object with four name/value pairs
// {
//     nameOne: value,
//     nameTwo: value,
//     nameThree: value,
//     nameFour: value
// }

// // nested literals
// {
//     nameOne: value,
//     nameTwo: value,
//     nameThree: {
//         innerName1: value,
//         innerName2: value
//     },
//     nameFour: value
// }


// To replicate our original example:

// This:
let planet = {
    name: "Jupiter",
    moons: ["Io", "Europa", "Ganymede"],
    getDescription: function () {
        return `Planet: ${this.name}, Has ${this.moons.length} moons.`;
    }
};

// is equivalent to:
planet = {};
planet.name = "Jupiter";
planet.moons = ["Io", "Europa", "Ganymede"];
planet.getDescription = function () {
    return `Planet: ${this.name}, Has ${this.moons.length} moons.`;
};


// Shorthand Properties

// A variable may be embedded in an object literal to create a shorthand property. 
// A shorthand property's name is the name of the variable. Its value is the variable's value.

// Shorthand properties can be mixed with standard property literals.

const name = "Taiga";
const averagePrecipitationMillimeters = 450.0;
const flora = ["larch", "spruce", "fir", "pine"];

const biome = {
    name,
    averagePrecipitationMillimeters,
    flora,
    fauna: ["wood frog", "wolverine", "mountain hare", "rough-legged buzzard"]
};

console.log(biome);

// The longer way to write out the code for biome (without Object shorthand):

// const biome = {
//     name: name,
//     averagePrecipitationMillimeters: averagePrecipitationMillimeters,
//     flora: flora,
//     fauna: ["wood frog", "wolverine", "mountain hare", "rough-legged buzzard"]
// };

// <pre class="console">
// {
//   name: 'Taiga',
//   averagePrecipitationMillimeters: 450,
//   flora: [ 'larch', 'spruce', 'fir', 'pine' ],
//   fauna: [ 'wood frog', 'wolverine', 'mountain hare', 'rough-legged buzzard' ]
// }
// </pre>


// JSON

// Web browser and Node.js JavaScript runtimes include a built-in JSON object for serializing objects into a J
// SON string and deserializing valid JSON back into an object.

// JSON.stringify serializes an object to JSON. Its input is an object. The output type is a string.

// All properties are serialized except for functions. Functions aren't serialized because that would create an expectation to 
// deserialize functions and deserialized functions are a security risk. Nested properties are serialized.

let planet = {
    name: "Jupiter",
    moons: ["Io", "Europa", "Ganymede"],
    getDescription: function () {
        return "Planet: " + this.name + ", Has " + this.moons.length + " moons.";
    }
};

let json = JSON.stringify(planet);

console.log(typeof json); // string
console.log(json);
// {"name":"Jupiter","moons":["Io","Europa","Ganymede"]}

// JSON.parse deserializes a valid JSON string to a value. Its input is a string. Parsing invalid JSON throws an exception. 
// Output is a non-function value.

// Primitive types can be parsed as well.

let json1 = '{"name":"Jupiter","moons":["Io","Europa","Ganymede"]}';
let obj = JSON.parse(json1);

console.log(typeof obj); // object
console.log(obj); // { name: 'Jupiter', moons: [ 'Io', 'Europa', 'Ganymede' ] }

json1 = '{"name":"Frank Ocean","albums":["Nostalgia, Ultra","Channel Orange","Endless","Blonde"]}';
obj = JSON.parse(json1);

console.log(obj);
/*
{
    name: 'Frank Ocean',
    albums: [ 'Nostalgia, Ultra', 'Channel Orange', 'Endless', 'Blonde' ]
} */

// non-object types
const b = JSON.parse('true');
const s = JSON.parse('"Nostalgia, Ultra"');
const n = JSON.parse("2.257");

console.log(typeof b, b); // boolean true
console.log(typeof s, s); // string Nostalgia, Ultra
console.log(typeof n, n); // number 2.257


// SPREAD SYNTAX

// Objects can use spread syntax inside of an object literal. 
// The syntax expands (or spreads) an existing object's properties into a new object creating a shallow copy.

// original
const obj1 = {
    a: 1,
    b: "b"
};

// spread out obj's properties
// into a new object, copy
const copy = { ...obj1 };
console.log(copy);
// { a: 1, b: 'b' }

// If an object contains nested objects, be careful with this approach. Nested objects aren't cloned, their reference is copied. So an object property in the original object is the same object as the object property in the copied object. A change to one changes the other.

// Spread can be used to merge objects. If two spread objects have the same property name, the last property wins.

const one = {
    a: 1,
    b: "one",
    c: true
};

const two = {
    a: 2,
    b: "two",
    d: ["red", "blue"]
};

// spread two objects inside a literal
const mergedObj = { ...one, ...two };

console.log(mergedObj);
// { a: 2, b: 'two', c: true, d: [ 'red', 'blue' ] }

// We can also mix standard property literals with spread syntax. If property names conflict, the last property wins.

const one1 = {
    a: 1,
    b: "one"
};

const two1 = {
    a: 2,
    d: ["red", "blue"]
};

// mix property literals with spread syntax
const mergedObj1 = {
    n: "cabbage",
    ...one1,
    c: 25.5,
    ...two1,
    d: "smokey"
};

console.log(mergedObj1);
// { n: 'cabbage', a: 2, b: 'one', c: 25.5, d: 'smokey' }


// OBJECT DESTRUCTURING

// Like arrays, objects can be destructured. An object destructuring assignment extracts individual properties from an object and assigns them to variables. It uses curly brace on the left hand of the assignment to define relationships between variables and properties.

// By default, variable names must match property names.

const learner = {
    id: 1024,
    firstName: "Ro",
    lastName: "Tegshbileg",
    interests: ["fiction", "baking", "swimming"],
    likesIceCream: true
};

let lastName = "Munro";
const { firstName, likesIceCream } = learner;

console.log(firstName);     // Ro
console.log(likesIceCream); // true

// parentheses are required when there's
// no `let` or `const`
({lastName} = learner);

console.log(lastName);      // Tegshbileg

// Rename
// To assign a variable name that's different than the object property, name the object's property, follow it with a colon, and define the desired name. Renaming can define a new variable or use an existing variable.

const learner1 = {
    id: 1024,
    firstName: "Ro",
    lastName: "Tegshbileg",
    interests: ["fiction", "baking", "swimming"],
    likesIceCream: true
};

let last = "Munro";
let lovesIceCream = false;

// define new variables
const {
    firstName: first,
    interests: hobbies
} = learner1;

console.log(first);   // Ro
console.log(hobbies); // [ 'fiction', 'baking', 'swimming' ]

// use an existing variable
({ lastName: last, likesIceCream: lovesIceCream } = learner1);

console.log(last);          // Tegshbileg
console.log(lovesIceCream); // true

// Default Values

// Since objects are dynamic, it's possible a property may be missing. To avoid the undefined value, set a default. After each variable, assign a default with the assignment operator. If a property exists, its value is used. If a property is missing, the default value is used.

const learner2 = {
    id: 1024,
    firstName: "Ro",
    lastName: "Tegshbileg",
    interests: ["fiction", "baking", "swimming"],
    likesIceCream: true
};

const { id = 0, middleName = "X" } = learner2;

console.log(id);         // 1024
console.log(middleName); // X


// Destructuring In Function Parameters

// A function may use destructuring syntax in a parameter definition to break an object into the pieces it requires.

// Note destructuring syntax: { v1, v2, etc }
function makeSummary({ firstName, middleInitial = "X", lastName, interests }) {
    return `${firstName} ${middleInitial}. ${lastName} likes ${interests[0]}.`;
}

const learner3 = {
    id: 1024,
    firstName: "Ro",
    lastName: "Tegshbileg",
    interests: ["fiction", "baking", "swimming"],
    likesIceCream: true
};

const summary = makeSummary(learner3);
console.log(summary); // Ro X. Tegshbileg likes fiction.


