const assert = require("assert");

// FIRST VOWEL
// Complete the function `getFirstVowel`.
// It should return the first vowel in a string.
// If the string doesn't contain vowels, `value` is null, 
// or `value` is undefined, return an empty string.

function getFirstVowel(value) {
    // if value is null or undefined, return an empty string 
    if (value === null || value === undefined) return "";

    // create a string of vowels 
    const vowels = "aeiouAEIOU";
    // loop through input value
    for (let i=0; i < value.length; i++){
        // if character is in string of vowels return true 
        if (vowels.includes(value.charAt(i))){
            return value.charAt(i);
        }
    }

    return ""; 
}

// Node's assert library will test your function.
// Execute this exercise.
// If you see the message "success!", all tests pass.

assert.strictEqual(getFirstVowel("magnificent"), "a");
assert.strictEqual(getFirstVowel("winsome"), "i");
assert.strictEqual(getFirstVowel("xxx"), "");
assert.strictEqual(getFirstVowel(), "");
assert.strictEqual(getFirstVowel("mAgnificent"), "A");

console.log("success!");