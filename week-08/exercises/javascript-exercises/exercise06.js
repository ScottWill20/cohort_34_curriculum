// INTERLEAVE
const first = "abc";
const second = "123";

// 1. Write a loop to interleave two strings to form a new string.
// To interleave, during each loop take one character from the first string and add it to the result
// and take one character from the second string and add it to the result.
// If there are no more characters available, don't add characters.

// 2. Print the result.

// Examples
// "abc", "123" -> "a1b2c3"
// "cat", "dog" -> "cdaotg"
// "wonder", "o" -> "woonder"
// "B", "igstar" -> "Bigstar"
// "", "huh?" -> "huh?"
// "wha?", "" -> "wha?"



function interleave(string1, string2) {
    const lengthOfLongestString = Math.max(string1.length,string2.length)

    let result = "";

    for(let i = 0; i < lengthOfLongestString; i++) {
        result += string1.charAt(i);
        result += string2.charAt(i);
    }

   return result;
}

const string1 = "hello";
const string2 = "world";

console.log(interleave(string1, string2))

// let result = "";

// for (let i = 0; i < (first.length + second.length); i++){
//     result += first.charAt(i);
//     result += second.charAt(i);
// }

// console.log(result);
// console.log(result.length);