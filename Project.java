///////////////////////////////////////
//--CS 1083 Lab: Project Class--
//Last Edited by: N/A
//Last Edit Date/Time: N/A
//Changelog:
//*Add next editlist here*
///////////////////////////////////////
class Project
{
	private String name;
	private JavaFile[] objects;
	private int count;
	
	public Project(String name)
	{
	 	this.name = name;
		objects = new JavaFile[1];
		count = 0;
	}
	
	public void addFile(JavaFile newFile)
	{
		if (objects.length == count)
		{
		 	JavaFile[] temp = new JavaFile[objects.length * 2];
			
			for(int i=0; i < objects.length; i++)
			{
			 	temp[i] = objects[i];
			}
			
			objects = temp;
		}
		
		objects[count] = newFile;
		count++;
	}

	public int search(String name)
	{
		boolean found = false;
		int i=0;

		while(i<count && !found)
		{
			if (name.equals(objects[i].getName()))
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
		if (index != 0)
		{
			if (index >= count)
			{
				System.out.println("Error: Invalid index");
			}
			else
			{
				for (int i=index; i<count-1; i++)
				{
					objects[i] = objects[i+1];
				}
				count--;
			}
		}
		else
			System.out.println("File not found");
	}
	
	public void printFiles()
	{
		
		System.out.println("\nProject name: " + name);
		System.out.println("-----------------------");
		System.out.println("File names: ");
	 	for(int i=0; i<count; i++)
		{
			System.out.println("\t"+objects[i]);
		}
	}
}