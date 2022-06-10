package microwave;
import org.junit.jupiter.api.*;

public class Test_Microwave {

		private Microwave m;
		
		@BeforeEach
		void init() {
			m = new Microwave();
		}

		
		/*
		 * Comprobamos los componentes del microondas
		 */
		
		@Test
		void testBeeper() {
			
			Beeper b = new Beeper();
			
			// Comprobamos que escucha los beeps
			b.beep(3);
			Assertions.assertTrue(beepListener.times(3));
			
			// Comprobamos que se reinicia a 0
			Assertions.assertTrue(beepListener.times(0));
		}
		@Test
		void testDisplay() {
			
			Display d = new Display();
			
			// Comprobamos que se inicia sin ningun mensaje
			Assertions.assertEquals(null, d.getDisplay());

			
			// Comprobamos que cambia el mensaje
			d.setDisplay("Mensaje");
			Assertions.assertEquals("Mensaje", d.getDisplay());
			
			//Comprobamos que el mensaje se borra
			d.clearDisplay();
			Assertions.assertEquals(null, d.getDisplay());

		}
		
		
		@Test
		void testTimer() {
			
			// Comprobamos que se inicia en 0
			Assertions.assertEquals(0, m.getTimer());
			
			// Comprobamos que incrementa el tiempo
			m.timer_inc();
			Assertions.assertEquals(1, m.getTimer());
			
			// Comprobamos que decrementa el tiempo
			m.timer_inc();
			m.timer_desc();
			Assertions.assertEquals(1, m.getTimer());
			
			//Comprobamos que el display muestra los cambios de tiempo
			Assertions.assertEquals("1", m.getDisplayConnection().getDisplay());

			// Comprobamos que se reinicia el tiempo
			m.timer_reset();
			Assertions.assertEquals(0, m.getTimer());
			
			// Comprobamos que no baja de 0
			m.timer_desc();
			Assertions.assertEquals(0, m.getTimer());
			
		}
		
		@Test
		void testPower() {
			
			// Comprobamos que se inicia en 0
			Assertions.assertEquals(0, m.getPower());
			
			// Comprobamos que incrementa la potencia
			m.power_inc();
			Assertions.assertEquals(1, m.getPower());
			
			// Comprobamos que decrementa la potencia
			m.power_inc();
			m.power_desc();
			Assertions.assertEquals(1, m.getPower());
			
			//Comprobamos que el display muestra los cambios de potencia
			Assertions.assertEquals("1", m.getDisplayConnection().getDisplay());

			// Comprobamos que se reinicia la potencia
			m.power_reset();
			Assertions.assertEquals(0, m.getPower());
			
			// Comprobamos que no baja de 0
			m.power_desc();
			Assertions.assertEquals(0, m.getPower());

		}
		
		@Test
		void turnableTest() {
			
			Turnable t = new Turnable();

			// Comprobamos que se inicia parado
			Assertions.assertFalse(t.isMoving());

			// Comprobamos que gira y se para correctamente
			t.turnable_start();
			Assertions.assertTrue(t.isMoving());
			
			t.turnable_stop();
			Assertions.assertFalse(t.isMoving());

		}
		
		@Test
		void heatingTest() {
			
			Heating h = new Heating();

			// Comprobamos que se inicia con potencia 0 y sin calentar
			Assertions.assertFalse(h.isHeating());
			Assertions.assertEquals(0, h.getPower());

			// Comprobamos que se enciende y se apaga
			h.heatingOn();
			Assertions.assertTrue(h.isHeating());
			
			h.heatingOff();
			Assertions.assertFalse(h.isHeating());
			
			// Comprobamos que podemos ajustar la potencia
			h.setPower(5);
			Assertions.assertEquals(5, h.getPower());

		}
		
		@Test
		void lampTest() {
			
			Lamp l = new Lamp();

			// Comprobamos que se inicia apagada
			Assertions.assertFalse(l.isLampOn());
			
			// Comprobamos que se enciende y apaga
			l.lampOn();
			Assertions.assertTrue(l.isLampOn());
			
			l.lampOff();
			Assertions.assertFalse(l.isLampOn());

			
		}
		
		/*
		 * Comprobamos los estados del microondas
		 */
		
		@Test
		void testOpenWithNoItem() {
			
			m.setEstado(new OpenWithNoItem(m));
			Assertions.assertTrue(m.getEstado() instanceof OpenWithNoItem);
			
			// Comprobamos que se inicia correctamente
			Assertions.assertFalse(m.isWithItem());
			Assertions.assertTrue(m.isDoorOpen());
			Assertions.assertFalse(m.isCooking());
			
			Assertions.assertFalse(m.getHeatingConnection().isHeating());
			Assertions.assertTrue(m.getLampConnection().isLampOn());
			Assertions.assertFalse(m.getTurnableConnection().isMoving());
			
			// Comprobamos que se puede modificar el tiempo y la potencia
			testPower();
			testTimer();
			
			// Comprobamos las excepciones
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_stop());
			Assertions.assertThrows(IllegalStateException.class, () -> m.item_removed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.door_opened());
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_start());
			Assertions.assertThrows(IllegalStateException.class, () -> m.tick());
			
			// Comprobamos que podemos cerrar la puerta y cambia de estado
			m.door_closed();
			Assertions.assertTrue(m.getEstado() instanceof ClosedWithNoItem);
						
			// Comprobamos que si metemos comida, cambia de estado
			m.setEstado(new OpenWithNoItem(m));
			m.item_placed();
			Assertions.assertTrue(m.getEstado() instanceof OpenWithItem);
		}
		
		@Test
		void testClosedWithNoItem() {
			
			m.setEstado(new ClosedWithNoItem(m));
			Assertions.assertTrue(m.getEstado() instanceof ClosedWithNoItem);
			
			// Comprobamos que se inicia correctamente
			Assertions.assertFalse(m.isWithItem());
			Assertions.assertFalse(m.isDoorOpen());
			Assertions.assertFalse(m.isCooking());
			
			Assertions.assertFalse(m.getHeatingConnection().isHeating());
			Assertions.assertFalse(m.getLampConnection().isLampOn());
			Assertions.assertFalse(m.getTurnableConnection().isMoving());
			
			// Comprobamos que se puede modificar el tiempo y la potencia
			testPower();
			testTimer();
			
			// Comprobamos las excepciones
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_stop());
			Assertions.assertThrows(IllegalStateException.class, () -> m.item_removed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.item_placed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.door_closed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_start());
			Assertions.assertThrows(IllegalStateException.class, () -> m.tick());
			
			// Comprobamos que podemos abrir la puerta y cambia de estado
			m.door_opened();
			Assertions.assertTrue(m.getEstado() instanceof OpenWithNoItem);
			
		}
		
		@Test
		void testOpenWithItem() {
			
			m.setEstado(new OpenWithItem(m));
			Assertions.assertTrue(m.getEstado() instanceof OpenWithItem);
			
			// Comprobamos que se inicia correctamente
			Assertions.assertTrue(m.isWithItem());
			Assertions.assertTrue(m.isDoorOpen());
			Assertions.assertFalse(m.isCooking());
			
			Assertions.assertFalse(m.getHeatingConnection().isHeating());
			Assertions.assertTrue(m.getLampConnection().isLampOn());
			Assertions.assertFalse(m.getTurnableConnection().isMoving());
			
			// Comprobamos que se puede modificar el tiempo y la potencia
			testPower();
			testTimer();
			
			// Comprobamos las excepciones
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_stop());
			Assertions.assertThrows(IllegalStateException.class, () -> m.item_placed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.door_opened());
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_start());
			Assertions.assertThrows(IllegalStateException.class, () -> m.tick());
			
			// Comprobamos que podemos cerrar la puerta y cambia de estado
			m.door_closed();
			Assertions.assertTrue(m.getEstado() instanceof ClosedWithItem);
						
			// Comprobamos que si sacamos comida, cambia de estado
			m.setEstado(new OpenWithItem(m));
			m.item_removed();
			Assertions.assertTrue(m.getEstado() instanceof OpenWithNoItem);
		}
		
		@Test
		void testClosedWithItem() {
			
			m.setEstado(new ClosedWithItem(m));
			Assertions.assertTrue(m.getEstado() instanceof ClosedWithItem);
			
			// Comprobamos que se inicia correctamente
			Assertions.assertTrue(m.isWithItem());
			Assertions.assertFalse(m.isDoorOpen());
			Assertions.assertFalse(m.isCooking());
			
			Assertions.assertFalse(m.getHeatingConnection().isHeating());
			Assertions.assertFalse(m.getLampConnection().isLampOn());
			Assertions.assertFalse(m.getTurnableConnection().isMoving());
			
			// Comprobamos que se puede modificar el tiempo y la potencia
			testPower();
			testTimer();
			
			// Comprobamos las excepciones
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_stop());
			Assertions.assertThrows(IllegalStateException.class, () -> m.item_removed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.item_placed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.door_closed());
			Assertions.assertThrows(IllegalStateException.class, () -> m.tick());
			
			// Comprobamos que podemos abrir la puerta y cambia de estado
			m.door_opened();
			Assertions.assertTrue(m.getEstado() instanceof OpenWithItem);
			
			// Comprobamos que lo podemos poner a cocinar si tiene la potencia
			// y tiempo definido, sino salta una excepcion
			m.setEstado(new ClosedWithItem(m));
			Assertions.assertThrows(IllegalStateException.class, () -> m.cooking_start());
			
			m.setPower(1);
			m.setTimer(1);
			m.cooking_start();
			Assertions.assertTrue(m.getEstado() instanceof Cooking);
		}
		
		@Test
		void testCooking() {
			
			m.setEstado(new ClosedWithItem(m));
			m.setPower(10);
			m.setTimer(10);
			m.cooking_start();
			Assertions.assertTrue(m.getEstado() instanceof Cooking);
			
			// Comprobamos que se inicia correctamente
			Assertions.assertTrue(m.isWithItem());
			Assertions.assertFalse(m.isDoorOpen());
			Assertions.assertTrue(m.isCooking());
			
			Assertions.assertTrue(m.getHeatingConnection().isHeating());
			Assertions.assertTrue(m.getLampConnection().isLampOn());
			Assertions.assertTrue(m.getTurnableConnection().isMoving());
			
			// Comprobamos que si el tiempo se reinicia para y cambia de estado
			m.timer_reset();
			Assertions.assertFalse(m.isCooking());
			Assertions.assertTrue(m.getEstado() instanceof ClosedWithItem);
			
			// Comprobamos que si la potencia se reinicia 0 para y cambia de estado
			m.setTimer(10);
			m.cooking_start();
			m.power_reset();
			Assertions.assertFalse(m.isCooking());
			Assertions.assertTrue(m.getEstado() instanceof ClosedWithItem);
			
			// Comprobamos que podemos cambiar la potencia y el tiempo mientras cocina
			m.setTimer(1);
			m.setPower(10);
			m.cooking_start();
			
			m.power_desc();
			Assertions.assertEquals(9, m.getPower());

			m.power_inc();
			Assertions.assertEquals(10, m.getPower());

			m.timer_inc();
			Assertions.assertEquals(2, m.getTimer());
			
			m.timer_desc();
			Assertions.assertEquals(1, m.getTimer());

			// Comprobamos que si el tiempo llega a 0 se para y cambia de estado
			m.timer_desc();
			Assertions.assertFalse(m.isCooking());	
			Assertions.assertTrue(beepListener.times(3));
			
			// Comprobamos que al abrir la puerta se para y cambia de estado
			m.setEstado(new Cooking(m));
			m.door_opened();
			Assertions.assertFalse(m.isCooking());
			Assertions.assertTrue(m.getEstado() instanceof OpenWithItem);
			
		}
}
