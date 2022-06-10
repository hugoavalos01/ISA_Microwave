Feature: microwave cooking

  Scenario: start cooking
    Given closed full microwave
    When set the power to 5
    And set the timer to 5
    And start cooking
    Then microwave starts cooking
    And light turns on
    And heating on
    And turnable turns
    
  Scenario: increase power
    Given cooking microwave with 5 power and 5 time
    When increase power
    Then display shows "6"

  Scenario: decrease power
    Given cooking microwave with 5 power and 5 time
    When decrease power
    Then display shows "4"
    
   Scenario: increase timer
    Given cooking microwave with 5 power and 5 time
    When increase timer
    Then display shows "6"

  Scenario: decrease timer
    Given cooking microwave with 5 power and 5 time
    When decrease timer
    Then display shows "4"  
    
  Scenario: stop cooking after resetting the power
    Given cooking microwave with 5 power and 5 time
    When reset power
    Then microwave isnt cooking
		And heating off
    And light turns off
    And turnable not turns
    And display shows "0"
    
   
  Scenario: stop cooking after resetting the timer
    Given cooking microwave with 5 power and 5 time
    When reset timer
    Then microwave isnt cooking
		And heating off
    And light turns off
    And turnable not turns
    And display shows "0"
    
  Scenario: stop cooking if the door is opened
    Given cooking microwave with 5 power and 5 time
    When open the door
    Then door opens
    And microwave isnt cooking
		And heating off
    And light turns on
    And turnable not turns

  Scenario Outline: cooking stop if timer finishes
    Given closed full microwave
    When set the power to <a> 
    And set the timer to <b>
    And start cooking
    And timer goes down <c> seconds
    Then microwave isnt cooking
    And light turns off
    And turnable not turns
    And heating off
    And beeper sounds 3 times
    And display shows "Listo"

    Examples: 
      | a   | b   | c   |
      |  10 |   5 |   5 |
      |  10 | 100 | 100 |
      |  10 |  15 |  15 |