import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel 
{
	Timer timer = new Timer();
	
	public ArrayList<AnimationObject> AnimationObjects = new ArrayList<AnimationObject>();
	
	// position
	double x = 100;
	double y = 100;
	
	// speed
	double velX = 20;
	double velY = 20;
	
	long lastFrameTime = System.currentTimeMillis();
	
	GamePanel()
	{
		timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        repaint();
                    }
                }
                , 0, 1);
		
		
		
	}

	@Override
	public void paintComponent(Graphics graphics) 
	{
         super.paintComponent(graphics);
         

         long currentFrameTime = System.currentTimeMillis();
         double delta = ((double)currentFrameTime - (double)lastFrameTime)/1000;        
         lastFrameTime = currentFrameTime;
         
         for (int i = 0; i < AnimationObjects.size(); ++i)
         {
        	 
        	 AnimationObject currentObject = AnimationObjects.get(i);
        	 currentObject.Update(delta);
        	 for (int j = i + 1; j < AnimationObjects.size(); ++j)
        	 {
        		 CollisionResolver resolver = 
        				 CollisionResolverFactory.GetCollisionResolver(AnimationObjects.get(i), 
        						 AnimationObjects.get(j));
        		 resolver.ResolveCollision(AnimationObjects.get(i), AnimationObjects.get(j));
        	 }
        	 currentObject.Draw(graphics);
         }

       
	}

}
