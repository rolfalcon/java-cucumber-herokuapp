@predefined
  Feature: Smoke steps

  @predefined1
  Scenario: Predefined steps for Google
    Given I open url "https://google.com"
    Then I should see page title as "Google"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "//*[@name='btnK']"
    Then I wait for element with xpath "//*[@id='ires']" to be present
    Then element with xpath "//*[@id='ires']" should contain text "Cucumber"

  @predefined2
  Scenario: Predefined steps for Yahoo
    Given I open url "https://www.yahoo.com"
    Then I should see page title as "Yahoo"
    Then element with xpath "//input[@id='uh-search-box']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='uh-search-box']"
    Then I click on element with xpath "//button[@id='uh-search-button']"
    Then I wait for element with xpath "//div[@id='web']" to be present
    Then element with xpath "//div[@id='web']" should contain text "Cucumber"

  @predefined3
  Scenario: Predefined steps for Bing
    Given I open url "https://www.bing.com"
    Then I should see page title as "Bing"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element with xpath "//input[@name='go']"
    Then I wait for element with xpath "//*[@id='b_results']" to be present
    Then element with xpath "//*[@id='b_results']" should contain text "Cucumber"

  @predefined4
  Scenario: Predefined steps for Gibiru
    Given I open url "http://gibiru.com"
    Then I should see page title as "Gibiru - Uncensored Anonymous Search"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element with xpath "//i[contains(@class,'fa-search')]"
    Then I wait for element with xpath "//div[@class='gsc-resultsbox-visible']" to be present
    Then element with xpath "//div[@class='gsc-resultsbox-visible']" should contain text "Cucumber"

  @predefined5
  Scenario: Predefined steps for DuckDuckGo
    Given I open url "https://duckduckgo.com"
    Then I should see page title as "DuckDuckGo — Privacy, simplified."
    Then element with xpath "//input[@id='search_form_input_homepage']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='search_form_input_homepage']"
    Then I click on element with xpath "//input[@id='search_button_homepage']"
    Then I wait for element with xpath "//div[@id='links']" to be present
    Then element with xpath "//div[@id='links']" should contain text "cucumber"

  @predefined6
  Scenario: Predefined steps for Swisscows
    Given I open url "https://swisscows.com"
    Then I should see page title as "Swisscows - secure search engine"
    Then element with xpath "//input[@name='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='query']"
    Then I click on element with xpath "//button[@class='search-submit']"
    Then I wait for element with xpath "//article[1]" to be present
    Then element with xpath "//div[contains(@class,'web-results ')]" should contain text "cucumber"

  @predefined8
  Scenario: Predefined steps for Startpage
    Given I open url "https://www.startpage.com"
    Then I should see page title as "Startpage.com - The world's most private search engine"
    Then element with xpath "//input[@id='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='query']"
    Then I click on element with xpath "//button[@class='search-form__button']"
    Then I wait for element with xpath "//ol[@class='list-flat']" to be present
    Then element with xpath "//ol[@class='list-flat']" should contain text "Cucumber"

  @predefined9
  Scenario: Predefined steps for Yandex
    Given I open url "https://www.yandex.com"
    Then I should see page title as "Yandex"
    Then element with xpath "//input[@id='text']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='text']"
    Then I click on element with xpath "//button[@type='submit']"
    Then I wait for element with xpath "//div[@class='main__content']" to be present
    Then element with xpath "//div[@class='main__content']" should contain text "cucumber"

  @predefined10
  Scenario: Predefined steps for Boardreader
    Given I open url "http://boardreader.com"
    Then I should see page title as "Boardreader - Forum Search Engine"
    Then element with xpath "//input[@id='title-query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='title-query']"
    Then I click on element with xpath "//button[@id='title-submit']"
    Then I wait for element with xpath "//li[1]" to be present
    Then element with xpath "//div[contains(@class,'searchResultsBlock')]" should contain text "Cucumber"

  @predefined11
  Scenario: Predefined steps for Ecosia
    Given I open url "https://www.ecosia.org"
    Then I should see page title as "Ecosia - the search engine that plants trees"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "//button[@type='submit']"
    Then I wait for element with xpath "//div[contains(@class,'card-web')]" to be present
    Then element with xpath "//div[contains(@class,'card-web')]" should contain text "cucumber"

  @predefined12
  Scenario: Predefined steps for Ebay
    Given I open url "https://www.ebay.com"
    When I type "Morgan Dollar" into element with xpath "//input[@id='gh-ac']"
    And I click on element with xpath "//input[@id='gh-btn']"
    Then I wait for element with xpath "//div[@class='srp-controls--selected-value']" to be present
    And I mouse over element with xpath "//div[@class='srp-controls--selected-value']"
    Then I click on element with xpath "//span[contains(text(),'lowest first')]"
    Then element with xpath "//ul[contains(@class,'srp-results')]" should contain text "1921-S"
