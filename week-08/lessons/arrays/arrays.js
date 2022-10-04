// To declare an array, use array literal notation. It's also possible to instantiate the Array type with the 
// new operator, but most JavaScript style guides, including Airbnb, Google, Idiomatic.js, and Standard.js, 
// discourage array constructors.

let a1 = [];          // Array literal - Good
let a2 = new Array(); // ✗ Array constructor - not idiomatic

// Initialize arrays with values using literal notation.

// initialize with any data type
const colors = ["red", "blue", "yellow"];
const evens = [2, 4, 6, 8];
const booleans = [true, false];

// types can be mixed
const mixedContent = ["red", 2, false]

const makeEven = () => Math.floor(Math.random() * 100) * 2;

// can initialize dynamically
const dynamicContext = [makeEven(), makeEven(), makeEven()];

// ACCESS AND STORE BY INDEX

// To store a value, name the array, put the element's index in square brackets 
// and assign a new value with the assignment operator.

const mixedContent1 = ["red", 2, false];

// access an element by index
const color = mixedContent1[0];
console.log(color);           // red

// an intermediate variable isn't required
console.log(mixedContent1[0]); // red

const likesCookies = mixedContent1[2];
console.log(likesCookies);    // false

// arrays are flexible, an out-of-bounds index is
// not an error, the value is `undefined`
const doesNotExist = mixedContent1[22];
console.log(doesNotExist);    // undefined

// store values
// element types can change
mixedContent1[0] = "blue";
mixedContent1[2] = "green";
console.log(mixedContent1);    // [ 'blue', 2, 'green' ]

// Use the .length property to determine the number of elements in an array.

const colors1 = ["red", "blue", "yellow"];
const evens1 = [2, 4, 6, 8];
const booleans1 = [true, false];

console.log(colors1.length);   // 3
console.log(evens1.length);    // 4
console.log(booleans1.length); // 2

// Array length can be abused. If we assign a value at an index greater than length - 1, the array grows but 
// all elements between the new element and the last original element are undefined. Explicitly setting the 
// array length makes it shrink or grow.

const colors2 = ["red", "blue", "yellow"];

colors2[100] = "orange";
console.log(colors2.length); // 101
console.log(colors2[100]);   // orange
console.log(colors2);        // [ 'red', 'blue', 'yellow', <97 empty items>, 'orange' ]

colors2.length = 2;
console.log(colors2[2]);     // undefined
console.log(colors2);        // [ 'red', 'blue' ]

colors2.length = 25;
console.log(colors2);        // [ 'red', 'blue', <23 empty items> ]

// ADD AND REMOVE AT THE END OF AN ARRAY

// The push method adds one or more elements to the end of an array and returns the final length. It modifies the array.

const colors3 = ["red", "blue", "yellow"];

colors3.push("green");
// [ 'red', 'blue', 'yellow', 'green' ]

const length = colors3.push("orange");
// [ 'red', 'blue', 'yellow', 'green', 'orange' ]

console.log(length); // 5

colors3.push("purple", "indigo", "violet");
/*
[
    'red',    'blue',
    'yellow', 'green',
    'orange', 'purple',
    'indigo', 'violet'
]
*/

// The pop method removes and returns the last element in the array. It modifies the array. 
// If the array is empty, pop returns undefined and leaves the array alone.

const colors4 = ["red", "blue", "yellow"];

let color1 = colors4.pop();
// color  === "yellow"
// colors === [ 'red', 'blue' ]

color1 = colors4.pop();
// color  === "blue"
// colors === [ 'red' ]

colors4.pop(); // remove "red"

color1 = colors4.pop();
// color  === undefined
// colors === []

// The unshift method adds one or more elements to the front of an array and returns the final length. It modifies the array.

const colors5 = ["red", "blue", "yellow"];

colors5.unshift("green");
// [ 'green', 'red', 'blue', 'yellow' ]

const length1 = colors5.unshift("orange");
// [ 'orange', 'green', 'red', 'blue', 'yellow' ]

console.log(length1); // 5

colors5.unshift("purple", "indigo", "violet");
/*
[
  'purple', 'indigo',
  'violet', 'orange',
  'green',  'red',
  'blue',   'yellow'
]
*/

// The shift method removes and returns the first element in the array. It modifies the array. 
// If the array is empty, shift returns undefined and leaves the array alone.

const colors6 = ["red", "blue", "yellow"];

let color2 = colors6.shift();
// color  === "red"
// colors === [ 'blue', 'yellow' ]

color2 = colors6.shift();
// color  === "blue"
// colors === [ 'yellow' ]

colors6.shift(); // remove "yellow"

color2 = colors6.shift();
// color  === undefined
// colors === []

// FUNCTIONAL METHODS

// The forEach method executes a callback for each element in the array. 
// The callback can serve many purposes: checking a condition in each element, modifying elements, or 
// building a result using elements.

const colors7 = ["red", "blue", "yellow"];

// The callback accepts an element from the array,
// the element's index (optional)
// and the original array (optional)
function callback(elem, index, srcArray) {
    console.log(elem, index, srcArray);
}

colors7.forEach(callback);

let html = "";
// arrow functions are convenient for inline callbacks
// can modify scope outside of the callback
colors7.forEach(elem => html += `<li>${elem}</li>\n`);
console.log(html);

// The for...of statement solves a similar problem to the forEach method. It visits every element in an iterable type.

const colors8 = ["red", "blue", "yellow"];

for (const color of colors8) {
    console.log(`${color}`);
}

// find and findIndex iterate through an array until they find a matching element. 
// Once they do, find returns the element. findIndex returns the element's index.

// find and findIndex accept a predicate function. A predicate accepts a value -- an array element -- and returns
// a boolean. true indicates the element is accepted. false is rejected. find and findIndex check each element 
// until the predicate returns true. If no elements match, find returns undefined. findIndex returns -1.

const colors9 = ["red", "blue", "yellow"];

// the predicate accepts a value, an array element,
// the element's index (optional)
// and the source array (optional)
function hasLengthFour(elem, index, srcArray) {
    console.log(elem, index, srcArray);
    return elem.length == 4;
}

// find ----------------------

// returns the first element with a length of 4.
let color3 = colors9.find(hasLengthFour);
console.log(color3); // blue

// returns the first element that starts with "y"
color3 = colors9.find(elem => elem.startsWith("y"));
console.log(color3); // yellow

// returns the first element that ends with "ing"
color3 = colors9.find(elem => elem.endsWith("ing"));
console.log(color3); // undefined

// findIndex --------------------

// returns the first index from an element with a length of 4.
let index = colors9.findIndex(hasLengthFour);
console.log(index); // 1

// returns the first index from an element that starts with "y"
index = colors9.findIndex(elem => elem.startsWith("y"));
console.log(index); // 2

// returns the first index from an element that ends with "ing"
index = colors9.findIndex(elem => elem.endsWith("ing"));
console.log(index); // -1

// filter is another powerful searching method. It can find multiple elements, not just the first match. 
// It accepts a predicate function and returns every accepted element in a new array.

const beelike = [
    "Hoverfly",
    "Banded white-tailed bumblebee",
    "Bee fly",
    "Orange-tailed mining bee",
];

const flies = beelike.filter(b => b.includes("fly"));
console.log(flies);
// [ 'Hoverfly', 'Bee fly' ]

const longNames = beelike.filter(b => b.length > 10);
console.log(longNames);
// [ 'Banded white-tailed bumblebee', 'Orange-tailed mining bee' ]



// The some, every, and includes methods scan each element in an array and return a boolean based on a condition.

// some accepts a predicate and returns true if any element matches.

const numbers = [1, 2, 3, 4, 5];

let result = numbers.some(n => n % 3 == 0);
console.log(result) // divisible by 3? true

result = numbers.some(n => n > 100);
console.log(result) // greater than 100? false

// every accepts a predicate and returns true if all elements match.

const numbers1 = [1, 2, 3, 4, 5];

let result1 = numbers1.every(n => n % 3 == 0);
console.log(result1) // divisible by 3? false

result1 = numbers1.every(n => n < 100);
console.log(result1) // less than 100? true

result1 = ["red", "blue", "yellow"]
    .every(c => !c.includes(" "));

console.log(result1) // no whitespace? true

// includes accepts a value and returns true if the value is an element in the array.

const numbers2 = [1, 2, 3, 4, 5];
const colors0 = ["red", "blue", "yellow"];

let result2 = numbers2.includes(4);
console.log(result2); // true

result2 = numbers2.includes(14);
console.log(result2); // false

result2 = colors0.includes("blue");
console.log(result2); // true

// string comparisons are case sensitive
result2 = colors0.includes("Blue");
console.log(result2); // false



// map and reduce are transformation methods. They visit each element in an array and use them to create a 
// new result.

// map accepts a mapping or transformation function as a callback. A mapping function accepts an element and 
// returns a new value. It transforms an element into something new. map returns a new array that contains 
// the result of applying the function to each element.

const numbers3 = [1, 2, 3, 4, 5];
const colorss = ["red", "blue", "yellow"];

// mapping function accepts a string parameter
// and returns a number
const lengths = colorss.map(c => c.length);
console.log(lengths);         // [ 3, 4, 6 ]

// mapping function accepts a number parameter
// and returns a new number
const doubled = numbers3.map(n => n * 2);
console.log(doubled);         // [ 2, 4, 6, 8, 10 ]

// mapping function accepts a string parameter
// and returns a new string
const upperCaseColors = colorss.map(c => c.toUpperCase());
console.log(upperCaseColors); // [ 'RED', 'BLUE', 'YELLOW' ]



// reduce is an aggregation method. It calculates a single value from the many values in an array. 
// It accepts a function with the following signature:

// accumulator: the aggregated result so far
// currentElement: `reduce` iterates over each element -
//    this is the current element.
function reduce(accumulator, currentElement) {
    return; // return the next accumulated value
}

// reduce works on any data type. Its second parameter is an optional initial value. If the initial value isn't specified, 
// the first element is used and looping starts with the second element.

const numbers4 = [1, 2, 3, 4, 5];
const colorsss = ["red", "blue", "yellow", "green"];

function min(accumulator, currentElement) {
    return Math.min(accumulator, currentElement);
}

// any reducer function works as long as it accepts 
// the expected parameters.

// use a declared function
const minValue = numbers4.reduce(min);
console.log(minValue); // 1

// use an arrow function
const maxValue = numbers4.reduce((a, c) => Math.max(a, c));
console.log(maxValue); // 5

const sum = numbers4.reduce((a, c) => a + c);
console.log(sum);      // 15

// use a function expression
const firstLetterReducer = function (accumulator, currentElement) {
    let result = "";
    if (accumulator.length > 0) {
        result = ",";
    }
    return `${accumulator}${result}${currentElement.charAt(0)}`;
};

// the second argument, "", is an initial value
// if no initial value is specified, the first element is used
// and looping starts with the second element.
const firstLetters = colorsss.reduce(firstLetterReducer, "");
console.log(firstLetters); // r,b,y,g

// SORTING

// The sort method orders array elements in place. It changes the original array instead of creating a new array. 
// sort returns a value, but it's a reference to the original, now sorted, array, not a new array.

// Without an argument, sort ranks elements with their data type default comparison. Numbers are sorted in 
// numeric order ascending. Strings are sorted lexicographically (roughly alphabetically).

const numbers5 = [3, 2, 1, 5, 4];
const colorsz = ["red", "blue", "yellow", "green"];

const sorted = numbers5.sort();
console.log(sorted);             // [ 1, 2, 3, 4, 5 ]
console.log(numbers5);            // [ 1, 2, 3, 4, 5 ]
console.log(sorted === numbers5); // the same array

colorsz.sort();
console.log(colorsz); // [ 'blue', 'green', 'red', 'yellow' ]

// Optionally, the sort method accepts a comparison function. The function accepts two parameters: 
// two array elements to rank against each other. The comparison function must return a number. 
// If the number is less than 0, the first parameter is ranked before the second. If the number is greater than 0,
// the first parameter is ranked after the second. If the number is 0, the first and second parameter have 
// the same rank.

const numbers6 = [3, 2, 1, 5, 4];
const colorszz = ["red", "blue", "yellow", "green"];

function sortDescending(a, b) {
    // ascending would be a - b;
    return b - a;
}

let sorted1 = numbers6.sort(sortDescending);
console.log(sorted1);

// a random sort isn't predictable and it's not sorting
// in the proper sense, but it's an interesting trick
// to "shuffle" array elements randomly.
sorted1 = numbers6.sort((a, b) => Math.random() - 0.5);
console.log(sorted1);

// sort strings descending
sorted1 = colorszz.sort((a, b) => b.localeCompare(a));
console.log(sorted1);

const roygbivCompare = function (a, b) {
    const roygbiv = "roygbiv";
    return roygbiv.indexOf(a.charAt(0)) - roygbiv.indexOf(b.charAt(0));
};

// sort in Roy G. Biv order
// (red, orange, yellow, green, blue, indigo, violet)
sorted1 = colorszz.sort(roygbivCompare);
console.log(sorted1);


// SLICE, SPLICE

// The slice method returns a new array that includes a subset of the original elements. 
// The first parameter is a starting index. The second, optional, parameter is the ending index -- 
// the element at the the end index is not included. We can think of slice as a similar operation to string's 
// substring, but instead of including a subset of characters, it includes a subset of array elements.

// Negative index values represent an offset from the end of the array.

const ccolors = ["red", "orange", "yellow",
    "green", "blue", "indigo", "violet"];

// start at index 1 (zero-based).
// index 5 is not included
let result3 = ccolors.slice(1, 5);
console.log(result3);
// [ 'orange', 'yellow', 'green', 'blue' ]

// start at index 2 and run to the end of the array.
result3 = ccolors.slice(2);
console.log(result3);
// [ 'yellow', 'green', 'blue', 'indigo', 'violet' ]

// the start is offset from the end of the array.
// this grabs the last 2 elements
result3 = ccolors.slice(-2);
console.log(result3);
// [ 'indigo', 'violet' ]

// leave off elements at the ends of the array.
result3 = ccolors.slice(1, -1);
console.log(result3);
// [ 'orange', 'yellow', 'green', 'blue', 'indigo' ]

// is start is bigger than end, an empty array is returned.
result3 = ccolors.slice(5, 2);
console.log(result3);
// []

// splice (easily confused with slice) is an array utility function. It can remove elements, add elements 
// in the middle of an array, or do both. splice modifies the array in place. It does not return a new array. 
// Its return value is an array containing removed elements.

// splice parameters:
    // starting index: the index to start the splice operation
    // items to delete (optional): can be 0, can be more
    // items to insert: zero or more elements to add at the splice location

const colorse = ["red", "orange", "indigo", "violet"];

// start at 2, don't delete, add three new colors
let result4 = colorse.splice(2, 0, "yellow", "green", "green");
console.log(result4); // []
console.log(colorse);
// ['red', 'orange', 'yellow', 'green', 'green', 'indigo', 'violet']
    
// start at 4, delete one (green), add blue
result4 = colorse.splice(4, 1, "blue");
console.log(result4); // ['green']
console.log(colorse);
// ['red', 'orange', 'yellow', 'green', 'blue', 'indigo', 'violet']
    
// start at 1, delete two, add nothing
result4 = colorse.splice(1, 2);
console.log(result4); // [ 'orange', 'yellow' ]
console.log(colorse);
// [ 'red', 'green', 'blue', 'indigo', 'violet' ]


// SPREAD

// Spread syntax is three dots in front of an array name: ...name. It shares a syntax with rest parameters 
// but has a different behavior. Spread expands an array into individual elements.

// For example, we can create a new array out of other arrays by spreading the array elements into the new array.

const warmColors = ["red", "orange", "yellow"];
const coolColors = ["green", "blue", "indigo", "violet"];
const otherColors = ["pink", "maroon"];

// spread into individual elements
const allColors = [...warmColors, ...coolColors, ...otherColors];
console.log(allColors);

const a11 = [2, 3];
const a22 = [6, 7]

// spread syntax can be combined with values
const numbersz = [1, ...a11, 4, 5, ...a22, 8, 9];
console.log(numbersz);

// copy an array with spread syntax
const copy = [...numbersz];

// Spread can also be used to expand array elements into individual arguments passed to a function.

function makeName(honorary, first, last) {
    return `${honorary} ${first} ${last}`;
}

const values = ["Dr.", "Mean", "Itch"];

// spread values into argument that match parameters
const n = makeName(...values);
console.log(n); // Dr. Mean Itch


// ARRAY DESTRUCTURING

// A destructuring assignment is syntax that breaks an object on the right side of the assignment operator, 
// in this case an array, into individual pieces on the left side of the operator. Array destructuring uses 
// square brackets on the left side of the assignment operator to map elements to variables.

// Elements are ignored by leaving empty spaces on the left.

const colorrs = ["red", "blue", "yellow"];

// array destructuring use square brackets to show which 
// index/element should be assigned to which variable.
const [a, b, c] = colorrs;
console.log(a); // red (string, no longer related to the array)
console.log(b); // blue
console.log(c); // yellow

let blue;
// elements are ignored by leaving empty spaces between commas.
[, blue,] = colorrs;

console.log(blue); // blue

// Use rest syntax to destructure remaining elements as an array. 
// Rest syntax is valid in the last assignment position.

const numberss = [2, 4, 6, 8, 10];

const [, element, ...lastThree] = numberss;

console.log(element);   // 4 (number)
console.log(lastThree); // [ 6, 8, 10 ] (array of numbers)

// Any expression that results in an array can be destructured.

function makeArray() {
    return ["tulip", "peony", "lilac", "dandelion"];
}

let aa, bb, cc;
[bb, cc, aa,] = makeArray();
console.log(aa); // lilac
console.log(bb); // tulip
console.log(cc); // peony



