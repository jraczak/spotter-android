Given(/^I am on the workouts list$/) do
  # Sample step definition
  # Example: (Given I am logged in)
  #  enter_text("* marked:'username'", USERNAME)
  #  enter_text("* marked:'password'", PASSWORD)
  #  touch("* marked:'login'")
  #  wait_for_view("* text:'Welcome #{USERNAME}'")

  # Remember: any Ruby is allowed in your step definitions
  wait_for_view("* text:'Hi, Justin'")
end

When(/^I create a new exercise that doesn't exist$/) do
  tap({marked: '+ New Exercise'})
  enter_text_in({marked: 'exercise_name_text_field'}, "Barbell Bench Press")
  tap({marked: 'Save Exercise'})
end

Then(/^I should see a confirmation message$/) do
   wait_for_view({marked: 'Barbell Bench Press was added to your library'})
end

When(/^I try to create a new exercise that already exists$/) do
  tap({marked: '+ New Exercise'})
  enter_text_in({marked: 'exercise_name_text_field'}, "Barbell Bench Press")
  tap({marked: 'Save Exercise'})
end

Then(/^I should see an error message$/) do
   wait_for_view({marked: 'This exercise already exists within this muscle group'})
end

When(/^I tap the "([^\"]*)" workout card$/) do |workout_name|
  # Sample step definition
  # Example: When I create a new entry
    tap("* marked:'#{workout_name}'")
  #  enter_text("* marked:'entry_title'", 'My Entry')
  #  touch("* marked:'submit'")
end

Then(/^I should see the text "([^"]*)"$/) do |text|
  # Sample step definition
  # Example: Then I should see the entry on my home page
  wait_for_view("* text:'#{text}'")
end

Then(/^I scroll the view down$/) do
  scroll({id: 'list_workouts_scrollview'}, :down)
end