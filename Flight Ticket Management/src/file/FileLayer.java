package file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLayer 
{
	File newFile=new File("Mani.txt");
			
	public void createNewFile(File newFile) throws IOException
	{
		if(newFile.createNewFile())
		{
			System.out.println("New File is Created :");
		}
		else
		{
			System.out.println("File Already Exists :");
		}
	}
	
	public void writeFileIndividual(String fileName,String flight) throws IOException
	{
		File fileObj=new File(fileName);
		createNewFile(fileObj);
		
		try(FileWriter writer=new FileWriter(fileObj,true);)
		{
			writer.write(flight);
		}
	}
	
	public void writeFileOverall() throws Exception
	{
		createNewFile(newFile);
		try(FileWriter out=new FileWriter(newFile);)
		{
			out.write("Flight-A112-Chennai-Mumbai\n");
			out.write("Flight-A113-Chennai-Kolkata\n");
			out.write("Flight-A114-Chennai-Delhi\n");
			out.write("Flight-A115-Chennai-Mumbai");
		}
		catch(IOException e)
		{
			throw new IOException("IOException Occured :");
		}
	}
	
	public String readFileIndividual(String fileName) throws Exception
	{
		File newFile=new File(fileName);
		try(FileReader in=new FileReader(newFile);)
		{
			String str="";
		
			int i;
		
			while((i=in.read())!=-1)
			{
				str=str+(char)i;
			}
		
			return str;
		}
		catch(IOException e)
		{
			throw new IOException("IOException Occured :");
		}
	}
	
	public List<String> readFile() throws IOException
	{
		try(FileReader in=new FileReader(newFile);)
		{
			List<String> newList=new ArrayList<>();
			String str="";
		
			int i;
		
			while((i=in.read())!=-1)
			{
				str=str+(char)i;
				if(i==10)
				{
					newList.add(str);
					str="";
				}
			}
			
			return newList;
		}
		catch(IOException e)
		{
			throw new IOException("IOException Occured :");
		}
		
	}
}
