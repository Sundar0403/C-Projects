package check;


public class Utility
{
	public void checkString(String input) throws Exception
	{
		if(input.isEmpty() || input==null)
		{
			throw new Exception("String Can't Be Null Or Empty :");
		}
	}
	
	public void checkObject(Object newObj) throws Exception
	{
		if(newObj==null)
		{
			throw new Exception("Object Can't Be Null or Empty :");
		}
	}
}
