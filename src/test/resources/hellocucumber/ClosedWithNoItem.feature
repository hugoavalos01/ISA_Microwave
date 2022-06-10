Feature: Closed empty microwave

    
   Scenario: Open a closed microwave
    Given closed empty microwave
    When open the door
    Then door opens
    And light turns on
    And heating off
    And turnable not turns
    
   Scenario: Reset power
    Given closed empty microwave
   	When reset power
   	Then power is zero
    
   Scenario: Reset timer
    Given closed empty microwave
    When reset timer
    Then timer is zero

	Scenario: Try to cook
    Given closed empty microwave
    When set the power to 5
    And set the timer to 5
    And start cooking
    Then microwave doesnt start cooking
  
  Scenario Outline: Set the timer
    Given closed empty microwave
    When set the timer to <a>
    Then display shows "<b>"

    Examples: 
      | a   | b   |
      |  -5 |   0 |
      |   0 |   0 |
      |   5 |   5 |

   Scenario Outline: Set the power
    Given closed empty microwave
    When set the power to <a>
    Then display shows "<b>"

    Examples: 
      | a   | b   |
      |  -5 |   0 |
      |   0 |   0 |
      |   5 |   5 |
      