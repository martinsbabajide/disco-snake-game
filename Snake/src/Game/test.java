package Game;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;

class test {

	GamePanel gamepanel;
	@Test
	void startGame () {
		int[] m= {10, 20, 30, 40};
		int[] n= {10, 20, 30, 40};
		assertTrue(true,gamepanel.checkApple(m,n));
	}

}