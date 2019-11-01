@api
Feature: Manage employees information

  Scenario: Create a new Employee information
    Given Martin wants to register a new Employee information
    When he makes a post with the employee information
    Then the employee should be registered

  Scenario: Get employee Information
    Given Martin has registered a new Employee information
    When he makes a request to get the information of the employee
    Then he should get the correct information

  Scenario: Delete Employee Information
    Given Martin has registered a new Employee information
    When he deletes the employee information
    Then he should get a success deleted response message
    And The employee should not exists
