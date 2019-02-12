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
import java.util.Optional;

public class GUI extends Application
{
   // Declaration of the controls needed
   Button compileButton;
	Button runButton;
   TextArea mainText;
	Label outputLabel;
	
   ChoiceBox<String> FileTab;
   // Space needed to fix initial button spacing
	String[] fileOptions = {"File                ", "New Project", "New", "Add", "Delete"};
	
	ChoiceBox<String> OpenTab;
	String[] openOptions = {"Open"};
	
	// Background code to drive
	Project proj = new Project("");
	boolean flag = false;

   public void start(Stage primaryStage) throws Exception
   {
      // code to indicate what the window will look like
      Font mainFont = new Font("courrier", 24);
      
      // File tab aesthetics, set options, initial selection.
		FileTab = new ChoiceBox<String>();
      FileTab.setStyle("-fx-font: 24px \"Courrier\";");  
      FileTab.getItems().addAll(fileOptions);
      FileTab.getSelectionModel().select(0);

      // OpenTab aesthetics, set options, initial selection.
		OpenTab = new ChoiceBox<String>();
      OpenTab.setStyle("-fx-font: 24px \"Courrier\";");  
      OpenTab.getItems().addAll(openOptions);
      OpenTab.getSelectionModel().select(0);

      // Main text area layout
		mainText = new TextArea();
      mainText.setFont(mainFont);
      mainText.setPrefRowCount(15);
      mainText.setPrefColumnCount(50);
      mainText.setWrapText(true);
      
      // Setup initial output
		outputLabel = new Label("To begin, create a new project.");
      outputLabel.setFont(mainFont);
		outputLabel.setWrapText(true);
      
      // Call handle method when FileTab is used
      FileTab.setOnAction(new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {
            processFileTab(e);
         }
      });
        
      // Call handle method when the open tab is used
		OpenTab.setOnAction(new EventHandler<ActionEvent>() 
		{
         public void handle(ActionEvent e) 
         {
            processOpenTab(e);
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
		outerGrid.add(FileTab, 0, 0, 3, 1);
		outerGrid.add(OpenTab, 3, 0);
		outerGrid.add(compileButton, 4, 0);
		outerGrid.add(runButton, 5, 0);
		outerGrid.add(mainText, 0, 3, 20, 4);
      outerGrid.add(outputLabel, 0, 7, 10, 3);

      // Create Scene and set size of window
      Scene theScene = new Scene(outerGrid, 1200, 800);
      primaryStage.setTitle("Hutt");
      primaryStage.setScene(theScene);
      primaryStage.show();
   }
   
   // When the run button is clicked
   public void processRunButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "run!" , ButtonType.OK);
      alert.showAndWait();

      // code from driver: 
      /* 
      proj.run();
      */
   }
   
   // When the compile button is clicked
	public void processCompileButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "Compiling!" , ButtonType.OK);
      alert.showAndWait();
      
      // Try to compile project
      try
      {
         proj.compile();
      }
      catch (Exception exc)
      {
         // If there is a compile error display this message
         Alert error = new Alert(AlertType.INFORMATION, 
                  "Error processing compile command" , ButtonType.OK);
      }
   }
   
   // When a FileTab option is clicked
	public void processFileTab(ActionEvent event)
	{	
      // if "new project" is clicked
		if (FileTab.getSelectionModel().getSelectedIndex() == 1)
		{
         // Create new project
			// make popup to take name of proj
			TextInputDialog dialog = new TextInputDialog();
      	dialog.setTitle("New Project");
      	dialog.setHeaderText("Enter the name of your project");
         dialog.showAndWait();
         String input = dialog.getEditor().getText();
         
         proj = new Project(input);
         flag = true;
            
         outputLabel.setText("New project created: " + input);
      }
      else if (FileTab.getSelectionModel().getSelectedIndex() == 2)
      {
         // new file is selected
         // This function has not been implemented yet
         Alert alert = new Alert(AlertType.INFORMATION, "New File!" , ButtonType.OK);
		   alert.showAndWait();
      }
      else if (FileTab.getSelectionModel().getSelectedIndex() == 3)
      {
         // add file is selected
         Alert alert = new Alert(AlertType.INFORMATION, "Add File!" , ButtonType.OK);
         alert.showAndWait();

         // When adding a new file to open tab, use this code: filesChoice.getItems().add(newFileName); 
         
         /*
         //Implement code from driver: 
         System.out.println("\nEnter name of file: ");
			String fileName = scan.nextLine();
						
			JavaFile newFile = new JavaFile(fileName);
		 	proj.addFile(newFile);
         */
      }
      else if (FileTab.getSelectionModel().getSelectedIndex() == 4)
      {
         // delete file is selected
         Alert alert = new Alert(AlertType.INFORMATION, "Delete File!" , ButtonType.OK);
         alert.showAndWait();
         
         /*
         // Code from driver:
         System.out.print("Enter name of file to delete: ");
			fileName = scan.nextLine();
			proj.removeFile(fileName);
         */
      }
	}
   
   // When an option in the open tab is clicked
	public void processOpenTab(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION, "OpenTab!" , ButtonType.OK);
      alert.showAndWait();
      // This method should show the contents of the selected file
      
      // driver code
      /*
      System.out.print("Enter name of file to read: ");
		fileName = scan.nextLine();
		
      proj.readFile(fileName);
       */
	}

   public static void main(String[] args)
   {
      launch(args);
   }
}