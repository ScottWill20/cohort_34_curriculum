// Functions are declared with the function keyword, a valid JavaScript identifier, zero-to-many parameter 
// names (also valid identifiers) grouped by parentheses, and a code block. Parameters define function 
// inputs. The return statement defines the output. return is optional.

/*
function           repeatStr   (value  , times  ) {
[function keyword] [identifier]([parm1], [parm2]) {
    
    [body]

    return           result;
    [return keyword] [value]
}
*/

function repeatStr(value, times) {
    let result = "";
    for(let i = 0; i < times; i++) {
        result += value;
    }
    return result;
}

// Functions are not required to accept parameters or return a value.

// no inputs
function getRandomColor() {
    switch(Math.floor(Math.random() * 4)) {
        case 0:
            return "red";
        case 1:
            return "blue";
        case 2:
            return "yellow";
        default:
            return "green";
    }
}

// no output
function repeatLog(message, times) {
    for(let i = 0; i < times; i++) {
        console.log(message);
    }
}

// FUNCTION EXECUTION

// To execute a function, name its identifier, provide required arguments separated by commas surrounded 
// by parentheses, and optionally capture the output.

const str = repeatStr("xo", 4); // str === "xoxoxoxo"
const color = getRandomColor(); // color is one of "red", "blue", "yellow", or "green"
repeatLog(str, 3);              // prints "xoxoxoxo" to the JavaScript console 3 times.

// FUNCTION EXPRESSIONS

// JavaScript functions are values. They can be assigned to variables, passed as arguments, and returned 
// from functions. The function itself is a value, not just its result. A function expression is an 
// expression that resolves to the function data type (typeof f === "function"). Function expressions 
// can be anonymous. They don't need a name.

// A function stored in a variable or passed as a parameter can be executed in the same way as a declared 
// function: name it, provide arguments, and optionally use the output.

// a bare-minimum function expression is:
// function           ()            {}
// [function keyword] [parentheses] [code block]

console.log(typeof function () { }); // "function"

// function expression can include parameters
const repeatPrint = function(message, n) {
    for (let i = 0; i < n; i++) {
        console.log(message);
    }
};

function executeTwice(func, message) {
    func(message, 2);
}

// execute the function just like a declared function
repeatPrint("Yellow", 2);

const repeatMessage = repeatPrint;
repeatMessage("Blue", 3);

executeTwice(repeatPrint, "Red");
executeTwice(repeatMessage, "Green");

// Below we return anonymous functions as output from the getMathFunction function.

// function declaration
function getMathFunction(operator) {
    switch (operator) {
        case "+":
            // function expression
            return function (a, b) {
                return a + b;
            };
        case "-":
            // function expression
            return function (a, b) {
                return a - b;
            };
        case "*":
            // function expression
            return function (a, b) {
                return a * b;
            };
        case "/":
            // function expression
            return function (a, b) {
                return a / b;
            };
        default:
            // function expression
            return function () { };

    }
}

const plus = getMathFunction("+");
const multiply = getMathFunction("*");
const unknown = getMathFunction("arrrgh");

console.log(plus(2, 5));         // 7
console.log(multiply(10.1, -5)); // -50.5
console.log(unknown(1, 1));      // undefined

// PARAMETERS AND ARGUMENTS

// When we define parameters in a function, we are not required to supply a matching number of arguments 
// when we call it. If a parameter is defined and the caller doesn't supply the argument, the value is 
// undefined. If a caller supplies too many arguments, the extra arguments are ignored.

function go(a, b, c) {
    let message = "a: " + a
        + ", b: " + b
        + ", c: " + c;
    console.log(message);
}

go();                                     // a: undefined, b: undefined, c: undefined
go("maple");                              // a: maple, b: undefined, c: undefined
go("maple", -314);                        // a: maple, b: -314, c: undefined
go("maple", -314, false);                 // a: maple, b: -314, c: false
go("maple", -314, false, "killer squid"); // a: maple, b: -314, c: false
go(1, 2, 3, 4, 5, 6, 7);                  // a: 1, b: 2, c: 3

// The arguments keyword is available inside a function's code block. It's an array-like data structure 
// that holds all argument values, regardless of the number. It can be used to determine the number of 
// arguments and their values, even if there's not a matching parameter.

function goWithArguments() {
    let args = Array.prototype.slice.call(arguments);
    console.log(arguments.length + ": " + args.join());
}

goWithArguments();                                     // 0: 
goWithArguments("maple");                              // 1: maple
goWithArguments("maple", -314);                        // 2: maple,-314
goWithArguments("maple", -314, false);                 // 3: maple,-314,false
goWithArguments("maple", -314, false, "killer squid"); // 4: maple,-314,false,killer squid
goWithArguments(1, 2, 3, 4, 5, 6, 7);                  // 7: 1,2,3,4,5,6,7

// ARROW FUNCTIONS

// Arrow functions express anonymous functions with less syntax but are limited. 
// For example, they can't be used as methods.

/*
Arrow function syntax is:
(name  )  =>     `Hello, ${name}!`     ;
([parm]) [arrow] [expression to return];
*/

const greet = (name) => `Hello, ${name}!`;
console.log(greet("Ayishat")); // "Hello, Ayishat!"

/*
Arrow functions can include multiple statements if a code block is included. 
In that case, the `return` keyword is required.
(a      , b      , c      ) =>      {
([parm1], [parm2], [parm3]) [arrow] [code block];
    return           "value";
    [return keyword] [value];
}          ;
[end block];
*/

const minOfThree = (a, b, c) => {
   let min = Math.min(a, b);
   min = Math.min(min, c);
   return min;
};

console.log(minOfThree(1, 2, 3));    // 1
console.log(minOfThree(1, -2, 3));   // -2
console.log(minOfThree(11, 222, 3)); // 3

// Below we return arrow functions as output from the getMathFunction function.

function getMathFunction(operator) {
    switch (operator) {
        case "+":
            return (a, b) => a + b; // arrow func
        case "-":
            return (a, b) => a - b; // arrow func
        case "*":
            return (a, b) => a * b; // arrow func
        case "/":
            return (a, b) => a / b; // arrow func
        default:
            return () => { };       // arrow func

    }
}

const plus1 = getMathFunction("+");
const multiply1 = getMathFunction("*");
const unknown1 = getMathFunction("arrrgh");

console.log(plus1(2, 5));         // 7
console.log(multiply1(10.1, -5)); // -50.5
console.log(unknown1(1, 1));      // undefined

// REST PARAMETERS

// If we don't know the number of arguments a function will receive, we're not limited to the arguments 
// object. We can use the ...name syntax. Three dots, ..., followed bv any name, activates a rest parameter.
// The named parameter is managed as a standard JavaScript array, not the funky array-like arguments object. 
// A rest parameter must be the last parameter in a parameter list.

function f1(...args) {
    console.log(args);
}

f1();              // [] (still an array, empty)
f1(1);             // [ 1 ]
f1(1, 2, 3);       // [ 1, 2, 3 ]

function f2(a, b, ...c) {
    console.log(a, b, c);
}

f2();              // undefined undefined []
f2(1, 2);          // 1 2 []
f2(1, 2, 3, 4, 5); // 1 2 [ 3, 4, 5 ]

// Rest parameters have clear advantages over arguments.

    // arguments includes all arguments passed to the function. If we're interested in arguments after the second, 
    // we have to do a bit of bookkeeping. Rest parameters eliminate the bookkeeping.

    // arguments is array-like, but not an array. There's an extra step in treating arguments as an array.

    // Rest parameters are always an array, even when arguments are missing or undefined. When arguments are missing, 
    // we still get an empty array. That simplifies the defensive code we need to write.

// DEFAULT PARAMETERS

// Parameters may be given a default value when an argument isn't included. Again, this simplifies defensive code. 
// To assign a default value, name the parameter and use the assignment operator with an in-scope expression.

function f3(a = 19, b = "chime", c) {
    console.log(a, b, c);
}

f3();                  // 19 chime undefined
f3(27);                // 27 chime undefined
f3(27, "surf");        // 27 surf undefined
f3(27, "surf", false); // 27 surf false

function sayHello(name) {
    return `Hello, ${name}!`;
}

// a default can use any expression in scope,
// including function calls
function f4(a, b = sayHello("Nunes")) {
    console.log(a, b);
}

f4();                 // undefined Hello, Nunes!
f4(true);             // true Hello, Nunes!
f4(true, "Dr. Itch"); // true Dr. Itch

// CLOSURES

// In JavaScript, functions create scope.

const one = 1;
// `one` is defined in global scope
// global scope cannot see `two`, `three`, or `four`
// global scope can see the outer function definition

function outer() {

    const two = 2;
    // `two` is defined in outer's scope
    // outer can see `one` and `two`
    // but not `three` or `four`
    // outer scope can see the mid function

    function mid() {

        const three = 3;
        // `three` is defined in mid's scope
        // mid can see see `one`, `two`, and `three`
        // but not `four`
        // mid can see the inner and outer functions

        function inner() {
            const four = 4;
            // `four` is defined in inner's scope
            // inner can see `one`, `two`, `three`, and `four`
            // inner can see outer and mid function
            console.log(one, two, three, four);
        }
    }
}

// A closure is a scope that captures a definition or variable from an outer scope. 
// The scope "closes over" state from an outer scope and prevents it from being garbage collected, 
// even after the original scope disappears.

function outer() {
    const outerState = "lilac";
    return function () {
        // this anonymous function closes over `outerState`
        // even after outer's state is gone, 
        // `outerState` will still be available via this function
        return "tulip " + outerState;
    };
}

const flowerFunc = outer();
// outer's state is now gone because function execution is complete.
// `outerState` is not visible in this scope
// and yet, "lilac" is printed to the console

console.log(flowerFunc()); // tulip lilac

// A closure can reference any state, including function definitions.

function outer(firstName, lastName) {

    function getFullName() {
        // captures `firstName` and `lastName`
        // which is part of outer's scope
        return `${firstName} ${lastName}`;
    }

    function greetWithHonorary(title) {
        // captures `getFullName`
        return title + getFullName();
    }

    return greetWithHonorary;
}

const f = outer("Meesh", "Mayslacks");
console.log(f("Dr. ")); // Dr. Meesh Mayslacks






