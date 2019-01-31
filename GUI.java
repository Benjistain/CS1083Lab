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
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class TextAndButton extends Application
{
   // here you put the declaration of the controls needed
   Button compileButton;
	Button runButton;
	TextArea mainText;
	Label message;
	Label bar;
   
   public void start(Stage primaryStage)
   {
      // here you put the code to indicate what the window will look like
      Font mainFont = new Font("courrier", 24);
		
		bar = new Label();
      bar.setFont(mainFont);
      
      MenuBar menuBar = new MenuBar();
		
		Menu menuLetter = new Menu("File");      
		MenuItem aMenu = new MenuItem("new project");
		MenuItem bMenu = new MenuItem("add");
		MenuItem dMenu = new MenuItem("delete");
		
      //aMenu.setOnAction(this::processAmenu);
		aMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
      			processNewButton(e);
            }
        });
		  
		 //aMenu.setOnAction(this::processAmenu);
		bMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
      			processAddButton(e);
            }
        });
		  //aMenu.setOnAction(this::processAmenu);
		dMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
      			processDeleteButton(e);
            }
        });
		
      menuLetter.getItems().addAll(aMenu, bMenu, dMenu);
      
      Menu menuAction = new Menu("Open");      
      MenuItem clearMenu = new MenuItem("where files are");
      //clearMenu.setOnAction(this::processClearMenu);
		clearMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
      			processOpenButton(e);
            }
        });
      menuAction.getItems().addAll(clearMenu);
      
      menuBar.getMenus().addAll(menuLetter, menuAction);
      
      VBox vpane = new VBox(menuBar, bar);
      
		mainText = new TextArea();
      mainText.setFont(mainFont);
      mainText.setPrefRowCount(10);
      mainText.setPrefColumnCount(50);
      mainText.setWrapText(true);
		
		message = new Label("output");
      message.setFont(mainFont);
		
      compileButton = new Button("compile");
      compileButton.setFont(mainFont);
      //compileButton.setOnAction(this::processButton);
		compileButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
               processCompileButton(e);
            }
        });
		  
		runButton = new Button("run");
      runButton.setFont(mainFont);
      //runButton.setOnAction(this::processButton);
		runButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
               processRunButton(e);
            }
        });
		  
		FlowPane panre = new FlowPane(compileButton, runButton);

		        
      VBox pane = new VBox(vpane, panre, mainText, message);
      Scene theScene = new Scene(pane, 1200, 800);
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
                     "compile!" , ButtonType.OK);
      alert.showAndWait();
   }
	
	 
   public void processNewButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "new project!" , ButtonType.OK);
      alert.showAndWait();
   }
	
	public void processDeleteButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "delete!" , ButtonType.OK);
      alert.showAndWait();
   } 
   public void processAddButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "add!" , ButtonType.OK);
      alert.showAndWait();
   }
	
	public void processOpenButton(ActionEvent event)
   {
      Alert alert = new Alert(AlertType.INFORMATION, 
                     "open!" , ButtonType.OK);
      alert.showAndWait();
   }

   public static void main(String[] args)
   {
      launch(args);
   }
}

