package jonst;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App extends Application {
    private static Scanner inputReader;
    private int openValue = 0;
    private String gameTitle;
    private String logo;

    public static void main(String[] args) {
        inputReader = new Scanner(System.in);
        launch();
    }

    public void init() {     //This runs at the very start of the game!
        openValue = 15;
        gameTitle = "The Amazing Race!";
        System.out.print("Message: ");
        //logo = inputReader.nextLine();

    }

    public void stop() {     //This runs when the game quits, even if closed down manually

        System.out.println(openValue);
        inputReader.close();
    }

    @Override
    public void start(Stage myStage) {
        myStage.setTitle(gameTitle);

        Group root = new Group();   //This creates a "root" group

        Group firstDisplay = new Group(root);   //This creates firstDisplay and puts it in the "root" group. We can build trees this way.
        //Note: root cannot be used to hold scenes now.

        Scene firstScene = new Scene(firstDisplay);     //This puts firstScene into the root group

        myStage.setScene(firstScene);       //This passes the firstScene to the Stage

        Canvas canvas = new Canvas(800, 600);       //Canvas object to draw on
        firstDisplay.getChildren().add(canvas);     //Adds the canvas to firstDisplay, apparently. We can still use the canvas name to reach it.




        //Event handling!
        ArrayList<String> input = new ArrayList<String>();

        firstScene.setOnKeyPressed(             //Pretty clever! It keeps a list of all keys that are pressed at the moment!
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        firstScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });





        GraphicsContext gc = canvas.getGraphicsContext2D(); //Not a new object; instead snags the canvas object's graphical details and binds it to the name "gc".


        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(theFont);
        //gc.strokeText( "logo", 70, 60 );
        //gc.fillText( "logo", 60, 50 );

        //gc.setStroke(Color.GRAY);
        //gc.strokeText("Hello", 70, 100);
        //We can write as much new text as we want, anywhere we want; changing the colors and whatnot doesn't change already written stuff.


        //Image logo = new Image("file:src/main/java/jonst/logo.png");    //Note annoying path. Can be amended with a URL string, maybe.
        //gc.drawImage(logo, 180,100);


        Image ball = new Image("file:src/main/java/jonst/ball.jpg");

        Image smiley = new Image("file:src/main/java/jonst/smiley.jpg", 50, 50, true, true);



        new AnimationTimer() {                           //This creates


            final long startNanoTime = System.nanoTime();

            double xDirection = 1;  //If positive, move to the right
            double yDirection = 1;  //If positive, move downwards
            double startingX = 0;
            double startingY = 0;
            double ballX = startingX;
            double ballY = startingY;

            double speed = 5;

            double smileyX = 50;
            double smileyY = 50;

            public void handle(long currentNanoTime) {

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


                //gc.strokeText("Wow!", Math.random() * canvas.getWidth(), Math.random() * canvas.getHeight());   //Draw the word "Wow!" at random locations


                if (input.contains("LEFT"))
                    smileyX-= 5;
                if (input.contains("RIGHT"))
                    smileyX+= 5;
                if (input.contains("UP"))
                    smileyY-= 5;
                if (input.contains("DOWN"))
                    smileyY+= 5;

                //double t = (currentNanoTime - startNanoTime) / 100_000_000.0;

                ballX += xDirection * speed;
                ballY += yDirection * speed;

                if (ballX > canvas.getWidth() - ball.getWidth()) {
                    xDirection = -1;
                }
                if (ballX < 0) {
                    xDirection = 1;
                }

                if (ballY > canvas.getHeight() - ball.getHeight()) {
                    yDirection = -1;
                }
                if (ballY < 0) {
                    yDirection = 1;
                }

                gc.drawImage(ball, ballX, ballY);
                gc.drawImage(smiley, smileyX, smileyY);


                //System.out.println(ballX+", "+ ballY);

            }

        }.start();

        myStage.show();     //This initiates the window
    }


}
