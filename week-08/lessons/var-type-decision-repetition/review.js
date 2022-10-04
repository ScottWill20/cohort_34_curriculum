
// JavaScript is a dynamically typed programming language.
// Data types are not determined at compile-time, they're determined at run-time.

let value2;
console.log(typeof value2); // undefined
value2 = 25;
console.log(typeof value2); // number
value2 = "Hodgepodge";
console.log(typeof value2); // string
value2 = true;
console.log(typeof value2); // boolean
value2 = {};
console.log(typeof value2); // object
value2 = [];
console.log(typeof value2); // object
value2 = null;
console.log(typeof value2); // object

// NUMBERS AND MATH

// The number data type may store values as an integer or floating-point. 
// If an integer operation can't be expressed exactly as an integer, the value is promoted to 
    // floating-point. The runtime decides.

let value = 2 / 3;
console.log(value); // 0.6666666666666666 (promote to floating point)
value = 0.1 + 0.2;
console.log(value); // 0.30000000000000004 (floating point errors)
value = 2 / 0;
console.log(value); // Infinity

value = 2 + "5";           // string concatenation
console.log(value);        // 25
console.log(typeof value); // string

value = 2 * "5";           // math operation
console.log(value);        // 10
console.log(typeof value); // number

value = "15" - "5";        // math operation
console.log(value);        // 10
console.log(typeof value); // number

value = 2 / "Hodgepodge";  // math operation
console.log(value);        // NaN
console.log(typeof value); // number

console.log(value == NaN); // false
console.log(isNaN(value)); // true

// KINDS OF EQUALITY

// The == equality operator does its best to convert operands so their types match. Then it compares them.

console.log("5" == 5);          // true
console.log(5.0 == 5);          // true
console.log(0 == false);        // true
console.log(1 == true);         // true
console.log(null == undefined); // true

console.log("5" == true);       // false
console.log(5 == "artichoke");  // false

// The === equality operator requires that both types and values match.

console.log("5" === 5);          // false
console.log(5.0 === 5);          // true (both are numbers and 5)
console.log(0 === false);        // false
console.log(1 === true);         // false
console.log(null === undefined); // false

// TRUTHINESS

// Decision and loop conditions can be truthy. 
// Like == equality, the runtime does its best to convert the expression to the correct type, 
//     in this case a boolean. The results are not always obvious.

if (true) {
    console.log("true literal is true");
}

if (false) {
    console.log("false literal is true");
}

if ("Hodgepodge") {
    console.log("string literal is true");
}

if (null) {
    console.log("null literal is true");
}

if (undefined) {
    console.log("undefined literal is true");
}

if ({}) {
    console.log("object literal is true");
}

if ([]) {
    console.log("array literal is true");
}

// STRINGS

// Strings are sequences of Unicode characters. 
// JavaScript doesn't have a character data type. It's all strings.

// String literals
let name = "Frank Ocean";      // Literals are delimited with either double quotes
name = 'Frank Ocean';          // or single quotes.

let helloWorld = "你好，世界！"; // unicode

// Choose the delimiter that makes it easy to express the literal. 
let message = "She doesn't like y'all.";               // If it contains a ", use ''.             
message = 'He keeps doing those stupid "air quotes".'; // If it contains a ', use ""

// Delimiters and special characters can be escaped.
let message1 = 'She doesn\'t like y\'all.';           
message1 = "He keeps doing those stupid \"air quotes\".";
message1 = "This message\nspans\nmultiple\nlines and contains\ttabs.";

// get the number of UTF-16 code units (characters, roughly) with a string's length property
let codeUnitCount = name.length;      // 14
codeUnitCount = "你好，世界！".length; // 6

// Template literals allow non-escaped newline characters and string delimiters in a string literal. 
    // They're delimited with a backtick.

    let html = `<html lang="en">
    <head>
    <title>Document</title>
    <body>
        <header>
            <h1 class="main-header">Document Header</h1>
        </header>
        <main id="content">
        </main>
    </body>
    </html>`;

// Template literals can embed or interpolate JavaScript expressions with the ${expression} 
    // escape sequence. 
// The escape sequence evaluates in-scope JavaScript and embeds the expression's value as a string.

let name1 = "Nomi";
let petCount = 3;
let petType = "dogs";
let verticalJump = 37.68;

let message2 = `${name1} has ${petCount} ${petType} and can jump ${verticalJump} inches vertically.`;

console.log(message2); // Nomi has 3 dogs and can jump 37.68 inches vertically.

let a = 2.33;
let b = -1.111;

console.log(`${a} + ${b} = ${a + b}`); // 2.33 + -1.111 = 1.219

// VARIABLE BINDING

// Prior to ES6, all variables were declared with the var binding. var binds variables to function scope.
// A variable declared in a function is visible anywhere inside the function's block.

// i is not visible here.
// Referencing it causes an error.
function loop() {

    // i is visible here. Its value is undefined.
    console.log(i);

    for (var i = 0; i < 3; i++) {
        // i is visible here.
        console.log(i);
    }

    // i is visible here. Its value is 3.
    console.log(i);
}

// i is not visible here (err).

loop();

// ES6 introduced the let and const bindings. let binds a variable to block scope. 
// A let variable is only visible inside the block where it's declared.

// i is not visible here (err).
function loop() {

    // i is not visible here (err).

    for (let i = 0; i < 3; i++) {
        // i is only visible in this block.
        console.log(i);
    }

    // i is not visible here (err).
}

// i is not visible here (err).

// The const binding also binds to block scope. It can only be assigned once. 
// Subsequent assignments cause an error. Effectively, it's a read-only variable once assigned.

const value1 = "sesame oil";
console.log(value1); // sesame oil

// ERROR:
// TypeError: Assignment to constant variable.
value1 = "grapeseed oil";
console.log(value1);

// DECISIONS

// The if statement evaluates a boolean expression. 
// If the expression is true (or truthy), code in the statement's block executes. Be deliberate with truthiness.

const animalCount = 2;
const canFly = true;

if (animalCount > 1 && canFly) { // true
    console.log("a flock");      // executes
}

if (!canFly) {                   // false
    console.log("walker");       // doesn't execute
    console.log("swimmer");
    console.log("crawler");
}

// The else clause can't stand alone. 
// It's always attached to an if. The else block runs if the if expression is false.

const animalCount1 = 1;
const canFly1 = false;

if (animalCount1 > 1 && canFly1) { // false
    // doesn't execute
    console.log("a flock");
} else {
    // executes
    console.log("could be a single bird");
    console.log("a herd?");
    console.log("either they can't fly");
    console.log("or there's one or less animals");
}

if (!canFly1) { // true
    // executes
    console.log("walker");
    console.log("swimmer");
    console.log("crawler");
} else {
    // doesn't execute
    console.log("plane, bird, dragonfly?")
}

// else and if can be stacked to check a sequence of conditions. 
// The first expression to evaluate to true breaks the chain. No further conditions are evaluated.

const packageWeight = 0.55;

if (packageWeight > 100.0) {      // check first condition
    console.log("too big for standard shipping");
} else if (packageWeight < 1.0) { // check a second condition
    console.log("too small. send a letter.");
} else {                          // the `else` clause executes if all other conditions are false.
    console.log("can ship");
}

const color = "orange";

if (color === "red") {
    console.log("red's compliment is green");
} else if (color === "blue") {
    console.log("blue's compliment is orange");
} else if (color === "yellow") {
    console.log("yellow's compliment is purple");
} else if (color === "green") {
    console.log("green's compliment is red");
} else if (color === "orange") {
    console.log("orange's compliment is blue");
} else if (color === "purple") {
    console.log("purple's compliment is yellow");
} else {
    console.log("I don't know that color");
}

// The switch statement inspects a value and then executes code for a specific case.

const place = 2;
let ribbonColor;

// 1. switch keyword, then an expression (often a variable) in parentheses, followed by a block
switch (place) {
    case 1: // 2. case keyword, then a literal value, then colon.
        // if the switch's expression resolves to the case's value,
        // all code nested inside the case executes.
        ribbonColor = "blue";
        console.log("first place!");
        break; // 3. break keyword, immediately exits the switch's block
    case 2:
        ribbonColor = "red";
        console.log("second place!");
        break;
    case 3:
        ribbonColor = "white";
        console.log("third place!");
        break;
    default: // 4. default keyword, executes when no other case matched
        ribbonColor = "unknown";
}

console.log(ribbonColor);

// The while statement evaluates a boolean expression. If it's true, the statement's block runs. 
// Then execution jumps back to the while keyword and repeats. 
// The expression is reevaluated. If it never evaluates to false, we're stuck in an infinite loop.

const message3 = "Visit Mars.";
let index = 0;

while (index < message3.length) {
    console.log(message3.charAt(index));
    index = index + 1;
}

// The do/while statement is similar to while but the boolean expression is evaluated after the block. 
// If the expression is true, the runtime jumps back to the do keyword and the block repeats. 
// do/while executes at least once.

let r = 0;
do {
    r = Math.random();
    console.log(r);
} while (r > 0.1); // ~10% chance to exit

// The for statement is a specialized loop with three clauses:
    // The first initializes variables or state.
    // The second is a boolean expression. If it evaluates to true, the for's code block executes, just like a while statement.
    // The third clause runs after each loop. It can be used to update variables or state.

// Clauses are separated by semicolons. All clauses are optional.

/*
initialize: one-time set-up before looping begins 
condition: a boolean expression, looping continues while it evaluates to true 
after-each-loop: operations that occur after each loop

for (<initialize> ; <condition>; <after-each-loop>) {

}
*/

const message4 = "Looping...";

// Standard format
for (let index = 0; index < message4.length; index++) {
    console.log(message4.charAt(index));
}

// This loop is formatted to demonstrate the clauses.
// The format isn't standard and may be confusing to a Java developer.
for (let index = 0;               // initialize
    index < message4.length; // condition
    index++                   // after each loop
) {
    console.log(message4.charAt(index));
}

// All looping statements can be interrupted with the continue and break keywords.

// continue skips the remaining statements in the code block and starts the next loop iteration.

for (let i = 0; i < 5; i++) {
    console.log(`Number: ${i}`);
    if (i < 3) {
        // jump to the next loop
        continue;
    }
    console.log("Are we there yet?");
}
console.log("We're there!");

// break immediately exits the loop regardless of the loop's condition. 
// The break keyword jumps out of the loop and no more looping occurs.

let word = "I'm melting...";
while (word.length > 0) {
    if (word.length <= 3) {
        // completely exits the loop. it's over.
        break;
    }
    console.log(word);
    word = word.substring(0, word.length - 1);
}

