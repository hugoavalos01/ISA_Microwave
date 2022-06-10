Feature: Opened empty microwave

  
  Scenario: Place food in the microwave
    Given opened empty microwave
    When place food
    Then microwave is full
    
   Scenario: Close an opened microwave
    Given opened empty microwave
    When close the door
    Then door closes
    And light turns off
    And heating off
    And turnable not turns
    
   Scenario: Reset power
    Given opened empty microwave
   	When reset power
   	Then power is zero
    
   Scenario: Reset timer
    Given opened empty microwave
    When reset timer
    Then timer is zero

	Scenario: Try to cook
    Given opened empty microwave
    When set the power to 5
    And set the timer to 5
    And start cooking
    Then microwave doesnt start cooking
  
  Scenario Outline: Set timer
    Given opened empty microwave
    When set the timer to <a>
    Then display shows "<b>"

    Examples: 
      | a   | b   |
      |  -5 |   0 |
      |   0 |   0 |
      |   5 |   5 |

   Scenario Outline: Set power
    Given opened empty microwave
    When set the power to <a>
    Then display shows "<b>"

    Examples: 
      | a   | b   |
      |  -5 |   0 |
      |   0 |   0 |
      |   5 |   5 |
      