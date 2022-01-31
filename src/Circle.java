import java.awt.*;
import java.awt.geom.*;
import java.awt.Polygon;
import javax.swing.*;
public class Circle extends AnimationObject 
{
	Vector2d Velocity = new Vector2d(0, 0);
	double Radius;
	double RadiusSqrd;
	Color FillColor;
	Color LineColor;
	Ellipse2D.Double Circle;
	double VisualBorderSize = 5;    // 5 additional pixels to radius for "better look"
	
	Circle(Vector2d Pos, Vector2d Vel, double R, Color Fill, Color Line)
	{
		//Position = Pos;
		Velocity = Vel;
		Radius    = R;
		RadiusSqrd = R*R;
		FillColor = Fill;
		LineColor = Line;
		Circle = new Ellipse2D.Double(Pos.X - VisualBorderSize, Pos.Y - VisualBorderSize, 
				VisualBorderSize + 2*Radius + VisualBorderSize, 
				VisualBorderSize + 2*Radius + VisualBorderSize); 
	}
	
	public Vector2d Position()
	{
		return new Vector2d(Circle.x + VisualBorderSize + Radius, 
				Circle.y + VisualBorderSize + Radius);
	}
	
	public void SetPosition(double x, double y)
	{
		Circle.x = x - Radius - VisualBorderSize;
		Circle.y = y - Radius - VisualBorderSize;
	}
	
	public void SetPosition(Vector2d Pos)
	{
		Circle.x = Pos.X - Radius - VisualBorderSize;
		Circle.y = Pos.Y - Radius - VisualBorderSize;
	}
	
	@Override
	public void Update(double elapsedFrameTime) 
	{
		Circle.x += Velocity.X*elapsedFrameTime;
		Circle.y += Velocity.Y*elapsedFrameTime;
	}

	
	
	@Override
	public void Draw(Graphics graphics) 
	{
		Graphics2D g2 = (Graphics2D) graphics;
		
		//Shape circle = new Ellipse2D.Double(Position.X - 5, Position.Y - 5, 
		//		2*Radius + 5*2, 2*Radius + 5*2);
		g2.setColor(Color.RED);
		g2.fill(Circle);
		Shape circle3 = new Ellipse2D.Double(Circle.x + 5, Circle.y + 5, 
				2*Radius, 2*Radius);
		g2.setColor(Color.ORANGE);
		g2.fill(circle3);
		
		Shape circle2 = new Ellipse2D.Double(Circle.x + 5 + Radius, Circle.y + 5 + Radius, 5, 5);
		g2.setColor(Color.BLUE);
		g2.fill(circle2);

	}
	
	public boolean IsIntersectingRectangle(Vector2d[] CarVertices)
	{	
		//Ellipse2D.Double circle = new Ellipse2D.Double(Position.X, Position.Y, Radius, Radius);
		return false;
	}

	public boolean IsPointWithin(Vector2d Point) 
	{
		Vector2d DistVector = new Vector2d(Circle.x + Radius, Circle.y + Radius).sub(Point);
		return (DistVector.lenSqrd() <= RadiusSqrd);
	}

}
