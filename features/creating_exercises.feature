Feature: Creating Exercises
  Scenario: Creating a new exercise
    Given I am on the workouts list
    When I try to create a new exercise that doesn't exist
    Then I should see a confirmation message

  Scenario: Creating a new exercise when a duplicate exists
    Given I am on the workouts list
    When I try to create a new exercise that already exists
    Then I should see an error message