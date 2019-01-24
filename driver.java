///////////////////////////////////////
//--CS 1083 Lab: Driver Class--
//Last Edited by: Bailey
//Last Edit Date/Time: Jan 14/19, 12:50
//Changelog:
//Jan 14/19, 12:50
//++Added Code Header and Changelog
//++Added Paragraph Spacing between "Menus" when running code
//
//*Add next editlist here*
///////////////////////////////////////
import java.util.Scanner;

class driver
{
 	public static void main (String []args) throws Exception
	{
		Scanner scan = new Scanner(System.in);
		int x;
		Project proj = new Project("");
		boolean flag = false;
		
		do
		{
			System.out.println("\nMenu: ");
			System.out.println("-----");
			System.out.println("\t1-Create project");
			System.out.println("\t2-Add file to project");
			System.out.println("\t3-Remove file from project");
			System.out.println("\t4-Compile project");
			System.out.println("\t5-Read file contents");
			System.out.println("\t6-Run file contents");
			System.out.println("\t7-Print list of files in project");
			System.out.println("Your choice? (0 to quit): ");
			x = scan.nextInt();
			scan.nextLine();
			
			switch(x)
			{
				case 1: 
					// Create a new project, allowing access to other commands
					System.out.println("\nEnter a name: ");
					String name = scan.nextLine();
					
					proj = new Project(name);
					flag = true;
					break;
				
			}
			
			if (flag)
			{
			
				switch (x)
				{
					case 2:
						// Add a new file to the project
						System.out.println("\nEnter name of file: ");
						String fileName = scan.nextLine();
						
						JavaFile newFile = new JavaFile(fileName);
					 	proj.addFile(newFile);
						break;
	
					case 3:
						// Delete a file from the project
						System.out.print("Enter name of file to delete: ");
						fileName = scan.nextLine();
						proj.removeFile(fileName);
						break;
			
					case 4:
						// compile entire project
						proj.compile();
						break;
						
					case 5: 
						//Print contents of the file specified by user
						System.out.print("Enter name of file to read: ");
						fileName = scan.nextLine();
						
						proj.readFile(fileName);
						break;
						
					case 6:
						
						proj.run();
						break;
					
					case 7:
						// Print file names in the project
						proj.printFiles();
						break;
				}
			}
			else 
			{
				System.out.println("\nError: no project exists");
			}
					
		}while (x != 0);
	}
}