import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceBox;
import java.util.Optional;

public class GUI extends Application
{
   // declaration of the controls needed
   Button compileButton;
	Button runButton;
	TextArea mainText;
	Label message;
	Label bar;
	
	ChoiceBox<String> FileTab;
	String[] fileOptions = {"File                ", "New Project", "New", "Add", "Delete"};
	
	ChoiceBox<String> OpenTab;
	String[] openOptions = {"Open"};
	
	// Background code
	Project proj = new Project("");
	boolean flag = false;

   
   public void start(Stage primaryStage) throws Exception
   {
      // code to indicate what the window will look like
      Font mainFont = new Font("courrier", 24);
		
		FileTab = new ChoiceBox<String>();
      FileTab.setStyle("-fx-font: 24px \"Courrier\";");  
      FileTab.getItems().addAll(fileOptions);
      FileTab.getSelectionModel().select(0);

		OpenTab = new ChoiceBox<String>();
      OpenTab.setStyle("-fx-font: 24px \"Courrier\";");  
      OpenTab.getItems().addAll(openOptions);
      OpenTab.getSelectionModel().select(0);

      
		mainText = new TextArea();
      mainText.setFont(mainFont);
      mainText.setPrefRowCount(15);
      mainText.setPrefColumnCount(50);
      mainText.setWrapText(true);
		
		message = new Label("output");
      message.setFont(mainFont);
		message.setWrapText(true);
		
      FileTab.setOnAction(new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {
            processFileTab(e);
         }
      });
		  
		OpenTab.setOnAction(new EventHandler<ActionEvent>() 
		{
         public void handle(ActionEvent e) 
         {
            processOpenTab(e);
         }
      });

      compileButton = new Button("Compile");
      compileButton.setFont(mainFont);
      compileButton.setOnAction(new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {
            processCompileButton(e);
         }
      });
		  
		runButton = new Button("Run");
      runButton.setFont(mainFont);
      runButton.setOnAction(new EventHandler<ActionEvent>() 
      {
         public void handle(ActionEvent e) 
         {
            processRunButton(e);
         }
      });
		  
		GridPane outerGrid = new GridPane();
		outerGrid.add(FileTab, 0, 0, 3, 1);
		outerGrid.add(OpenTab, 3, 0);
		outerGrid.add(compileButton, 4, 0);
		outerGrid.add(runButton, 5, 0);
		outerGrid.add(mainText, 0, 3, 20, 4);
      outerGrid.add(message, 0, 7, 10, 3);

      //GridPane outerGrid = new GridPane(vpane, panre, mainText, message);

      Scene theScene = new Scene(outerGrid, 1200, 800);
      primaryStage.setTitle("The Hutt");
      primaryStage.setScene(theScene);
      primaryStage.show();
   }
   
   public void processRunButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "run!" , ButtonType.OK);
      alert.showAndWait();
   }
	
	
	public void processCompileButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "Compiling!" , ButtonType.OK);
      alert.showAndWait();
      
      try
      {
         proj.compile();
      }
      catch (Exception exc)
      {
         Alert error = new Alert(AlertType.INFORMATION, 
                  "Error processing compile command" , ButtonType.OK);
      }
   }
	
	public void processFileTab(ActionEvent event)
	{
		//Alert alert = new Alert(AlertType.INFORMATION, "FileTab!" , ButtonType.OK);
		//alert.showAndWait();
		
		if (FileTab.getSelectionModel().getSelectedIndex() == 1)
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
         	message.setText("New project created: " + input);
				
				proj = new Project(input);
				flag = true;

      	}
      }
      else if (FileTab.getSelectionModel().getSelectedIndex() == 2)
      {
         // new file 
      }
      else if (FileTab.getSelectionModel().getSelectedIndex() == 3)
      {
         // add file
      }
      else if (FileTab.getSelectionModel().getSelectedIndex() == 4)
      {
         // delete file
      }
		// When adding a new file to open tab, use this code: filesChoice.getItems().add(newFileName); 
		
		
	}
	
	public void processOpenTab(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION, "OpenTab!" , ButtonType.OK);
		alert.showAndWait();
	}

	
   public static void main(String[] args)
   {
      launch(args);
   }
}

