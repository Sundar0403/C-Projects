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
		String coin=logicObj.getCoin(pos);

		String coinType=logicObj.getType(coin);

		String name=logicObj.getCoinName(coin);

		switch(name)
		{
			case "Pawn":
			{
				 positions=logicObj.isPawn(pos,coin,coinType);
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				break;
			}
			
			case "Rook":
			{
				positions=logicObj.isRook(pos, coin, coinType);
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				break;
			}
			
			case "Bishop":
			{
				positions=logicObj.isBishop(pos, coin, coinType);
				
				System.out.println("The Positions Available Are :");
				
				System.out.println(positions.toString());
				break;
			}
			
			case "Queen":
			{
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
		}
	}
	public static void main(String args[])
	{
		ChessGame gameObj=new ChessGame();
		try {
			gameObj.print();
			
			gameObj.getPosition();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
