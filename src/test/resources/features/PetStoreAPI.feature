Feature: Pet Store Task

  Background:
    Given the user goes to the API

  @wip
  Scenario: Add new one pet information to the list
    When user create new pet information
      | id            | 123       |
      | category.id   | 0         |
      | category.name | Pets      |
      | name          | Asuman    |
      | photoUrls     |           |
      | tags.id       | 0         |
      | tags.name     | pet-cat   |
      | status        | available |
    Then the user verifies status code 200
    And user verifies response has id
    And user verifies created name should be equal to the posted name
    And user verifies Content-type should be "application/json"
    And user verifies Response header should has a date value

  @wip
  Scenario: Get new pets information
    When the user get request of new pets info
    Then the user verifies status code 200
    And  the user verifies response body should be equal to the first Task’s response body
    And  user verifies Content-type should be "application/json"
    And  user verifies Response header should has a date value


  Scenario: Delete new pets information
    When  the user use delete request for deleting
    Then  the user verifies status code 200
    And   user verifies Content-type should be "application/json"
    And   user verifies Response header should has a date value
    And   user verifies Message should be equal to “unknown”