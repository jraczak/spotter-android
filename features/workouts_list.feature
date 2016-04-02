Feature: Workout list cards
  Scenario: Tapping the heavy chest workout card
    Given I am on the workouts list
    When I tap the "Heavy Chest" workout card
    Then I should see the text "You tapped Heavy Chest"

  Scenario: Tapping the heavy shoulders workout card
    Given I am on the workouts list
    When I scroll the view down
    Then I tap the "Heavy Shoulders" workout card
    Then I should see the text "You tapped Heavy Shoulders"