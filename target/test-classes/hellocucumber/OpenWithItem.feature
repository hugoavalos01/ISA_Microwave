Feature: Opened full microwave

  
  Scenario: Remove food from the microwave
    Given opened full microwave
    When remove food
    Then microwave is empty
    
   Scenario: Close an opened microwave
    Given opened full microwave
    When close the door
    Then door closes
    And light turns off
    And heating off
    And turnable not turns
    
   Scenario: Reset power
    Given opened full microwave
   	When reset power
   	Then power is zero
    
   Scenario: Reset timer
    Given opened full microwave
    When reset timer
    Then timer is zero

	Scenario: Try to cook
    Given opened full microwave
    When set the power to 5
    And set the timer to 5
    And start cooking
    Then microwave doesnt start cooking
  
  Scenario Outline: Set timer
    Given opened full microwave
    When set the timer to <a>
    Then display shows "<b>"

    Examples: 
      | a   | b   |
      |  -5 |   0 |
      |   0 |   0 |
      |   5 |   5 |

   Scenario Outline: Set power
    Given opened full microwave
    When set the power to <a>
    Then display shows "<b>"

    Examples: 
      | a   | b   |
      |  -5 |   0 |
      |   0 |   0 |
      |   5 |   5 |
      