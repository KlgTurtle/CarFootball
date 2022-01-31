
import java.io.*;
import java.awt.*;
import java.awt.geom.Path2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;


public class Car extends AnimationObject {

	public Vector2d Position =  new Vector2d(0, 0);
	public double Speed = 0;
	//public Vector2d Velocity =  new Vector2d(0, 0);
	//public Vector2d Direction = new Vector2d(0, -1);
	public double SteeringWheelAngle = 0;
	public double CarAngle = 0;
	final double ImageScaling = 0.2;
	double CarWidth;
	double CarHeight;
	double FieldWidth;
	double FieldHeight;
	
	public boolean Gas = false;
	public boolean Reverse = false;
	public boolean SteerRight = false;
	public boolean SteerLeft = false;
	
	BufferedImage CarImage; 
	
	Car(Vector2d Pos, double speed, double fieldWidth, double fieldHeight) throws IOException
	{
		CarImage = ImageIO.read(new File("bin\\car.png"));	
		Position = Pos; 
		Speed    = speed;
		CarWidth =  CarImage.getWidth()   * ImageScaling;
		CarHeight = CarImage.getHeight()  * ImageScaling;
		FieldWidth = fieldWidth;
		FieldHeight = fieldHeight;
	}
	
	public Vector2d GetDirVector()
	{
		return new Vector2d(Math.sin(CarAngle), -Math.cos(CarAngle));  
	}
	
	@Override
	public void Update(double elapsedFrameTime) {
		if (Gas && Speed < 300)
		{		
			Speed += 1;	
		}
		if (Reverse && Speed > -200)
		{
			Speed -= 1;
		}
		
		double SteeringFactor = Math.min(Math.abs(Speed*elapsedFrameTime), 10);
		if (Gas || Reverse)
		{
			SteeringFactor *= 3;
		}
		if (SteerLeft)
		{
			SteeringWheelAngle -= SteeringFactor;
		}
		else if (SteerRight)
		{
			SteeringWheelAngle += SteeringFactor;
		}
		else if (SteeringWheelAngle != 0)
		{
			double Amount;
			if (Gas)
			{
				Amount = -1*(SteeringWheelAngle*4)*elapsedFrameTime;
			}
			else
			{
				Amount = -1*(SteeringWheelAngle*2)*elapsedFrameTime;
			}
			if (Math.abs(Amount) > Math.abs(SteeringWheelAngle))
			{
				SteeringWheelAngle = 0;
			}
			else
			{
				SteeringWheelAngle += Amount;
			}
		}
		
		if (Math.abs(SteeringWheelAngle) > 10)
		{
			SteeringWheelAngle = 10 * Math.signum(SteeringWheelAngle);
		}
		
		
		CarAngle += Speed*SteeringWheelAngle/500000;
		
		
		
		Position = Position.add(GetDirVector().scale(Speed*elapsedFrameTime));
		//CheckAndHandleEdgeCollision();
	
	
	}
	
	public void CheckAndHandleBallCollision()
	{
		
	}
	
	public void CheckAndHandleEdgeCollision()
	{
		Vector2d[] Vertex = GetCarVertex();
		for (int i = 0; i < 4; ++i)
		{
			if (!Vertex[i].IsWithin(0, FieldWidth, 0, FieldHeight))
			{
				
				Speed = -0.7*Speed;
				break;
					
				
			}
		}
	}
	
	public Vector2d GetCarCenter()
	{
		Vector2d[] Vertex = GetCarVertex();
		Vector2d Center = new Vector2d( 
				Vertex[0].X + (Vertex[3].X - Vertex[0].X)/2, 
				Vertex[0].Y + (Vertex[3].Y - Vertex[3].Y)/2);
		
		return Center;
	}
	
	public Vector2d[] GetCarVertex()
	{
		Vector2d V2Result[] = new Vector2d[4];
		
		Vector2d RotPoint = GetRotationPoint();
		Vector2d ScaledPos = Position;
		V2Result[0] = new Vector2d(ScaledPos.X, ScaledPos.Y).sub(RotPoint);
		V2Result[1] = new Vector2d(ScaledPos.X + CarWidth, ScaledPos.Y).sub(RotPoint);
		V2Result[2] = new Vector2d(ScaledPos.X + CarWidth, ScaledPos.Y + CarHeight).sub(RotPoint);	
		V2Result[3] = new Vector2d(ScaledPos.X, ScaledPos.Y + CarHeight).sub(RotPoint);
		
		V2Result[0] = V2Result[0].rotate(-CarAngle).add(RotPoint);
		V2Result[1] = V2Result[1].rotate(-CarAngle).add(RotPoint);
		V2Result[2] = V2Result[2].rotate(-CarAngle).add(RotPoint);
		V2Result[3] = V2Result[3].rotate(-CarAngle).add(RotPoint);
		
		return V2Result;
	}
	
	public Path2D.Double GetRectangle()
	{
		Vector2d vtx[] = GetCarVertex();
		Path2D.Double CarPath = new Path2D.Double();
		CarPath.moveTo(vtx[0].X, vtx[0].Y);
		CarPath.lineTo(vtx[1].X, vtx[1].Y);
		CarPath.lineTo(vtx[2].X, vtx[2].Y);
		CarPath.lineTo(vtx[3].X, vtx[3].Y);
		CarPath.lineTo(vtx[0].X, vtx[0].Y);
		return CarPath;
	}
	
	public Vector2d GetRotationPoint()
	{
		return new Vector2d(Position.X + CarWidth/2, 
				Position.Y + CarHeight*3/4);
	}

	@Override
	public void Draw(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, (int)FieldWidth, (int)FieldHeight);
		
		Vector2d RotPoint = GetRotationPoint();
		
		Graphics2D g2 = (Graphics2D) graphics;
		g2.rotate(CarAngle, RotPoint.X , RotPoint.Y);
		
		g2.scale(ImageScaling, ImageScaling);
		
		
		graphics.drawImage(CarImage, (int)Position.X*(int)(1/ImageScaling), 
				(int)Position.Y*(int)(1/ImageScaling), null);
		
		g2.scale(1/ImageScaling, 1/ImageScaling);
		g2.rotate(-CarAngle, RotPoint.X, RotPoint.Y);
		
		Vector2d vtx[] = GetCarVertex();
		Path2D.Double CarPath = new Path2D.Double();
		g2.setColor(Color.BLACK);
		CarPath.moveTo(vtx[0].X, vtx[0].Y);
		CarPath.lineTo(vtx[1].X, vtx[1].Y);
		CarPath.lineTo(vtx[2].X, vtx[2].Y);
		CarPath.lineTo(vtx[3].X, vtx[3].Y);
		CarPath.lineTo(vtx[0].X, vtx[0].Y);
		//g2.draw(CarPath);
	}

}
