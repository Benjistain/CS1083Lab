///////////////////////////////////////
//--CS 1083 Lab: Project Class--
//Last Edited by: N/A
//Last Edit Date/Time: N/A
//Changelog:
//*Add next editlist here*
///////////////////////////////////////
import java.util.Scanner;

class Project
{
	private String name;
	private JavaFile[] files;
	private int count;
	
	public Project(String name)
	{
	 	this.name = name;
		files = new JavaFile[1];
		count = 0;
	}
	
	public void addFile(JavaFile newFile)
	{
		if (files.length == count)
		{
		 	JavaFile[] temp = new JavaFile[files.length * 2];
			for(int i=0; i < files.length; i++)
			{
			 	temp[i] = files[i];
			}
			
			files = temp;
		}
		
		files[count] = newFile;
		count++;
	}

	public int search(String name)
	{
		boolean found = false;
		int i=0;

		while(i<count && !found)
		{
			if (name.equals(files[i].getName()))
			{
				found = true;
			}
			else
				i++;
		}

		if (found)
			return i;
		else
			return -1;
	}

	public void removeFile(String fileName)
	{
		int index = search(fileName);
		if (index != -1)
		{
			if (index >= count)
			{
				System.out.println("Error: Invalid index");
			}
			else
			{
				for (int i=index; i<count-1; i++)
				{
					files[i] = files[i+1];
				}
				count--;
			}
		}
		else
			System.out.println("File not found");
	}
	
	public void readFile(String fileName) throws Exception
	{
		int index = search(fileName);
		System.out.print(files[index].getContents());
	}
	
	public void printFiles()
	{
		
		System.out.println("\nProject name: " + name);
		System.out.println("-----------------------");
		System.out.println("File names: ");
	 	for(int i=0; i<count; i++)
		{
			System.out.println("\t"+files[i]);
		}
	}
	
	public void compile() throws Exception
	{
		for (int i=0; i<count; i++)
		{
			Runtime.getRuntime().exec("javac " + files[i].getName() + ".java");
		}
	}
}