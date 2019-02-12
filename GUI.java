import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import java.util.Optional;

public class GUI extends Application
{
   // Declaration of the controls needed
   Button compileButton;
	Button runButton;
   TextArea mainText;
   
   static String prevOutput = "";
   static Label outputLabel;
   static ScrollPane sp; 
	
   ChoiceBox<String> fileTab;
   // Space needed to fix initial button spacing
	String[] fileOptions = {"File                ", "New Project", "New", "Add", "Delete"};
	
	ChoiceBox<String> openTab;
	String[] openOptions = {"Open"};
	
	// Background code to drive
	Project proj = new Project("");
	boolean flag = false;

   public void start(Stage primaryStage) throws Exception
   {
      // code to indicate what the window will look like
      Font mainFont = new Font("courrier", 24);
      
      // File tab aesthetics, set options, initial selection.
		fileTab = new ChoiceBox<String>();
      fileTab.setStyle("-fx-font: 24px \"Courrier\";");  
      fileTab.getItems().addAll(fileOptions);
      fileTab.getSelectionModel().select(0);

      // openTab aesthetics, set options, initial selection.
		openTab = new ChoiceBox<String>();
      openTab.setStyle("-fx-font: 24px \"Courrier\";");  
      openTab.getItems().addAll(openOptions);
      openTab.getSelectionModel().select(0);

      // Main text area layout
		mainText = new TextArea();
      mainText.setFont(mainFont);
      mainText.setPrefRowCount(15);
      mainText.setPrefColumnCount(50);
      mainText.setWrapText(true);

      // Create scrollable output area 
      sp = new ScrollPane();
      sp.setContent(outputLabel);
      
      // Setup initial output
		outputLabel = new Label("To begin, create a new project.");
      outputLabel.setFont(mainFont);
		outputLabel.setWrapText(true);
      
      // Call handle method when fileTab is used
      fileTab.setOnAction(new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {
            processfileTab(e);
         }
      });
        
      // Call handle method when the open tab is used
		openTab.setOnAction(new EventHandler<ActionEvent>() 
		{
         public void handle(ActionEvent e) 
         {
            processopenTab(e);
         }
      });

      // Create compile button
      compileButton = new Button("Compile");
      compileButton.setFont(mainFont);

      // When the compile button is clicked
      compileButton.setOnAction(new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {
            processCompileButton(e);
         }
      });
        
      // Create run button
		runButton = new Button("Run");
      runButton.setFont(mainFont);

      // When the run button is clicked
      runButton.setOnAction(new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {
            processRunButton(e);
         }
      });
      
      // Create main gridpane
		GridPane outerGrid = new GridPane();
		outerGrid.add(fileTab, 0, 0, 3, 1);
		outerGrid.add(openTab, 3, 0);
		outerGrid.add(compileButton, 4, 0);
		outerGrid.add(runButton, 5, 0);
      outerGrid.add(mainText, 0, 3, 20, 4);
      outerGrid.add(sp, 0, 7, 20, 3);

      // Create Scene and set size of window
      Scene theScene = new Scene(outerGrid, 1200, 800);
      primaryStage.setTitle("Hutt");
      primaryStage.setScene(theScene);
      primaryStage.show();
   }
   
   // When the run button is clicked
   public void processRunButton(ActionEvent event)
   {
      // code from driver: 
      try
      {
      proj.run();
      }
      catch (Exception exc)
      {
         GUI.output("Run Error. See run method in project class");
      }
   }
   
   // When the compile button is clicked
	public void processCompileButton(ActionEvent event)
   {  
      // Try to compile project
      try
      {
         proj.compile();
      }
      catch (Exception exc)
      {
         // If there is a compile error display this message
         Alert error = new Alert(AlertType.INFORMATION, "Error processing compile command" , ButtonType.OK);
         error.showAndWait();
      }
   }
   
   // When a fileTab option is clicked
	public void processfileTab(ActionEvent event)
	{	
      // if "new project" is clicked
		if (fileTab.getSelectionModel().getSelectedIndex() == 1)
		{
         // Create new project
			// make popup to take name of proj
			TextInputDialog dialog = new TextInputDialog();
      	dialog.setTitle("New Project");
      	dialog.setHeaderText("Enter the name of your project");
         Optional<String> result = dialog.showAndWait();
         if (result.isPresent())
         {
            String input = dialog.getEditor().getText();
            
            proj = new Project(input);
            flag = true;
               
            output("\nNew project created: " + input + "\n");
         }
      }
      else if (flag && fileTab.getSelectionModel().getSelectedIndex() == 2)
      {
         // new file is selected
         // This function has not been implemented yet
         Alert alert = new Alert(AlertType.INFORMATION, "New File!" , ButtonType.OK);
		   alert.showAndWait();
      }
      else if (flag && fileTab.getSelectionModel().getSelectedIndex() == 3)
      {
         // add file is selected
         // When adding a new file to open tab, use this code: filesChoice.getItems().add(newFileName); 
         
         // Implement code from driver: 
         // make popup to take name of proj
			TextInputDialog dialog = new TextInputDialog();
      	dialog.setTitle("Add File");
      	dialog.setHeaderText("Enter the name of your file");
         Optional<String> result = dialog.showAndWait();
         if (result.isPresent())
         {
            String fileName = dialog.getEditor().getText();
                     
            JavaFile newFile = new JavaFile(fileName);
            proj.addFile(newFile);

            openTab.getItems().add(fileName);
            
            output("\nAdded file: " + newFile + ".java");
         }
         
      }
      else if (flag && fileTab.getSelectionModel().getSelectedIndex() == 4)
      {
         // delete file is selected
         TextInputDialog dialog = new TextInputDialog();
      	dialog.setTitle("Delete File");
      	dialog.setHeaderText("Enter the name of the file to delete: ");
         Optional<String> result = dialog.showAndWait();
         if (result.isPresent())
         {
            String fileName = dialog.getEditor().getText();

            int index = proj.removeFile(fileName);
            
            // Remove file from openTab by shifting others on top of it
            try
            {
               openTab.getItems().remove(index+1);
            }
            catch( Exception e)
            {
               GUI.output("\nError removing from openTab");
            }
         }
      }
      else if(!flag)
      {
         GUI.output("\nCreate project first");
      }
	}
   
   // When an option in the open tab is clicked
	public void processopenTab(ActionEvent event)
	{
      // This method should show the contents of the selected file
      try
      {
         String fileName = openTab.getSelectionModel().getSelectedItem();
         int fileIndex = openTab.getSelectionModel().getSelectedIndex();
         //GUI.output("\nFileName= "+ fileName + "\nFileIndex= " + fileIndex);
         proj.readFile(fileName, fileIndex-1);
      }
      catch (Exception e)
      {
         GUI.output("\nError opening file \n");
      }
	}

   public static void output(String newOutput)
   {
      prevOutput += newOutput;
      outputLabel.setText(prevOutput);
      sp.setContent(outputLabel);
   }
   public static void main(String[] args)
   {
      launch(args);
   }
}