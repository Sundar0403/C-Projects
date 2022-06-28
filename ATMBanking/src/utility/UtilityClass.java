package utility;

public class UtilityClass 
{
	public void checkString(String input) throws Exception
	{
		if(input==null || input.isEmpty())
		{
			throw new Exception("String Can't be Null or Empty :");
		}
	}
	
	public void checkObject(Object obj) throws Exception
	{
		if(obj==null)
		{
			throw new Exception("Object Can't be Null :");
		}
	}
	
}
