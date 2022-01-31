import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CarController extends AbstractAction 
{

	static final public int UP_PRESSED    = 1;
	static final public int DOWN_PRESSED  = 2;
	static final public int LEFT_PRESSED  = 3;
	static final public int RIGHT_PRESSED = 4;
	
	static final public int UP_RELEASED    = 5;
	static final public int DOWN_RELEASED  = 6;
	static final public int LEFT_RELEASED  = 7;
	static final public int RIGHT_RELEASED = 8;
	
	int moveAction;
	Car gameCar;
	
	boolean Up = false;
	boolean Down = false;
	boolean Left = false;
	boolean Right = false;
	
	CarController(Car GameCar, int Action)
	{
		gameCar = GameCar;
		moveAction = Action; 
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(moveAction)
		{
		case UP_PRESSED:
			gameCar.Gas = true;		
			break;
		case DOWN_PRESSED:
			gameCar.Reverse = true;
			break;
		case LEFT_PRESSED:
			gameCar.SteerLeft = true;
			break;
		case RIGHT_PRESSED:
			gameCar.SteerRight = true;
			break;
		case UP_RELEASED:
			gameCar.Gas = false;
			break;
		case DOWN_RELEASED:
			gameCar.Reverse = false;
			break;
		case LEFT_RELEASED:
			gameCar.SteerLeft = false;
			break;
		case RIGHT_RELEASED:
			gameCar.SteerRight = false;
			break;
		default:
			break;
		}
		
		
	}

}
