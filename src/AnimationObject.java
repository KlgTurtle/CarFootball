import javax.swing.*;
import java.awt.*;

public abstract class AnimationObject  
{
	abstract public void Update(double elapsedFrameTime);
	abstract public void Draw(Graphics graphics);
}
