///////////////////////////////////////
//--CS 1083 Lab: JavaFile Class--
//Last Edited by: N/A
//Last Edit Date/Time: N/A
//Changelog:
//*Add next editlist here*
///////////////////////////////////////
import java.util.Scanner;
import java.io.*;

class JavaFile
{
 	private String name;
	private String contents;
	
	public JavaFile(String name)
	{
	 	this.name = name;
		contents = "";
	}
	
	public void load() throws Exception
	{
		Scanner scan = new Scanner(new File(name + ".java"));
		while(scan.hasNextLine())
		{
			contents += scan.nextLine() + "\n";
		}
	}
	
	public String getContents() throws Exception
	{
		if ((contents.equals("")))
		{
			load();
		}
		
		return contents;
	}

	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name;
	}
}