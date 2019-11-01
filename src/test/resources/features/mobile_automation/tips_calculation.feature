@mobile
Business Need: Calculate Tips for Amounts

  Scenario Outline: Calculate Tips using default Percentage
    Given Tomas wants to know the total amount to pay with Tips included
    When he calculates the total amount for <amount_without_tip>
    Then he should see that the total amount is the expected one
    Examples:
      | amount_without_tip |
      | 1000               |
      | 150.52             |

  Scenario Outline: Calculate Tips using a defined Percentage
    Given Tomas wants to know the total amount to pay with Tips included
    And he has defined the tip percentage to be <percentage>%
    When he calculates the total amount for <amount_without_tip>
    Then He should see that the total amount is the expected one
    Examples:
      | percentage | amount_without_tip |
      | 10.5871    | 1000               |
      | 30         | 1540.25            |
      | 25.1       | 1000               |

