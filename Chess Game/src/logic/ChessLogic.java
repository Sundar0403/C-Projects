package logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cache.CacheLayer;

public class ChessLogic 
{
	CacheLayer cacheObj=new CacheLayer();
	public void getPrint() throws Exception
	{
		Map<String,String> boardMap = cacheObj.getMap();
		int count=0;
		System.out.println("-*-*-* CHESS BOARD -*-*-*");
		System.out.println();
		System.out.println();
		System.out.println("    "+"A"+"    "+"B"+"    "+"C"+"    "+"D"+"    "+"E"+"    "+"F"+"    "+"G"+"    "+"H");
		int innerCount=0;
		for(String value : boardMap.values())
		{
			if(count%8==0)
			{
				innerCount++;
				System.out.println();
				System.out.print(innerCount+"  ");
			}
			if(value.equals(""))
			{
				System.out.print(value +"     ");
			}
			else
			{	
				System.out.print(value +"  ");
			}	
			count++;
		}
	}
	public List<String> isKing(String pos, String coin, String coinType)
	{
		List<String> positions=new ArrayList<>();
		System.out.println("HI");
		String arr[]=pos.split("");
		int start=Integer.parseInt(arr[0]);
		int cols=arr[arr.length-1].charAt(0)-64;
		int row=0;
		int col=0;
		for(int direc=1;direc<=8;direc++)
		{
			row=start;
			col=cols;
			if(direc==1)
			{
				row++;
				if(row<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==2)
			{
				col++;
				if(col<=8)
				{
					String temp=""+row+(char)(col+64);
					
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==3)
			{
				row--;
				if(row>0)
				{
					String temp=""+row+(char)(col+64);
					
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==4)
			{
				col--;
				if(col>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==5)
			{
				row++;
				col++;
				if(row<=8 && col<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==6)
			{
				row++;
				col--;
				if(row<=8 && col>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==7)
			{
				row--;
				col++;
				if(row>0 && col<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==8)
			{
				row--;
				col--;
				if(col>0 && row>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
		}
		return positions;
	}
	public List<String> isPawn(String pos, String coin, String coinType) 
	{
		List<String> positions=new ArrayList<>();
		String arr[]=pos.split("");
		int start=Integer.parseInt(arr[0]);
		int end=start+2;
		if(coinType.equals("White"))
		{
			if(start!=2)
			{
				end--;
			}
			for(int i=start+1;i<=end;i++)
			{
				String temp=i+arr[arr.length-1];
				if(cacheObj.getCoin(temp)=="")
				{
					positions.add(temp);
				}
				else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
				{
					break;
				}
				if(i==start+1)
				{
					char ch=(char)((int)arr[arr.length-1].charAt(0)+1);
					temp=""+i+ch;
					if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							positions.add(temp+"(Can Be Occupied)");
						}
						else
						{
							break;
						}
					}
					char ch1=(char)((int)arr[arr.length-1].charAt(0)-1);
					temp=""+i+ch1;
					if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							positions.add(temp+"(Can Be Occupied)");
						}
						else
						{
							break;
						}
					}
				}
			}
		}
		else
		{
			if(start!=7)
			{
				end++;
			}
			for(int i=start-1;i>=start-2;i--)
			{
				String temp=i+arr[arr.length-1];
				if(cacheObj.getCoin(temp)=="")
				{
					positions.add(temp);
				}
				else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
				{
					break;
				}
				if(i==start-1)
				{
					char ch=(char)((int)arr[arr.length-1].charAt(0)+1);
					temp=""+i+ch;
					if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							positions.add(temp+"(Can Be Occupied)");
						}
						else
						{
							break;
						}
					}
					char ch1=(char)((int)arr[arr.length-1].charAt(0)-1);
					temp=""+i+ch1;
					if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							positions.add(temp+"(Can Be Occupied)");
						}
						else
						{
							break;
						}
					}
				}
			}
		}
		return positions;
	}
	public List<String> isRook(String pos,String coin,String coinType)
	{
		List<String> positions=new ArrayList<>();
		String arr[]=pos.split("");
		int start=Integer.parseInt(arr[0]);
		int cols=arr[arr.length-1].charAt(0)-64;
		int row=0;
		int col=0;
		for(int direc=1;direc<=4;direc++)
		{
			row=start;
			col=cols;
			if(direc==1)
			{
				row++;
				while(row<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					row++;
				}
			}
			if(direc==2)
			{
				col++;
				while(col<=8)
				{
					String temp=""+row+(char)(col+64);
					
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					col++;
				}
			}
			if(direc==3)
			{
				row--;
				while(row>0)
				{
					String temp=""+row+(char)(col+64);
					
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					row--;
				}
			}
			if(direc==4)
			{
				col--;
				while(col>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					col--;
				}
			}
		}
		return positions;
	}
	public List<String> isBishop(String pos,String coin,String coinType)
	{
		List<String> positions=new ArrayList<>();
		String arr[]=pos.split("");
		int start=Integer.parseInt(arr[0]);
		int cols=arr[arr.length-1].charAt(0)-64;
		int row=0;
		int col=0;
		for(int direc=1;direc<=4;direc++)
		{
			row=start;
			col=cols;
			if(direc==1)
			{
				row++;
				col++;
				while(row<=8 && col<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					row++;
					col++;
				}
			}
			if(direc==2)
			{
				col++;
				row--;
				while(col<=8 && row>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					col++;
					row--;
				}
			}
			if(direc==3)
			{
				row++;
				col--;
				while(row<=8 && col>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					row++;
					col--;
				}
			}
			if(direc==4)
			{
				row--;
				col--;
				while(col>0 && row>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							break;
						}
						else
						{
							break;
						}
					}
					row--;
					col--;
				}
			}
		}
		return positions;
	}
	public List<String> isNight(String pos,String coin,String coinType)
	{
		List<String> positions=new ArrayList<>();
		
		String arr[]=pos.split("");
		
		int start=Integer.parseInt(arr[0]);
		int cols=arr[arr.length-1].charAt(0)-64;
		int row=0;
		int col=0;
		for(int direc=1;direc<=8;direc++)
		{
			row=start;
			col=cols;
			if(direc==1)
			{
				row++;
				col=col+2;
				if(row<=8 && col<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==2)
			{
				row++;
				col=col-2;
				if(row<=8 && col>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==3)
			{
				row--;
				col=col+2;
				if(row>0 && col<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==4)
			{
				row--;
				col=col-2;
				if(col>0 && row>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==5)
			{
				row=row+2;
				col++;
				if(row<=8 && col<=8)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==6)
			{
				row=row+2;
				col--;
				if(row<=8 && col>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==7)
			{
				row=row-2;
				col++;
				if(row>0 && col<=8)
				{
					String temp=""+row+(char)(col+64);	
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}	
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}
			if(direc==8)
			{
				row=row-2;
				col--;
				if(row>0 && col>0)
				{
					String temp=""+row+(char)(col+64);
					if(cacheObj.getCoin(temp)=="")
					{
						if(!positions.contains(temp))
						positions.add(temp);
					}
					else if(cacheObj.getCoin(temp)!="" && cacheObj.getCoin(temp)!=null)
					{
						String coins=cacheObj.getCoin(temp);
						if(cacheObj.getType(coins)!=coinType)
						{
							if(!positions.contains(temp))
							positions.add(temp+"(Can Be Occupied)");
							continue;
						}
						else
						{
							continue;
						}
					}
				}
			}		
		}
		
		return positions;
	}
	public String getType(String coin) 
	{
		String type=cacheObj.getType(coin);
		return type;
	}
	public String getCoin(String pos) 
	{
		String coin=cacheObj.getCoin(pos);
		return coin;
	}
	public String getCoinName(String coin) 
	{
		String newArr[]=coin.split("_");
		if(newArr[newArr.length-1].equals("P"))
		{
			return "Pawn";
		}
		else if(newArr[newArr.length-1].equals("R"))
		{
			return "Rook";
		}
		else if(newArr[newArr.length-1].equals("N"))
		{
			return "Night";
		}
		else if(newArr[newArr.length-1].equals("B"))
		{
			return "Bishop";
		}
		else if(newArr[newArr.length-1].equals("Q"))
		{
			return "Queen";
		}
		else if(newArr[newArr.length-1].equals("K"))
		{
			return "King";
		}
		return null;
	}
	public void setCoin(String previousPos, String position, String coin, String coinType) 
	{
		cacheObj.setCoin(position, coin);
		cacheObj.setCoin(previousPos,"");
	}
	public String getKey(String coin) 
	{
		String pos=cacheObj.getBoardPos(coin);
		return pos;
	}
	public List<String> isCastling(String pos, String coin, String coinType) 
	{
		String arr[]=pos.split("");
		List<String> castling=new ArrayList<>();
		int start=arr[arr.length-1].charAt(0)-64;
		String rookPos=arr[0]+((char)(8+64));
		System.out.println(rookPos);
		String coins=cacheObj.getCoin(rookPos);
		String name=getCoinName(coins);
		int i=start+1;
		if(name.equals("Rook"))
		{
			while(i<=7)
			{
				String temp=""+arr[0]+(char)(i+64);
				System.out.println(temp);
				if(cacheObj.getCoin(temp)=="")
				{
					i++;
				}
				else
				{
					break;
				}
				if(i==7 && cacheObj.getCoin(temp)=="")
				{
					castling.add(temp);
					castling.add(rookPos);
				}
			}
		}
		rookPos=arr[0]+((char)(1+64));
		coins=cacheObj.getCoin(rookPos);
		name=getCoinName(coins);
		i=start-1;
		if(name.equals("Rook"))
		{
			while(i>=2)
			{
				String temp=""+arr[0]+(char)(i+64);
				if(cacheObj.getCoin(temp)=="")
				{
					i--;
				}
				else
				{
					break;
				}
				if(i==1 && cacheObj.getCoin(temp)=="")
				{
					castling.add(temp);
					castling.add(rookPos);
				}
			}
		}
		return castling;
	}
}
