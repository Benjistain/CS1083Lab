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
	
	// Constructor
	public Project(String name)
	{
	 	this.name = name;
		files = new JavaFile[1];
		count = 0;
	}
	
	public void addFile(JavaFile newFile)
	{
		// if the array is full, double its size
		if (files.length == count)
		{
		 	JavaFile[] temp = new JavaFile[files.length * 2];
			for(int i=0; i < files.length; i++)
			{
			 	temp[i] = files[i];
			}
			
			files = temp;
		}
		
		// add new file to array 
		files[count] = newFile;
		count++;
	}

	// Searches an array of files based on the name and returns the index of the file
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
			// Didnt find file with passed name
			return -1;
	}

	// Remove a file from an array of files based on the name
	public void removeFile(String fileName)
	{
		// Find the index of the given file
		int index = search(fileName);
		// remove the index
		if (index != -1)
		{
			if (index >= count)
			{
				System.out.println("Error: Project.removeFile (index out of bounds)");
			}
			else
			{
				// Shift files up to replace the removed file
				for (int i=index; i<count-1; i++)
					files[i] = files[i+1];
				
				count--;
			}
		}
		else
			System.out.println("File not found");
	}
	
	// Read and print the contents of a given file
	public void readFile(String fileName) throws Exception
	{
		// Error occuring because the filename we are reading doesnt have .java at the end. 
		// Cannot add it here because the serach would break
		// May be able to get rid of search and use the choicebox index as the index of the array (cindex-1 = projindex)
		int index = search(fileName);
		GUI.output(files[index].getContents());
	}
	
	// Print a list of the files in the current project
	public void printFiles()
	{
		System.out.println("\nProject name: " + name);
		System.out.println("-----------------------");
		System.out.println("File names: ");
		
	 	for(int i=0; i<count; i++)
			System.out.println("\t"+files[i]);
	}
	
	// Compile all files in the project
	public void compile() throws Exception
	{
		boolean flag = true;
		int i = 0;
		while(flag && i < count)
		{
			Process p1 = Runtime.getRuntime().exec("javac " + files[i].getName()+ ".java");
			Scanner scan = new Scanner(p1.getErrorStream());
			GUI.output("\n\nCompiling "+ files[i].getName()+".java");
			if (scan.hasNextLine())
			{
				while(scan.hasNextLine())
				{
					GUI.output(scan.nextLine() + "\n");
				}
				flag = false;
			}
			else
			{
				GUI.output("\nCompiled "+ files[i].getName()+".java successfully");
			}
			i++;	
		}
	}
	
	//Run the last file in array
	public void run() throws Exception
	{
		Process p1 = Runtime.getRuntime().exec("java " + files[count-1].getName());
		Scanner scan = new Scanner(p1.getInputStream());

		GUI.output("\n");
		while(scan.hasNextLine())
		{
			GUI.output("\n" + scan.nextLine());
		}
		GUI.output("\n");		
	}
}