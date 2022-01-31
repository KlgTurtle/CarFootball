
public class CollisionResolverFactory {
	
	public static CollisionResolver GetCollisionResolver(AnimationObject first, AnimationObject second)
	{
		if ((first.getClass().getName() == "Circle" && second.getClass().getName() == "Car") ||
			(first.getClass().getName() == "Car" && second.getClass().getName() == "Circle"))	
		{
			return new CarCircleCollisionResolver();
		}
		else
		{
			return null;
		}
	}
}
