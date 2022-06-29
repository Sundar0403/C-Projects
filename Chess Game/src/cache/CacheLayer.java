package cache;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CacheLayer 
{
	Map<String,String> board=new LinkedHashMap<>();
	Map<String,String> coins=new LinkedHashMap<>();
	Scanner scan=new Scanner(System.in);
	
	public CacheLayer()
	{
		System.out.println("Enter the Number of Rows : ");
		int row=scan.nextInt();
		scan.nextLine();
		
		System.out.println("Enter the Number of Columns :");
		int col=scan.nextInt();
		scan.nextLine();
		String coin="";
		for(int i=1;i<=row;i++)
		{
			for(int j=1;j<=col;j++)
			{
				String pos=getPos(i,j);
				if(i==2)
				{
					coin="W_P";
					board.put(pos, coin);
					coins.put(coin,"White");
				}
				else if(i==1)
				{
					if(j==1 || j==8)
					{
						coin="W_R";
						board.put(pos, coin);
						coins.put(coin,"White");
					}
					if(j==2 || j==7)
					{
						coin="W_N";
						board.put(pos, coin);
						coins.put(coin,"White");
					}
					if(j==3 || j==6)
					{
						coin="W_B";
						board.put(pos, coin);
						coins.put(coin,"White");
					}
					if(j==4)
					{
						coin="W_K";
						board.put(pos, coin);
						coins.put(coin,"White");
					}
					if(j==5)
					{
						coin="W_Q";
						board.put(pos,coin);
						coins.put(coin,"White");
					}
				}
				else if(i==7)
				{
					coin="B_P";
					board.put(pos,coin);
					coins.put(coin,"Black");
				}
				else if(i==8)
				{
					if(j==1 || j==8)
					{
						coin="B_R";
						board.put(pos, coin);
						coins.put(coin,"Black");
					}
					if(j==2 || j==7)
					{
						coin="B_N";
						board.put(pos, coin);
						coins.put(coin,"Black");
					}
					if(j==3 || j==6)
					{
						coin="B_B";
						board.put(pos, coin);
						coins.put(coin,"Black");
					}
					if(j==4)
					{
						coin="B_K";
						board.put(pos, coin);
						coins.put(coin,"Black");
					}
					if(j==5)
					{
						coin="B_Q";
						board.put(pos,coin);
						coins.put(coin,"Black");
					}
				}
				else
				{
					board.put(pos,"");
				}
			}
			
		}
		System.out.println(board.toString());
	}
	
	public Map<String,String> getMap() throws Exception
	{
		return board;
	}

	private String getPos(int i, int j) 
	{
		String pos=""+i+(char)(j+64);
		return pos;
	}

	public String getCoin(String pos) 
	{
		return board.get(pos);
	}
	
	public void setCoin(String pos,String coin)
	{
		board.put(pos, coin);
	}

	public String getType(String coin) 
	{
		return coins.get(coin);
	}

	public String getBoardPos(String coin) 
	{
		for(Map.Entry<String,String> newSet : board.entrySet())
		{
			if(newSet.getValue().equals(coin))
			{
				return newSet.getKey();
			}
		}
		return "";
	}	
}
