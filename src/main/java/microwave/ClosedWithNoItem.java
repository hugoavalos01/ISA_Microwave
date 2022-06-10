package microwave;

public class ClosedWithNoItem implements Estado {

	public ClosedWithNoItem(Microwave m) {
		// TODO Auto-generated constructor stub
		
		m.setDoorOpen(false);
		m.setWithItem(false);
		m.setCooking(false);
		
		m.getHeatingConnection().heatingOff();
		m.getTurnableConnection().turnable_stop();
		m.getLampConnection().lampOff();
	}

	@Override
	public void door_opened(Microwave m) {
		// TODO Auto-generated method stub
		m.setEstado(new OpenWithNoItem(m));
	}

	@Override
	public void door_closed(Microwave m) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Error: La puerta ya esta cerrada");

	}

	@Override
	public void item_placed(Microwave m) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Error: Hay que abrir la puerta primero");

	}

	@Override
	public void item_removed(Microwave m) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Error: Hay que abrir la puerta primero");

	}

	@Override
	public void cooking_start(Microwave m) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Error: No hay nada dentro");

	}

	@Override
	public void cooking_stop(Microwave m) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Error: El microondas no esta cocinando");

	}

	@Override
	public void timer_reset(Microwave m) {
		// TODO Auto-generated method stub
		m.setTimer(0);
	}

	@Override
	public void power_reset(Microwave m) {
		// TODO Auto-generated method stub
		m.setPower(0);
	}

	@Override
	public void tick(Microwave m) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Error: El microondas no esta cocinando");

	}

	@Override
	public void power_desc(Microwave m) {
		// TODO Auto-generated method stub
		if (m.getPower() > 0) {
			m.setPower(m.getPower() - 1);
			m.getDisplayConnection().setDisplay(Integer.toString(m.getPower()));
		}
	}

	@Override
	public void timer_desc(Microwave m) {
		// TODO Auto-generated method stub
		if (m.getTimer() > 0) {
			m.setTimer(m.getTimer() - 1);
			m.getDisplayConnection().setDisplay(Integer.toString(m.getTimer()));
		}
	}

}
