import java.util.Scanner;

class driver
{
 	public static void main (String []args)
	{
		Scanner scan = new Scanner(System.in);
		int x;
		Project obj = new Project("");
		boolean flag = false;
		
		do
		{
		 	System.out.println("Menu: ");
			System.out.println("-----");
			System.out.println("\t1-Create project");
			System.out.println("\t2-Add file to project");
			System.out.println("\t3-Print list of files in project");
			System.out.println("Your choice? (0 to quit)");
			x = scan.nextInt();
			scan.nextLine();
			
			switch(x)
			{
				case 1: 
					System.out.println("Enter a name: ");
					String name = scan.nextLine();
					
					obj = new Project(name);
					flag = true;
					break;
				
				case 2:
					if (flag)
					{
						System.out.println("Enter name of file: ");
						String fileName = scan.nextLine();
						
						JavaFile newFile = new JavaFile(fileName);
					 	obj.addFile(newFile);
					}
					
					else 
					{
					 	System.out.println("Error: no project exists");
					}
					
					break;
				
				case 3:
					if (flag)
					{
						
						obj.printFiles();
					}
					
					else
					{
					 	System.out.println("No files in the projects");
					}
					break;
			}
					
		}while (x != 0);
	}
}