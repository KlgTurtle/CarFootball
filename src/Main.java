import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.EnumMap;
import java.util.Map;


public class Main {

	private static final int ScreenWidth  = 1200;
	private static final int ScreenHeight = 800;
	public static void main(String[] args) 
	{
		
		//System.out.println(new java.io.File("").getAbsolutePath());
		JFrame frame = new JFrame("Hello World");
		frame.setMinimumSize(new Dimension(ScreenWidth, ScreenHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel panel = new GamePanel();
		//JLabel label = new JLabel("Hello Very Much World", SwingConstants.CENTER);
	
		
		
	//	panel.AnimationObjects.add(new Circle(new Vector2d(500, 300),   
	//			new Vector2d(-15, 25), 30, 
	//			new Color(255,0,255), // Fill Color
	//			new Color(255,255,0))); // Line Color
	//	panel.AnimationObjects.add(new Circle(new Vector2d(100, 500), 
	//			new Vector2d(35, -55),
	//			70, new Color(0,100,255),
	//			new Color(255,100,0)));
		
		
		frame.add(panel);
		//panel.add(label);
		
		frame.pack();
		frame.setVisible(true);
		Car GameCar = null;
		try {
			GameCar = new Car(new Vector2d(10, 400), 0, panel.getSize().width, 
					panel.getSize().height);
			//GameCar.CarAngle = -Math.PI/4;
			//for (int i = 0; i < 4; ++i)
			//{
			//	System.out.println(GameCar.GetCarVertex()[i].X + " " +  GameCar.GetCarVertex()[i].Y);
			//}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.AnimationObjects.add(GameCar);
		panel.AnimationObjects.add(new Circle(new Vector2d(300, 300), 
				new Vector2d(0, 0), 70, new Color(0,0,255),
				new Color(255,255,0)));
		 int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
	      InputMap inputMap = panel.getInputMap(condition);
	      ActionMap actionMap = panel.getActionMap();
	      
	      KeyStroke UpKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
	      inputMap.put(UpKeyPressed, "up key pressed");
	      actionMap.put("up key pressed", 
	    		  new CarController(GameCar, CarController.UP_PRESSED));
	      
	      KeyStroke UpKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
	      inputMap.put(UpKeyReleased, "up key released");
	      actionMap.put("up key released", 
	    		  new CarController(GameCar, CarController.UP_RELEASED));
	      
	      KeyStroke DownKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
	      inputMap.put(DownKeyPressed, "down key pressed");
	      actionMap.put("down key pressed", 
	    		  new CarController(GameCar, CarController.DOWN_PRESSED));
	      
	      KeyStroke DownKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
	      inputMap.put(DownKeyReleased, "down key released");
	      actionMap.put("down key released", 
	    		  new CarController(GameCar, CarController.DOWN_RELEASED));
	      
	      KeyStroke LeftKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
	      inputMap.put(LeftKeyPressed, "left key pressed");
	      actionMap.put("left key pressed", 
	    		  new CarController(GameCar, CarController.LEFT_PRESSED));
	      
	      KeyStroke LeftKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
	      inputMap.put(LeftKeyReleased, "left key released");
	      actionMap.put("left key released", 
	    		  new CarController(GameCar, CarController.LEFT_RELEASED));
	      
	      KeyStroke RightKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
	      inputMap.put(RightKeyPressed, "right key pressed");
	      actionMap.put("right key pressed", 
	    		  new CarController(GameCar, CarController.RIGHT_PRESSED));
	      
	      KeyStroke RightKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
	      inputMap.put(RightKeyReleased, "right key released");
	      actionMap.put("right key released", 
	    		  new CarController(GameCar, CarController.RIGHT_RELEASED));

	}

}



