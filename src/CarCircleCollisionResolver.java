import java.awt.geom.Line2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class CarCircleCollisionResolver extends CollisionResolver {

	
	

	@Override
	public void ResolveCollision(AnimationObject first, AnimationObject second) 
	{
		Car GameCar = null;
		Circle Ball = null;
		if (first.getClass().getName() == "Car")
		{
			GameCar =  (Car)first;
			Ball = (Circle)second;
		}
		else
		{
			GameCar =  (Car)second;
			Ball = (Circle)first;
		}
		
		if (Ball.Circle.getBounds().intersects(GameCar.GetRectangle().getBounds()))
		{
			System.out.println("Collision maybe!! found");
			HandleCollision(GameCar, Ball);
		}
		
	}

	private void HandleCollision(Car GameCar, Circle Ball) 
	{
		Vector2d CarVertices[] = GameCar.GetCarVertex();
		System.out.println("Start Edge response");
		for (int i = 0; i < 4; ++i)
		{
			Vector2d p1 = CarVertices[i % 4];
			Vector2d p2 = CarVertices[(i + 1) % 4];
			if (p1.X > p2.X)
			{
				Vector2d temp = p1;
				p1 = p2;
				p2 = temp;
			}
			
			p1 = p1.sub(Ball.Position());
			p2 = p2.sub(Ball.Position());
			
			double denominator = (p2.X - p1.X) != 0 ? (p2.X - p1.X) : 0.00001; 
			// define variables for y = mx + b
			double m = (p2.Y - p1.Y) / denominator;
			double b = ((p1.Y*p2.X) - (p1.X*p2.Y)) / denominator;
			double r2 = Ball.RadiusSqrd;
			double r  = Ball.Radius;
			
			double discriminant = m*m*r2 + r2 - b*b;
			if (discriminant >= 0)
			{
				System.out.println(">=0");
				double SqrtDiscriminant = Math.sqrt(discriminant);
				double x1 = (-m*b + SqrtDiscriminant)/(1+m*m);
				double x2 = (-m*b - SqrtDiscriminant)/(1+m*m);
				
				// Check that intersection occurs on the line segment
				if ((x1 >= p1.X && x1 <= p2.X) || (x2 >= p1.X && x2 <= p2.X))
				{
					
					if (discriminant > 0)
					{
						Vector2d FirstIntersectionPt;
						Vector2d MidPoint; 
						if ((x1 >= p1.X && x1 <= p2.X))
						{
							FirstIntersectionPt = new Vector2d(x1, m*x1+b);
							if ((x2 >= p1.X && x2 <= p2.X))
							{
								Vector2d SecondIntersectionPt = new Vector2d(x2, m*x2+b);
								MidPoint = FirstIntersectionPt.add(SecondIntersectionPt).scale(0.5);
							}
							else
							{
								MidPoint = FirstIntersectionPt;
							}
						}
						else
						{
							MidPoint = new Vector2d(x2, m*x2+b);
						}
						
						System.out.println(">0");
						
						
						
						
						double PushVectorSize = Math.max(1, Math.abs(r - MidPoint.length()));
						Vector2d PushVector = new Vector2d(0,0).sub(MidPoint).normalize().scale(PushVectorSize);
						
						Ball.SetPosition(Ball.Position().add(PushVector));
						
					}
					else // discriminant == 0
					{
						System.out.println("==0");
						Vector2d ContactPoint = new Vector2d(x1, m*x1 + b);
						double PushVectorSize = Math.max(1, Math.abs(r - ContactPoint.length()));
						Vector2d PushVector = ContactPoint.normalize().scale(PushVectorSize);
						Ball.SetPosition(Ball.Position().add(PushVector));
					}
					break;
				}
			}	
		}	
	}

	

}
