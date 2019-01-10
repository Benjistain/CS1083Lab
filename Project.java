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
	
	public void printFiles()
	{
		
		System.out.println("\nProject name: " + name);
		System.out.println("-----------------------");
		System.out.println("File names: ");
	 	for(int i=0; i<count; i++)
		{
			System.out.println("\t"+objects[i]);
		}
		System.out.println();
	}
}