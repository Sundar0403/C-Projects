package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import cache.CacheLayer;
import logic.ChessLogic;

public class ChessGame {
	
	ChessLogic logicObj=new ChessLogic();
	List<String> positions=new ArrayList<>();
	List<String> coinPos=new ArrayList<>();
	List<String> kingss=new ArrayList<>();
	List<String> white=new ArrayList<>();
	List<String> black=new ArrayList<>();

	int kingCount=0;
	String previousPos="";
	String coinType="";
	String coin="";
	String checkState="";
	Scanner scan=new Scanner(System.in);
	private void print() throws Exception
	{
		logicObj.getPrint();
	}
	
	public void getPosition() 
	{
		System.out.println();
		System.out.println("Enter the Position :");
		String pos=scan.nextLine();
		previousPos=pos;
		String coin=logicObj.getCoin(pos);
		this.coin=coin;

		String coinType=logicObj.getType(coin);

		String name=logicObj.getCoinName(coin);

		switch(name)
		{
			case "Pawn":
			{
				if(positions.size()>0)
				{
					positions.clear();
				}
				 positions=logicObj.isPawn(pos,coin,coinType);
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				break;
			}
			
			case "Rook":
			{
				if(positions.size()>0)
				{
					positions.clear();
				}
				positions=logicObj.isRook(pos, coin, coinType);
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				break;
			}
			
			case "Bishop":
			{
				if(positions.size()>0)
				{
					positions.clear();
				}
				positions=logicObj.isBishop(pos, coin, coinType);
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				break;
			}
			
			case "Queen":
			{
				if(positions.size()>0)
				{
					positions.clear();
				}
				List<String> first=logicObj.isRook(pos, coin, coinType);
				List<String> second=logicObj.isBishop(pos, coin, coinType);
				
				for(int i=0;i<first.size();i++)
				{
					positions.add(first.get(i));
				}
				
				for(int j=0;j<second.size();j++)
				{
					positions.add(second.get(j));
				}
				
				System.out.println(positions.toString());
				break;
			}
			
			case "Night":
			{
				if(positions.size()>0)
				{
					positions.clear();
				}
				positions=logicObj.isNight(pos, coin, coinType);
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				break;
			}
			
			case "King":
			{
				if(positions.size()>0)
				{
					positions.clear();
				}
				kingss=logicObj.isKing(pos, coin, coinType);
				
				if(kingCount==0)
				{
					coinPos=logicObj.isCastling(pos,coin,coinType);
					
					if(coinPos.size()!=0)
					{
						for(int i=0;i<coinPos.size()-1;i++)
						{
							kingss.add(coinPos.get(i));
						}
					}
				}
				
				if(coin.startsWith("W"))
				{
					for(int i=0;i<kingss.size();i++)
					{
						if(!white.contains(kingss.get(i)))
						{
							positions.add(kingss.get(i));
						}
					}
				}
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				kingss.clear();
				break;
				
			}
		}
	}
	
	public void moveTheCoin() throws Exception
	{
		if(positions.size()==0)
		{
			throw new Exception("There is No Possible Moves From Here :");
		}
		System.out.println("Enter the Position to Move the Coin :");
		String position=scan.nextLine();
		
		String coinType=logicObj.getType(coin);
		
		if(!this.coinType.equals(coinType))
		{
			logicObj.setCoin(previousPos,position,coin,coinType);
		}
		
		if(coinPos.size()!=0 && positions.add(position))
		{
			String rookPos=coinPos.get(coinPos.size()-1);
			String coins=logicObj.getCoin(rookPos);
			System.out.println(coins);
			String coinsType=logicObj.getType(coins);
			String arr[]=position.split("");
			String temporary[]=rookPos.split("");
			int temp=temporary[temporary.length-1].charAt(0)-64;
			int value=0;
			if(temp<4)
			{
				value=arr[arr.length-1].charAt(0)+1;
			}	
			else
			{
				value=arr[arr.length-1].charAt(0)-1;
			}
			String newPos=arr[0]+((char)(value));
			System.out.println(newPos);
			
			logicObj.setCoin(rookPos,newPos,coins,coinsType);
		}
		else
		{
			if(coinType.equals("White"))
			{
				System.out.println("Its Black's Turn");
			}
			else
			{
				System.out.println("Its White's Turn");
			}
		}
		this.previousPos=position;
		this.coinType=coinType;
		positions.clear();
	}
	
	public void kingObstacleCheck() throws Exception
	{
		String coinType="";
		String coin="";
		if(this.coinType.equals("White"))
		{
			coinType="Black";
			coin="B_K";
			String kings=logicObj.getKey(coin);
			
			String previousCoin=logicObj.getCoin(previousPos);
			System.out.println(previousCoin);
			List<String> kingList=logicObj.isKing(kings, coin, coinType);
			if(previousCoin.endsWith("P"))
			{
				positions=logicObj.isPawn(previousPos,previousCoin, this.coinType);
			}
			if(previousCoin.endsWith("R"))
			{
				positions=logicObj.isRook(previousPos,previousCoin, this.coinType);
			}
			if(previousCoin.endsWith("N"))
			{
				positions=logicObj.isNight(previousPos,previousCoin, this.coinType);
			}
			if(previousCoin.endsWith("B"))
			{
				positions=logicObj.isBishop(previousPos,previousCoin, this.coinType);
			}
			if(previousCoin.endsWith("Q"))
			{
				List<String> bishop=logicObj.isBishop(previousPos,previousCoin, this.coinType);
				List<String> rook=logicObj.isRook(previousPos,previousCoin, this.coinType);
				for(int i=0;i<bishop.size();i++)
				{
					positions.add(bishop.get(i));
				}
				for(int i=0;i<rook.size();i++)
				{
					positions.add(rook.get(i));
				}
				
			}
			
			white=logicObj.getBlackPositions("White");
			
			int count=0;
			for(int i=0;i<kingList.size();i++)
			{
				if(white.contains(kingList.get(i)))
				{
					count++;
				}
			}
			if(count==kingList.size())
			{
				System.out.println("Checkmate");
				System.exit(0);
			}
			
			String king=logicObj.getKey(coin)+"(Can Be Occupied)";
			checkState=logicObj.getCheckStatus(positions,king);
			
			String queenPos=logicObj.getKey("B_Q");
			if(logicObj.isBishop(queenPos,"B_Q","Black").contains(king)||logicObj.isRook(queenPos,"W_Q","Black").contains(king))
			{
				checkState="Check";
			}
			String bishopPos=logicObj.getKey("B_B");
			if(logicObj.isBishop(bishopPos,"B_B","Black").contains(king))
			{
				checkState="Check";
			}
			String rookPos=logicObj.getKey("B_R");
			if(logicObj.isRook(rookPos,"B_R","Black").contains(king))
			{
				checkState="Check";
			}
			
			System.out.println("This is Check State :"+checkState);
		}
		else
		{
			coinType="White";
			coin="W_K";
			String king=logicObj.getKey(coin);
			
			List<String> kingList=logicObj.isKing(king, coin, coinType);
			
			String previousCoin=logicObj.getCoin(previousPos);
			
			if(previousCoin.endsWith("P"))
			{
				positions=logicObj.isPawn(previousPos,previousCoin, coinType);
			}
			if(previousCoin.endsWith("R"))
			{
				positions=logicObj.isRook(previousPos,previousCoin, coinType);
			}
			if(previousCoin.endsWith("N"))
			{
				positions=logicObj.isNight(previousPos,previousCoin, coinType);
			}
			if(previousCoin.endsWith("B"))
			{
				positions=logicObj.isBishop(previousPos,previousCoin, coinType);
			}
			if(previousCoin.endsWith("Q"))
			{
				List<String> bishop=logicObj.isBishop(previousPos,previousCoin, coinType);
				List<String> rook=logicObj.isRook(previousPos,previousCoin, coinType);
				for(int i=0;i<bishop.size();i++)
				{
					positions.add(bishop.get(i));
				}
				for(int i=0;i<rook.size();i++)
				{
					positions.add(rook.get(i));
				}
			}
			king=king+"(Can Be Occupied)";
			checkState=logicObj.getCheckStatus(positions,king);
			
			black=logicObj.getBlackPositions("Black");
			int count=0;
			for(int i=0;i<kingList.size();i++)
			{
				if(black.contains(kingList.get(i)))
				{
					count++;
				}
			}
			if(count==kingList.size())
			{
				System.out.println("Checkmate");
				System.exit(0);
			}
			String queenPos=logicObj.getKey("B_Q");
			if(logicObj.isBishop(queenPos,"B_Q","Black").contains(king)||logicObj.isRook(queenPos,"W_Q","Black").contains(king))
			{
				checkState="Check";
			}
			String bishopPos=logicObj.getKey("B_B");
			if(logicObj.isBishop(bishopPos,"B_B","Black").contains(king))
			{
				checkState="Check";
			}
			String rookPos=logicObj.getKey("B_R");
			if(logicObj.isRook(rookPos,"B_R","Black").contains(king))
			{
				checkState="Check";
			}
			
			
			System.out.println(checkState);
		}
		
		/*String pos=logicObj.getKey(coin);
		System.out.println(pos);*/
		
		
	}
	
	public void checkMate()
	{
		
	}
	
	public static void main(String args[])
	{
		ChessGame gameObj=new ChessGame();
		Scanner scan=new Scanner(System.in);
		
		int choice=0;
		boolean flag=true;
		
		while(flag)
		{
			System.out.println("-*-*-* WELCOME TO CHESS GAME -*-*-*");
			System.out.println();
			System.out.println();
			System.out.println("-*-*-* 1 . PRINT THE CHESS BOARD -*-*-*");
			System.out.println("-*-*-* 2 . GET THE POSITIONS TO MOVE -*-*-*");
			System.out.println("-*-*-* 3 . MOVE THE COIN -*-*-*");
			System.out.println("Enter Your Choice :");
			try
			{
				choice=scan.nextInt();
				scan.nextLine();
			}
			catch(Exception e)
			{
				System.out.println("Number Format is Wrong :"+e.getMessage());
				e.printStackTrace();
			}
			switch(choice)
			{
				case 1:
						try
						{
							gameObj.print();
							System.out.println();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured :"+e.getMessage());
							e.printStackTrace();
						}
						break;
			
				case 2:
						try
						{
							gameObj.getPosition();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured :"+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 3:
						try
						{
							gameObj.moveTheCoin();
							gameObj.kingObstacleCheck();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured :"+e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 4:
						try
						{
							gameObj.kingObstacleCheck();
						}
						catch(Exception e)
						{
							System.out.println("Exception Occured :"+e.getMessage());
							e.printStackTrace();
						}
						break;			
				
				
			}
		}
	}
}
