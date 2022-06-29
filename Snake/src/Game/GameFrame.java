/*BY MARTINS BABAJIDE 
 * 100709716
 * REFERENCED FROM BroCode
 * 		Changes and updates Done  by Martins Babajide
 */


package Game;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	//create a constructor for the game frame class
	GameFrame(){
		
		this.add(new GamePanel());
		//adds title of the game
		this.setTitle("DiscoSnake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//adds component to the JFrame
		this.pack();
		this.setVisible(true);
		//sets game to the middle of the screen
		this.setLocationRelativeTo(null);
	}
}