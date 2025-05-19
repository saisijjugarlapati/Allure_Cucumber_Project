Feature: Search functionality on e-commerce site

  Scenario Outline: Search for product keyword
    Given I open DuckDuckGo search engine
    When I search for "<keyword>"
    Then I should see results related to "<keyword>"

    Examples:
      | keyword |
      | Laptop  |
      | Mobile  |
