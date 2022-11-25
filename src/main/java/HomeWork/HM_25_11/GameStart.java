package HomeWork.HM_25_11;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class GameStart extends Application {

    public static final int width = 600;
    public static final int height = 600;
    public static final int r = 10;
    private static final int step = 5;

    private Pane root;
    private Text text;
    private Circle circle;
    private Random random;
    private Rectangle rectangle;
    private Orientation orientation;
    private Integer score = 0;






    @Override
    public void start(Stage stage) throws Exception {

        root = new Pane();
        root.setPrefSize(width,height);
        random = new Random();
        orientation = Orientation.UP;
        newCircle();

        newRectangle();
        Text();
        Runnable runnable = () -> {
            try {
                for (;;){
                    move();
                    Thread.sleep(100);
                }

            }catch (InterruptedException e){

            }
        };

        Scene scene = new Scene(root);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP){
                rectangle.setY(rectangle.getY()-step);
            }
            else if (code == KeyCode.DOWN){
                rectangle.setY(rectangle.getY()+step);
            }
            else if (code == KeyCode.LEFT){
                rectangle.setX(rectangle.getX()-step);
            }
            else if (code == KeyCode.RIGHT){
                rectangle.setX(rectangle.getX()+step);
            }
        });

        stage.setTitle("Game Snake");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();

    }

    public static void main(String[] args) {
        launch(args);
    }



    private void Text(){
        text = new Text(15,15,"score = "+score);
        text.setFill(Color.BLACK);
        root.getChildren().add(text);
    }

    private boolean encounter(){
        return circle.intersects(rectangle.getBoundsInLocal());
    }

    private void step(){
        if (orientation == Orientation.UP){
            orientation = Orientation.UP;
        }
        else if (orientation == Orientation.DOWN){
            orientation = Orientation.DOWN;
        }
        else if (orientation == Orientation.LEFT){
            orientation = Orientation.LEFT;
        }
        else if (orientation == Orientation.RIGHT){
            orientation = Orientation.RIGHT;
        }
    }
    private void move(){
        Platform.runLater(() -> {
            step();
            fieldRestriction();
            if (encounter()){
                score++;
                text.setText("score = "+ score);

                changes();
                root.getChildren().remove(circle);
                newCircle();
                if (score == 16){
                    gameOver();
                }
            }
        });

    };

    public Node newRectangle(){
        rectangle = new Rectangle((int) 300.0d, (int) 300.0d, (int) 20.0d, (int) 20.0d);
        root.getChildren().add(rectangle);

        return null;
    }
    private   void fieldRestriction(){
        if (rectangle.getX()<0){
            rectangle.setX(GameStart.width);
        } else if (rectangle.getX()>GameStart.width) {
            rectangle.setX(0);
        }
        if (rectangle.getY()<0){
            rectangle.setY(GameStart.height);
        } else if (rectangle.getY()>GameStart.height) {
            rectangle.setY(0);
        }
    }
    private void changes(){
        rectangle.setHeight(rectangle.getHeight()+5);
        rectangle.setWidth(rectangle.getWidth()+5);

    }

    private void gameOver(){
        text.setText("Победа, вы набрали 10 очков");
        root.getChildren().remove(rectangle);
        root.getChildren().add(newRectangle());
        score = 0;
    }

    private void newCircle(){
        circle = new Circle(random.nextInt(GameStart.width) ,random.nextInt(GameStart.height),GameStart.r);
        circle.setFill(Color.RED);
        root.getChildren().add(circle);
    }

}
