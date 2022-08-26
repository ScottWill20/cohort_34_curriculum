
# Capsule Hotel Assessment Plan

## High Level Requirements

* The application user is the hotel administrator.

- On start up, the application prompts the administrator for the hotel's capacity. The capacity determines how many capsules are available.
- The administrator may book a guest in an unoccupied numbered capsule.
- The administrator may check out a guest from an occupied capsule.
- The administrator may view guests and their capsule numbers in groups of 11.

## Technical Requirements
- When the program starts up, capsules and guests will be represented by a String[] of the appropriate size.
- Unoccupied capsules are represented by a null array value.
- Occupied capsules are represented by the occupant's name as a String. 
- At least one method beyond main is required. A few more methods may make your life easier.

## Application Flow and Features

### Start Up

`main()` Method

Will need Scanner, Print
- Print a welcome message
- Prompt user for # of available capsules
- Create a `String[]` array using # of available capsules for capacity (100)
- Print message showing # of available capsules to book

### Main Menu

within `main()` Method

- define a `do/while` loop to keep app running
- Within loop...
  - Print main menu options
  - Prompt user for their menu option
      - _Note: assume that user will provide integer_
  - Print error message if user provides invalid menu option (!1-4)
  - Define a `switch` statement to handle user selected menu option
- Review Bank App


### Check In

Within a method named `checkIn()`

- (Optional) consider checking if all rooms are booked...
  - Print error message
  - Return user to `main()`
- Print header message to confirm with user they are in guest check in
- Prompt user for guest name
  - (Optional) consider forcing user to provide a guest name
- Prompt user for capsule #
  - display the expected range of acceptable values `[1-100]`
  - (Optional) consider forcing user to provide room number w/i available range
- Provide error message if provided capsule number `!null`
  - loop prompt for capsule number
- If provided capsule # unoccupied...
  - using capsule #, `- 1` to get that room's index in the array
  - assign array value with guest name
  - Print confirmation message that guest has been booked into provided capsule #
- Return from `checkIn()` method back to `main()`

### Check Out

Within a method named `checkOut()`

- (Optional) consider checking if all rooms are empty...
  - Print error message
  - Return user to `main()`
- Print header message to confirm with user they are in guest check out
- Prompt user for capsule #
  - display the expected range of acceptable values `[1-100]`
  - (Optional) consider forcing user to provide room number w/i available range
- Provide error message if provided capsule number is unoccupied
    - loop prompt for capsule number
- If provided capsule # is occupied...
  - using capsule #, `- 1` to get that room's index in the array
  - assign array value with `null`
  - Print confirmation message that guest has been checked out of provided capsule #

- Return from `checkOut()` method back to `main`

### View Guests

Within a method named `viewGuest()`

- Print header message to confirm with user they are in View Guest
- Prompt user for capsule #
  - using capsule #, `- 1` to get that room's index in the array
  - display the expected range of acceptable values `[1-100]`
  - (Optional) consider forcing user to provide room number w/i available range
- Print list of capsules
- list 5 rooms below and 5 rooms above user provided capsule # (total 11 rooms)
  - if array element item is `null`, print `[Unoccupied]`
  - if array element item is `!null`, print guest name
  - if provided capsule # is > 5
    - display first 11 rooms
    - Ex. if user provided capsule # = 2, list 1 - 11
  - if provided capsule # is > 95 
    - display last 11 rooms
    - Ex. if user provided capsule # = 97, list 99 - 100

- Return from `viewGuest()` method back to `main`

### Exit

Within a `boolean` method named `exit()`

- Print header message to confirm with user they are in Exit
  - Print ask user to confirm if they want to exit?
  - Print "All data will be lost."
  - Prompt user [yes/no]
    - If no, `false`, back to `main()` method, prompt `do/while` loop in `main`
    - If yes, `true` back to `main()` method, end runtime


## Tasks

_TODO_ define top level tasks with estimates

- Startup (1 hour)
- Main Menu (2 hours)
- Check In (4 hours)
- Check Out (2 hours)
- View Guest (3 hours)
- Exit (1 hour)

**Total Hours: 13 hours**


# Sample UI

Use the following as inspiration. Your UI is not required to be identical.

## Start Up

```
Welcome to Capsule-Capsule.
===========================
Enter the number of capsules available: 100

There are 100 unoccupied capsules ready to be booked.
```
## Main Menu

```
Guest Menu
==========
1. Check In
2. Check Out
3. View Guests
4. Exit
Choose on option [1-4]: 1
```

## Check In

```
Guest Check In
==============
Guest Name: Timi Neno
Capsule #[1-100]: 25

Error :(
Capsule #25 is occupied.

Capsule #[1-100]: 26

Success :)
Timi Neno is booked in capsule #26.
```

## Check Out
```
Guest Check Out
===============
Capsule #[1-100]: 76

Error :(
Capsule #76 is unoccupied.

Capsule #[1-100]: 26

Success :)
Timi Neno checked out from capsule #26
```

## View Guests

Given a capsule number, the "View Guests" workflow shows
the 11 capsules closest to that capsule: 5 smaller and 5 larger.

```
View Guests
===========
Capsule #[1-100]: 26

Capsule: Guest
21: [unoccupied]
22: Kalil Cordoba
23: [unoccupied]
24: [unoccupied]
25: Ker Demanche
26: Timi Neno
27: [unoccupied]
28: [unoccupied]
29: Pren Fancy
30: Odille Grimsditch
31: [unoccupied]
```
## Exit

```
Exit
====
Are you sure you want to exit?
All data will be lost.
Exit [y/n]: y

Goodbye!
```

