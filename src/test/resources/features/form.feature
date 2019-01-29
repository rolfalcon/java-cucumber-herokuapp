@form
  Feature: Sample form with Page Object

    @form1
    Scenario: Sample form Page Object required fields
      Given I open sample page
      When I fill out sample fields
      And I submit the sample form
      Then I verify all fields