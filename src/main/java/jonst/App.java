package jonst;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    private static Scanner inputReader;
    private int openValue = 0;
    private String gameTitle;
    private String logo;

    public static void main( String[] args )
    {
        inputReader = new Scanner(System.in);
        launch();
    }

    public void init(){     //This runs at the very start of the game!
        openValue = 15;
        gameTitle = "The Amazing Race!";
        System.out.print("Message: ");
        //logo = inputReader.nextLine();

    }

    public void stop(){     //This runs when the game quits, even if closed down manually

        System.out.println(openValue);
    }

    @Override
    public void start(Stage myStage) {
        myStage.setTitle(gameTitle);

        Group root = new Group();   //This creates a "root" group

        Scene firstScene = new Scene(root);     //This puts firstScene into the root group

        myStage.setScene(firstScene);       //This passes the firstScene to the Stage

        Canvas canvas = new Canvas(400, 200);       //Canvas object to draw on
        root.getChildren().add(canvas);     //Adds the canvas to root, apparently. We can still use the canvas name to reach it.

        GraphicsContext gc = canvas.getGraphicsContext2D(); //Not a new object; instead snags the canvas object's graphical details and binds it to the name "gc".

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        gc.fillText( "logo", 60, 50 );
        gc.strokeText( "logo", 60, 50 );

        Image logo = new Image("file:src/main/java/jonst/logo.png");    //Note annoying path. Can be amended with a URL string, maybe.
        gc.drawImage(logo, 180,100);

        myStage.show();     //This initiates the window
    }



}
