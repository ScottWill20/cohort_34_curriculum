
# Module 4 Code Reviews

## Process

_See [this document](../misc/code-reviews.md) for a complete breakdown of the code review process._

## Requirements Checklist

* [ ] Add a Forager
  * Does the ForagerService class contain the necessary validations?
* [ ] Report: Item kg/day
  * Is the Stream API code located in the domain or data layer?
* [ ] Report: Category $/day
  * Is the Stream API code located in the domain or data layer?
* [ ] Missing Feature (View Foragers)
  * Did the trainee use the existing ForagerService and ForagerFileRepository `findByState()` methods?
* [ ] Validation (Duplicate Forages)
  * This validation could be addressed two different ways: reject duplicate forages using a validation or allow duplicate forages by updating the existing forage instead of creating a new forage
  * Were the test doubles updated to support unit testing this validation?
* [ ] Resolved Existing Bugs
  * The `ItemService.add()` method doesn't validate that the item category has been provided
  * The `ItemService.add()` method doesn't correctly validate the $/Kg field value
    * If the category is edible, medicinal, validate that the $/kr is between $0.01 and $7500.00
    * If the category is inedible, poisonous, validate that the $/kg is $0
  * The ItemFileRepository doesn't protect against the delimiter being added in the `Name` field
* [ ] Domain and Data Layer Testing (new code only)
  * Are both the happy and unhappy paths fully tested?
* [ ] Spring DI
* [ ] File Formats Unaltered
* [ ] Proper BigDecimal/LocalDate Usage
* [ ] Java Idioms (excellent layering, class design, method responsibilities, and naming)
* [ ] Pattern Awareness (complete understanding of patterns: repository, service, MVC)

## Test Plan

* [ ] On startup, the application displays a menu containing at least the following items:
  * Exit
  * View Forages By Date
  * View Foragers By State
  * View Items
  * Add a Forage
  * Add a Forager
  * Add an Item
  * Report: Kilograms of Item
  * Report: Item Category Value
* [ ] **Add a Forager** with the following information:
  * First Name: Test
  * Last Name: McTest
  * State: MN
  * Does the application display a success message on the creation of the Forager?
  * Does the application return to the main menu?
  * Open `foragers.csv`. Is the newly added forager present in the file? 
* [ ] **Add a Forager** with the same information as above
  * Does the application display an error message that the forager is a duplicate?
  * Does the application return to the main menu?
  * Open `foragers.csv`. Is the duplicate forager present in the file?
* [ ] **View Foragers By State** for MN (or **View Foragers By Last Name** for McTest)
  * Does the application display the newly added forager?
* [ ] **Add an Item** with the following information:
  * Name: A,B
  * Category: Inedible
  * Price/Kg: 1
  * Does the application do one of the following:
    * Display an error message that Inedible & Poisonous items cannot have a value > 0
    * Add the item successfully but with the value of 0
  * Does the application return to the main menu?
* [ ] **Add a Forage** with the following information:
  * Forager: McTest, Test
  * Item Category: Edible
  * Item: Ramps
  * Forage Date: Today + 1 Day
  * Kilograms: 1
  * Does the application display an error message that prevents future forages?
* [ ] **Add a Forage** with the following information:
  * Forager: McTest, Test
  * Item Category: Edible
  * Item: Ramps
  * Forage Date: Today
  * Kilograms: 0
  * Does the application display an error message that prevents 0 or negative forage sizes?
* [ ] **Add a Forage** with the following information:
  * Forager: McTest, Test
  * Item Category: Edible
  * Item: Ramps
  * Forage Date: Today
  * Kilograms: 1
  * Does the forage successfully add?
  * Have the student open the `forage_data` folder. Is there a new file named `[today's date].csv`?
  * Does that new file have the newly added forage in it?
* [ ] **Add a Forage** with the same information that was just successfully added
  * Does the application properly handle duplicate forages (i.e. rejecting them or updatig the existing forage)?
* [ ] **View Forages By Date** for Today's Date
  * Does the application display 1 forage?
* [ ] **Report: Kilograms of Item** for date 06/26/2020
  * Here is a sampling of data from the original files provided (Note that some of the values may be different if the student added forages for the date in question, but the majority should line up):
  ```
  Blackberry: 8.85 kg
  Cattail: 6.86 kg
  Chanterelle: 1.94 kg
  Chicken of the Woods Mushroom: 1.03 kg
  Chicory: 20.91 kg
  Dandelion: 7.41 kg
  Duckweed: 13.99 kg
  ```
* [ ] **Report: Item Category Value** for date 06/26/2020
  * Here is the output from the original files provided (Note that some of the values may be different if the student added forages for the date in question, but the majority should line up):
  ```
  EDIBLE: $323.06
  MEDICINAL: $141.68
  INEDIBLE: $0.00
  ```