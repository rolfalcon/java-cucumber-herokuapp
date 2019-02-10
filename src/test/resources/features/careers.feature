@careers
Feature:  Heroukupp page



  @careers1
  Scenario: Careers scenario
    Given I open "careers" page
    And I login as "recruiter"
    Then I verify "recruiter" login
    When I create new position
    And I verify position created

  @careers2
  Scenario: Careers candidate scenario
    Given I open "careers" page
    And I apply to a new position
    Then I verify profile is created
    And I see position in my jobs

  @careers2-1
  Scenario: Careers candidate scenario using RESTAPI
    Given I open "careers" page           
    And I apply to a new position using RestAPI
    Then I verify profile is created
    And I see position in my jobs using RestAPI

  @careers3
  Scenario: Careers adds new job
    Given I open "careers" page
    And I login as "candidate"
    Then I verify "candidate" login
    When I apply for a new job
    Then I see position marked as applied
    And I see position in my jobs