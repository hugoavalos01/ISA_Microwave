package microwave;

public class Beeper {

	public void beep(int d) {
		beepListener.listen(d);
	}
}
