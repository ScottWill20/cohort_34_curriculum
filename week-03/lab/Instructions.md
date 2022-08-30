## Lab: File I/O
Work with a partner to solve the problem defined inside. The README is a text file that both describes and is the problem.
You are working with a simple programming language and are essentially creating a language interpreter.

### Instructions:
Work with a partner to solve the problem defined inside. The `README` is a text file that both describes and is the problem.
The `README` defines and is a line-based command format
Comments start with a `#` and describe the command language and expectations
Blank lines are ignored
There are for other commands:
`CREATE`
`APPEND`
`DELETE`
`COPY`

## TO DO
* Create the project
    * Add the README.txt file
    * Open the `README` and print each line to the console
    * Implement `CREATE`
    * Implement `APPEND`
    * Implement `DELETE`
    * Implement `COPY`
#### CREATE
Create a new file based on a relative path.
#### APPEND
Append content to the end of an existing file defined by a relative path. The README never asks to append to a non-existent file. If students choose to add commands to the README, they can decide how they want to handle an append for a file that doesn't exist.
#### DELETE
Delete a file based on a relative path. If the file doesn't exist, the command still succeeds.
#### COPY
Copy the contents of one file to another based on relative paths. If the source file doesn't exist, ignore the command. If the destination doesn't exist, it should be created. If the destination exists, contents should be appended, not replaced.