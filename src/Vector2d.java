
public class Vector2d 
{
	public double X;
	public double Y;	

	Vector2d(double VX, double VY)
	{
		X = VX;
		Y = VY;
	}
	
	public Vector2d add(Vector2d Other)
	{
		return new Vector2d(this.X + Other.X, this.Y + Other.Y);
	}
	
	public Vector2d sub(Vector2d Other)
	{
		return new Vector2d(this.X - Other.X, this.Y - Other.Y);
	}
	
	public Vector2d scale(double ScaleFactor)
	{
		return new Vector2d(this.X * ScaleFactor, this.Y * ScaleFactor);
	}
	
	public double dot(Vector2d Other)
	{
		return (this.X*Other.X) + (this.Y*Other.Y);
	}
	
	public double length()
	{
		return Math.sqrt(this.X*this.X + this.Y*this.Y);
	}
	
	public double lenSqrd()
	{
		return this.X*this.X + this.Y*this.Y;
	}
	
	public Vector2d normalize()
	{
		double len = length();
		return new Vector2d(this.X/len, this.Y/len);
	}
	
	public Vector2d rotate(double Angle)
	{
		return new Vector2d(Math.cos(Angle)*X + Math.sin(Angle)*Y,
				           -Math.sin(Angle)*X + Math.cos(Angle)*Y);
	}
	
	public Vector2d perp()
	{
		return new Vector2d(-Y, X);
	}
	
	public boolean IsWithin(double MinX, double MaxX, double MinY, double MaxY)
	{
		return (X >= MinX && X <= MaxX && Y >= MinY && Y<= MaxY);
	}
}
