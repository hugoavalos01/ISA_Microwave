package microwave;
public class Microwave {
	
	private boolean doorOpen;
	private Integer power;
	private Integer timer;
	private boolean cooking;
	private boolean withItem;
	private Estado estado;
	private Heating heatingConnection = new Heating();
	private Lamp lampConnection = new Lamp();
	private Turnable turnableConnection = new Turnable();
	private Beeper beeperConnection = new Beeper();
	private Display displayConnection = new Display();
	
	//Constructor 
	
	public Microwave() {
		
		doorOpen = false;
		cooking = false;
		withItem = false;
		power = 0;
		timer = 0;
		estado = new ClosedWithNoItem(this);

	}
	
	//Metodos
	
	public void door_opened() {
		estado.door_opened(this);
	}
	
	public void door_closed() {
		estado.door_closed(this);
	}

	public void item_placed() {
		estado.item_placed(this);
	}

	public void item_removed() {
		estado.item_removed(this);
	}

	public void power_inc() {
		power++;
		displayConnection.setDisplay(Integer.toString(power));
	}

	public void power_desc() {
		estado.power_desc(this);
	}

	public void power_reset() {
		estado.power_reset(this);
		displayConnection.setDisplay(Integer.toString(power));
	}

	public void timer_inc() {
		timer++;
		displayConnection.setDisplay(Integer.toString(timer));
	}

	public void timer_desc() {
		estado.timer_desc(this);
	}

	public void timer_reset() {
		estado.timer_reset(this);
		displayConnection.setDisplay(Integer.toString(timer));
	}

	public void cooking_start() {
		estado.cooking_start(this);
	}

	public void cooking_stop() {
		estado.cooking_stop(this);
	}

	public void tick() {
		estado.tick(this);
	}
	
	// Getters y Setters
	
	public boolean isDoorOpen() {
		return doorOpen;
	}
	
	public void setDoorOpen(boolean doorOpen) {
		this.doorOpen = doorOpen;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int p) {
		power = p;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int t) {
		timer = t;
	}

	public boolean isCooking() {
		return cooking;
	}

	public void setCooking(boolean c) {
		cooking = c;
	}

	public boolean isWithItem() {
		return withItem;
	}

	public void setWithItem(boolean withItem) {
		this.withItem = withItem;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado e) {
		estado = e;
	}

	public Heating getHeatingConnection() {
		return heatingConnection;
	}

	public Lamp getLampConnection() {
		return lampConnection;
	}

	public Turnable getTurnableConnection() {
		return turnableConnection;
	}

	public Beeper getBeeperConnection() {
		return beeperConnection;
	}

	public Display getDisplayConnection() {
		return displayConnection;
	}
	
	
}
