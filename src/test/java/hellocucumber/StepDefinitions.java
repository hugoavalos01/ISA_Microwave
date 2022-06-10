package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import microwave.Microwave;
import microwave.beepListener;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
	
	private Microwave m;

	/*
	 * Given
	 */

	@Given("closed empty microwave")
	public void closedEmpty() {
		m = new Microwave();
	}

	@Given("closed full microwave")
	public void closedFull() {
		openedFull();
		m.door_closed();
	}

	@Given("opened empty microwave")
	public void openedEmpty() {
		closedEmpty();
		m.door_opened();
	}

	@Given("opened full microwave")
	public void openedFull() {
		openedEmpty();
		m.item_placed();
	}

	@Given("cooking microwave with {int} power and {int} time")
	public void cooking(Integer power, Integer time) {
		closedFull();
		setTimer(time);
		setPower(power);
		m.cooking_start();
	}

	/*
	 * When
	 */

	@When("open the door")
	public void openDoor() {
		m.door_opened();
	}

	@When("close the door")
	public void closeDoor() {
		m.door_closed();
	}

	@When("place food")
	public void placeFood() {
		m.item_placed();
	}

	@When("remove food")
	public void removeFood() {
		m.item_removed();
	}

	@When("set the timer to {int}")
	public void setTimer(Integer t) {
		m.timer_reset();
		for (int i = 0; i < t; i++) {
			m.timer_inc();
		}
	}

	@When("set the power to {int}")
	public void setPower(Integer p) {
		m.power_reset();
		for (int i = 0; i < p; i++) {
			m.power_inc();
		}
	}

	@When("increase timer")
	public void incTimer() {
		m.timer_inc();
	}

	@When("increase power")
	public void incPower() {
		m.power_inc();
	}

	@When("decrease timer")
	public void decTimer() {
		m.timer_desc();
	}

	@When("decrease power")
	public void decPower() {
		m.power_desc();
	}

	@When("reset timer")
	public void resetTimer() {
		m.timer_reset();
	}

	@When("reset power")
	public void resetPower() {
		m.power_reset();
	}

	@When("start cooking")
	public void startCooking() {
		try {
			m.cooking_start();
		} catch (IllegalStateException ex) {
			notStartCooking();
		}
	}

	@When("timer goes down {int} seconds")
	public void timePasses(Integer t) {
		for (int i = 0; i < t; i++) {
			m.tick();
		}
	}


	/*
	 * Then
	 */

	@Then("microwave doesnt start cooking")
	public void notStartCooking() {
		Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_start());

	}
	
	@Then("microwave starts cooking")
	public void StartCooking() {
		Assertions.assertTrue(m.isCooking());
	}
	
	@Then("microwave isnt cooking")
	public void notCooking() {
		Assertions.assertFalse(m.isCooking());
	}

	@Then("door opens")
	public void doorOpens() {
		Assertions.assertTrue(m.isDoorOpen());
	}

	@Then("door closes")
	public void doorCloses() {
		Assertions.assertFalse(m.isDoorOpen());
	}

	@Then("light turns on")
	public void lightOn() {
		Assertions.assertTrue(m.getLampConnection().isLampOn());
	}

	@Then("light turns off")
	public void lightOff() {
		Assertions.assertFalse(m.getLampConnection().isLampOn());
	}

	@Then("microwave is full")
	public void isFull() {
		Assertions.assertTrue(m.isWithItem());
	}

	@Then("microwave is empty")
	public void isEmpty() {
		Assertions.assertFalse(m.isWithItem());
	}

	@Then("turnable turns")
	public void turnableT() {
		Assertions.assertTrue(m.getTurnableConnection().isMoving());
	}

	@Then("turnable not turns")
	public void turnableNT() {
		Assertions.assertFalse(m.getTurnableConnection().isMoving());
	}

	@Then("power is zero")
	public void powerZero() {
		Assertions.assertEquals("0", m.getDisplayConnection().getDisplay());
	}
	
	@Then("timer is zero")
	public void timerZero() {
		Assertions.assertEquals("0", m.getDisplayConnection().getDisplay());
	}
	
	@Then("heating on")
	public void heatingOn() {
		Assertions.assertTrue(m.getHeatingConnection().isHeating());
	}
	
	@Then("heating off")
	public void heatingOff() {
		Assertions.assertFalse(m.getHeatingConnection().isHeating());
	}
	
	@Then("display shows {string}")
	public void displayShows(String s) {
		Assertions.assertEquals(s, m.getDisplayConnection().getDisplay());
	}
	
	@Then("beeper sounds {int} times")
	public void soundTimes(Integer t) {
		Assertions.assertTrue(beepListener.times(t));
	}
}
