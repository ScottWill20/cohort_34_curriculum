
# Dev10 M04 Assessment: Sustainable Foraging

## TODO

### Review

* [ ] Verify existing features
  * [X] Add an item
  * [X] View items
  * [ ] View foragers _(this feature is missing)_
  * [X] Add a forage
  * [X] View forages by date
* [X] Step through existing code to confirm understanding
* [X] Manually test existing features
  * [X] Add an Item
  * [X] View Items
  * [X] View Foragers
  * [X] Add a Forage
  * [X] View Forages by date
* [ ] Review existing data layer code
  * [ ] Confirm that every class method is used
  * [ ] Confirm that data persistence works as expected
* [ ] Review existing domain layer code
  * [ ] Confirm that every class method is used
  * [ ] Confirm that data validation is implemented fully and correctly
* [ ] Review requirements and general project information to validate understanding
* [ ] Review existing unit tests to ensure that they work as expected
* [ ] Document findings
  * [ ] Move all TODOs into this README that are actually bugs or things to fix
  * [ ] Commit TODOs before making any code changes

### New Development

* [ ] Add Spring DI to the project
* [ ] Add a Forager
  * [ ] Add unit tests
* [ ] Create a report that displays the kilograms of each Item collected on one day
  * [ ] Add unit tests
  * [ ] Use streams or loops (and intermediate variables)
* [ ] Create a report that displays the total value of each Category collected on one day
  * [ ] Add unit tests
  * [ ] Use streams or loops (and intermediate variables)

### Existing Issues/Bugs

#### High Priority (must fix)

1. [ ] Add the "View Foragers" feature
  * This feature was supposed to be implemented but wasn't even represented in the menu (as an unimplemented item)
1. [ ] Update the `ForageService.validate()` method to validate that the combination of date, item, forager is unique
  * [ ] Add unit test

#### Medium Priority (fix if you have time)

1. [ ] Update the `ForageService.validateFields()` method to check if the kilograms is a positive number (currently is testing the lower bound as less than `0`)
  * [ ] Add unit test
1. [ ] Update the validations in the `ItemService.add()` method to validate that the item category has been provided
  * [ ] Add unit test
1. [ ] Update the validations in the `ItemService.add()` method to correctly validate the $/Kg field value
  * [ ] If the category is edible, medicinal, validate that the $/kr is between $0.01 and $7500.00
  * [ ] If the category is inedible, poisonous, validate that the $/kg is $0
  * [ ] Add unit tests

#### Low Priority (fix as stretch goals)

1. [ ] Comment out all unused methods
  * [ ] `ForageFileRepository.update()`
  * [ ] `ForageRepository.update()`
  * [ ] `ForagerFileRepository.findByState()`
  * [ ] `ForagerRepository.findByState()`
  * [ ] `ItemFileRepository.update()`
  * [ ] `ForagerService.findByState()`
  * [ ] `ConsoleIO.readBoolean()`
1. [ ] Add missing `@Override` annotation to the `ItemFileRepository.add()` method
1. [ ] Add missing `ItemRepository.update()` method (comment out for now as the method isn't being used)
1. [ ] Update the access modifier on the `ItemFileRepository.writeAll()` method to `private`
1. [ ] Update the domain services and repositories so that all stream operations are located in the domain or data layer
  * [ ] Add missing unit tests
1. [ ] Update the `ForagerService.findByLastName()` method so that it's not case-sensitive
  * [ ] Add unit test
1. [ ] Move hard-coded literals into static final variables
1. [ ] Refactor the validations in the `ItemService.add()` method into their own method for consistency
  * [ ] Verify that unit tests exist
1. [ ] Merge the `Response` class into the `Result` class
1. [ ] Update the `View.chooseForager()` method index selection to not check if the index is less than `0`
1. [ ] Update the `View.chooseItem()` method to immediately return if there aren't any items to display
1. [ ] Move the validations found in the `View.getGenerateRequest()` method into the domain layer
1. [ ] Update the validations in the `ForageSerivce.generate()` method to match the approach used in the other domain class methods
  * [ ] Add unit tests
1. [ ] Update the `Controller.generate()` method to handle a result from the domain layer
1. [ ] Rename `.txt` data files to `.csv`
1. [ ] Update CSV file writing in all repositories to escape commas before serializing data
  * [ ] Add unit tests
1. [ ] Update existing unit tests
  * [ ] Improve the design of the existing unit tests
  * [ ] Add additional unit tests to improve code coverage
1. [ ] Update the current implementation for supporting "hidden" menu item
  * [ ] Update the `View.selectMainMenuOption()` method to properly handle more than one hidden menu item

## Glossary

* **Forager:** A person who searches for and harvests wild plants and fungi. Sustainable Foraging tracks a forager's first name, last name, and state.
* **Item:** A wild plant or fungi. Items have a name, an item category, and a price per Kg.
* **Category:** One of four general item groups: edible (good to eat), medicinal (treats illness), inedible (unpleasant to eat, may make you sick, but not poisonous), or poisonous (causes illness or death).
* **Forage:** A successful foraging venture linking a forager to the item they found. Forages have a date, a forager, an item, and the kilograms collected. "Successful" here means that an item was found. That item could be worthless because it's inedible or poisonous.

## The Situation

Sustainable Foraging is almost complete. The previous development team foraged a psychoactive plant or fungi and quit en masse to follow their bliss. Your job is to complete the application by its project deadline.

### Working Features

All of the following features were reported as "completed" by the previous development team, but early application testers have been complaining that some things aren't working correctly or might actually be missing. Review the functionality of each of the following features and fix any issues that you find:

- Add an Item.
- View Items.
- View Foragers.
- Add a Forage.
- View Forages by date.

> **Hint:** One feature is missing entirely and an important validation is missing. Work with your project manager (instructor) to decide when fixes are required.

### Incomplete Features

The following features are not functional, though there may be existing application components that can be used to implement them.

- Add a Forager.
- Create a report that displays the kilograms of each Item collected on one day.
- Create a report that displays the total value of each Category collected on one day.

### Architecture

The application uses a three-layer architecture with data, domain, and ui layers. `learn.foraging.App` is the application entry point.

Components and packages are displayed below.

```
src
├───main
│   ├───java
│   │   └───learn
│   │       └───foraging
│   │           │   App.java
│   │           │
│   │           ├───data
│   │           │       DataException.java
│   │           │       ForageFileRepository.java
│   │           │       ForageRepository.java
│   │           │       ForagerFileRepository.java
│   │           │       ForagerRepository.java
│   │           │       ItemFileRepository.java
│   │           │       ItemRepository.java
│   │           │
│   │           ├───domain
│   │           │       ForagerService.java
│   │           │       ForageService.java
│   │           │       ItemService.java
│   │           │       Response.java
│   │           │       Result.java
│   │           │
│   │           ├───models
│   │           │       Category.java
│   │           │       Forage.java
│   │           │       Forager.java
│   │           │       Item.java
│   │           │
│   │           └───ui
│   │                   ConsoleIO.java
│   │                   Controller.java
│   │                   GenerateRequest.java
│   │                   MainMenuOption.java
│   │                   View.java
│   │
│   └───resources
└───test
    └───java
        └───learn
            └───foraging
                ├───data
                │       ForageFileRepositoryTest.java
                │       ForageRepositoryDouble.java
                │       ForagerFileRepositoryTest.java
                │       ForagerRepositoryDouble.java
                │       ItemFileRepositoryTest.java
                │       ItemRepositoryDouble.java
                │
                └───domain
                        ForageServiceTest.java
                        ItemServiceTest.java
```

## Requirements

### Items

- Name is required.
- Name cannot be duplicated.
- Category is required.
- Dollars/Kg is required.
- Dollars/Kg must be between $0 (inedible, poisonous) and $7500.
- Item ID is a system-generated unique sequential integer.

### Foragers

- First name is required.
- Last name is required.
- State is required.
- The combination of first name, last name, and state cannot be duplicated.
- Forager ID is a system-generated GUID (globally unique identifier).

### Forages

- Item is required and must exist.
- Forager is required and must exist.
- Date is required and must not be in the future.
- Kilograms must be a positive number not more than 250.
- The combination of date, Item, and Forager cannot be duplicated. (Can't forage the same item on the same day. It should be tracked as one Forage.)
- Forage ID is a system-generated GUID (globally unique identifier).

## Technical Requirements

In addition to application features, please add Spring dependency injection to the project. You may configure Spring DI with either XML or annotations.

Generate the kilogram/item report with streams and total value/category report with loops and intermediate variables, but keep the ultimate goal in mind: **using data to create accurate reports**. If one approach gives you too much trouble, use the other to solve the data problem.

All financial math must use `BigDecimal`.

Dates must be `LocalDate`, never strings.

### File Format

Foragers and Items are stored in comma-delimited files with a header. You may not change the delimiter or alter the header. If possible, prevent commas from being added to data. An extra comma will prevent the repositories from reading the record.

Forage data is stored in what we call an "unfortunate decision". Each day's data is stored in a separate file with the naming convention: `yyyy-MM-dd.csv`. 

#### Examples

Forages for 15-July-2020 are stored in `2020-07-15.csv`.

Forages for 31-Oct-2019 are stored in `2019-10-31.csv`.

Forages for 1-Jan-2019 are stored in `2019-01-01.csv`.

---

Forage files are comma-delimited with a header. You may not change any aspect of the Forage data files. It is an inconvenient file format, but it's what the client wanted.

### Testing

All new features must be thoroughly tested. You are not responsible for creating testing for existing features, unless you find a bug. If you find a bug, please create a test to confirm the expected behavior and prevent regressions.

## Approach

Plan before you write code. 

We expect that you will have questions. In fact, you should have questions. Please direct them to your Project Manager (your Instructor). Take time to understand the code before you write new code. Run the application. Trace its execution. Draw pictures.

## Stretch Goals

If time allows, the client would love to get started on Version 2 features. 

They include:

- Update an existing Item.
- Delete an Item. (Careful with this one. If an Item is deleted, one of two things should happen: 1. All Forages that include that Item should also be deleted. Forages without a valid Item are not allowed. 2. Make it a "soft" delete. Add an Item status and hide all Items with a _deleted_ status from search results.)
- Update a Forager.
- Delete a Forager. (See Item deletion.)
- Update a Forage.
- Delete a Forage.
- Figure out a way to view Forages by Forager. This is a non-trivial change.
- Add reports: total value per Forager, Item kilograms collected per Forager