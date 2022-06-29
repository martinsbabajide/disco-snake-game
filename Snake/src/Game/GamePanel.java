/*BY MARTINS BABAJIDE 
 * 100709716
 * REFERENCED FROM BroCode
 * Changes and updates Done  by Martins Babajide
 */


package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class GamePanel extends JPanel implements ActionListener {

	//declaring all variables to be used in the code
	
	//setting the screen width
	static final int SCREEN_WIDTH = 800;
	
	//setting the screen height
	static final int SCREEN_HEIGHT = 800;
	
	//calling the unit size to determine the object size
	static final int UNIT_SIZE = 30;
	
	//setting how much the units/objects can fit on the screen 
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	
	//creating a delay for the timer, higher the number, slower the snake moves
	static int DELAY = 60;
	
	//holds the coordinate of all the body part of the snake 
	//final
	int x[] = new int[GAME_UNITS];
	//final
	int y[] = new int[GAME_UNITS];
	
	//initial amount of body parts the snake begins with
	int bodyParts = 3;
	
	//declare an integer for the apple eaten, it starts at zero
	int applesEaten;
	int appleX;
	int appleY;
	
	//check if apple is eaten
	boolean isEaten = false;
	
	//direction for the snake; snake begins going right at the start of the game
	char direction = 'R';
	boolean running = false;
	
	//setting the timer and the apples switch places randomly 
	Timer timer;
	Random random;
	
	//used for the pause option
	static boolean gameinsession = false;
	boolean text = true;
	
	//buttons to create the level of difficulty the user wants to start with
	JButton easy;
	JButton medium;
	JButton hell;
	
	GamePanel() {
		//sets game functionality
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		//sets/initializes the buttons of the level of difficulty
		easy = new JButton("Easy");
		medium = new JButton("Medium");
		hell = new JButton("Hell");
		
		this.add(easy);
		this.add(medium);
		this.add(hell);
	
		//sets the game to adapt to the call of the user;for this case,the easy level is much slower and the other difficulties are put to hide
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DELAY = 100;
				text = false;
				running = true;
				startGame();
				easy.hide();
				medium.hide();
				hell.hide();
				
			}
		});
		
		//sets the game to adapt to the call of the user;for this case,the medium level is fair and the other difficulties are put to hide
		medium.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				DELAY = 90;
				text = false;
				running = true;
				startGame();
				easy.hide();
				medium.hide();
				hell.hide();
				

				
			}
		});
		
		//sets the game to adapt to the call of the user;for this case,the hell level is much faster and the other difficulties are put to hide
		hell.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				DELAY = 40;
				text = false;
				running = true;
				startGame();
				easy.hide();
				medium.hide();
				hell.hide();
				
				
			}
		});
		
		
		
	}

	
	//creating a method to start the game, and setting different parameters
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
		direction = 'R';
		bodyParts = 3;
		repaint();
	}
	
	//the game pauses when it is in the pause session
	public void pause() {
		GamePanel.gameinsession = true;
		timer.stop();
	}

	//the game resumes from where it was stopped.
	public void resume() {
		GamePanel.gameinsession = false;
		timer.start();
	}
	

	//drawing and applying the graphic
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw (g);
		
	}
		
	//Graphic method of the graphics in the game in session
	public void draw(Graphics g) {
		if(running) {
	//setting the color and size of the apple(object)
		g.setColor(Color.red);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
	//in this for loop, this is used to set the color, and movement of the snake.
			for(int i = 0; i< bodyParts;i++) {
			if(i == 0) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
			else {
				g.setColor(new Color(45,180,0));
				g.setColor(new Color(random.nextInt(255)));
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
		//used for the text,font and feedback score the game provides.
			g.setColor(Color.yellow);
			g.setFont(new Font("Optima", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
		else if(!text){
			gameOver(g);
		}
	}
	//setting the random movement of the apple in the appropriate size of the screen.
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	//setting the movement of the snake, the snake should be able to go up,down,left,right and in the restricted border or screen using a case.
	public void move() {
		for (int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
			switch(direction) {
			case 'U':
				y[0] = y[0] - UNIT_SIZE;
				break;
			case 'D':
				y[0] = y[0] + UNIT_SIZE;
				break;
			case 'L':
				x[0] = x[0] - UNIT_SIZE;
				break;
			case 'R':
				x[0] = x[0] + UNIT_SIZE;
				break;
		}
	}
		
	
	//method for the points the user gets from the snake eating the apple.
	public boolean checkApple(int m[], int n[]) {
		x = m;
		y = n;
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			//here
			isEaten = true;	
			newApple();
		}
		return isEaten;
	}
		
	
	//check for collision
	public void checkCollisions() {
		
		//checks if head collides with its own bom9dy
		for(int i = bodyParts;i>0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		//checks if head touches border left
		if (x[0] < 0) {
			running =false;
		}
		//if head touches border right
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check for top border
		if (y[0] < 0) {
			running = false;
		}
		//checks bottom
		if (y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		//if the snake is not moving, and the game is paused, the snake stops moving
		if (!running) {
			timer.stop();
		}
	}
	
	//game over method and also the restart, score and other output the game provides the user.
	public void gameOver(Graphics g) {
		g.setColor(Color.yellow);
		g.setFont(new Font("Optima",Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/5, g.getFont().getSize()/2+10);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("Optima",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/3, SCREEN_HEIGHT/3);
		g.setFont(new Font("Optima",Font.BOLD, 30));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Press Space to Restart", (SCREEN_WIDTH - metrics1.stringWidth("Press Space to Restart"))/2, SCREEN_HEIGHT/2);
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		//stopping the timer when the snake hits the border or touches its body parts.
		if(running) {
			move();
			//x[0] = 1;
			//y[0] = 2;
			
			//checkApple('1','2');
			checkCollisions();
		}
		repaint();
	}

	
//user control of the snake using a switch to get the event and code,as the user uses the arrows o control the snake
public class MyKeyAdapter extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			//limit to 90 degrees turn 
			if(direction != 'R') {
				direction = 'L';
			}
			break;
		case KeyEvent.VK_RIGHT:
			//limit to 90 degrees turn
			if(direction != 'L') {
				direction = 'R';
			}
			break;
		case KeyEvent.VK_UP:
			//limit to 90 degrees turn
			if(direction != 'D') {
				direction = 'U';
			}
			break;
		case KeyEvent.VK_DOWN:
			//limit to 90 degrees turn
			if(direction != 'U') {
				direction = 'D';
			}
			break;
		
			//use the space bar in the game session to pause the game.
		case KeyEvent.VK_SPACE:
			if(GamePanel.gameinsession) {
				resume();
			} else {
				pause();
			}
			break;
		}
		
		//used to restart the game by calling the space method, and the game starts as normal
		if(!running&&!text) {
				if(e.getKeyChar()==KeyEvent.VK_SPACE) {
					startGame();
					for(int i=bodyParts;i>0;i--) {
						x[i] = bodyParts*-1;
						y[i] = 0;
					}
					x[0] = 0;
					y[0] = 0;
					repaint();
					applesEaten = 0;
					
			}

		}
    }

}
}

