package microwave;

public class Cooking implements Estado {

	public Cooking(Microwave m) {
		// TODO Auto-generated constructor stub

		m.setDoorOpen(false);
		m.setWithItem(true);
		m.setCooking(true);

		m.getHeatingConnection().heatingOn();
		m.getTurnableConnection().turnable_start();
		m.getLampConnection().lampOn();
		m.getHeatingConnection().setPower(m.getPower());
	}

	@Override
	public void door_opened(Microwave m) {
		// TODO Auto-generated method stub
		m.setEstado(new OpenWithItem(m));
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
		throw new IllegalStateException("Error: Ya est� cocinando");

	}

	@Override
	public void cooking_stop(Microwave m) {
		// TODO Auto-generated method stub
		m.setEstado(new ClosedWithItem(m));
	}

	@Override
	public void timer_reset(Microwave m) {
		// TODO Auto-generated method stub
		m.setEstado(new ClosedWithItem(m));
		m.setTimer(0);
	}

	@Override
	public void power_reset(Microwave m) {
		// TODO Auto-generated method stub
		m.setEstado(new ClosedWithItem(m));
		m.setPower(0);
	}

	@Override
	public void tick(Microwave m) {
		// TODO Auto-generated method stub

		if (m.getTimer() > 1) {
			m.timer_desc();
		} else {
			m.timer_desc();
			m.getBeeperConnection().beep(3);
			m.getDisplayConnection().setDisplay("Listo");
			cooking_stop(m);
		}
	}

	@Override
	public void power_desc(Microwave m) {
		// TODO Auto-generated method stub

		if (m.getPower() == 0) {
			cooking_stop(m);

		} else {

			m.setPower(m.getPower() - 1);
			m.getDisplayConnection().setDisplay(Integer.toString(m.getPower()));
		}

	}

	@Override
	public void timer_desc(Microwave m) {
		// TODO Auto-generated method stub

		if (m.getTimer() == 1) {
			m.getBeeperConnection().beep(3);
			m.getDisplayConnection().setDisplay("Listo");
			cooking_stop(m);

		} else {

			m.setTimer(m.getTimer() - 1);
			m.getDisplayConnection().setDisplay(Integer.toString(m.getTimer()));

		}
	}

}
