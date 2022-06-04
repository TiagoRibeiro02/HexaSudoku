package sudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.animation.Animation;
import static javafx.animation.Animation.Status.PAUSED;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class primarypage implements Initializable {

    @FXML
    Button button_ZERO;
    @FXML
    Button button_one;
    @FXML
    Button button_two;
    @FXML
    Button button_three;
    @FXML
    Button button_four;
    @FXML
    Button button_five;
    @FXML
    Button button_six;
    @FXML
    Button button_seven;
    @FXML
    Button button_eight;
    @FXML
    Button button_nine;
    @FXML
    Button button_A;
    @FXML
    Button button_B;
    @FXML
    Button button_C;
    @FXML
    Button button_D;
    @FXML
    Button button_E;
    @FXML
    Button button_F;
    @FXML
    Canvas canvas;
    @FXML
    Button pausa;
    @FXML
    Button Menu;
    @FXML
    Label timer;
    @FXML
    Pane whitePane;

    timer time = new timer("00:00:00");
    GameBoard gameboard;
    int player_selected_row;
    int player_selected_col;

    int dif;
    String user;
    
    File file = new File("Tempos.csv");
    
    Animation.Status currentState = RUNNING;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
        time.oneSecondPassed();
        timer.setText(time.getCurrentTime());
    }));
    public primarypage(int k){
        this.dif=k;
    }
	
   /*
    * @param arg0
    * @param arg1
    */ 
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gameboard = new GameBoard(dif);
        GraphicsContext context = canvas.getGraphicsContext2D();
        drawOnCanvas(context);
        player_selected_row = 0;
        player_selected_col = 0;
        timer.setText(time.getCurrentTime());

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public void receiveuser( String user) {
        this.user = user;
    }
    
    private void writeToFile() throws IOException {
        String username = user;
        String Timer = timer.getText();
        BufferedWriter writer;
        
        writer = new BufferedWriter(new FileWriter(file,true));
        
        writer.write(username + "," + Timer + "\n");
        writer.close();
    }

    /*
	 *
	 * @param context pass the GraphicsContext instance of a canvas
     */
    public void drawOnCanvas(GraphicsContext context) {

        context.clearRect(0, 0, 800, 800);
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                int position_y = row * 55 + 2;
                int position_x = col * 55 + 2;
                int width = 46;
                context.setFill(Color.WHITE);
                context.setStroke(Color.BLACK);
                context.setLineWidth(5);
                context.fillRoundRect(position_x, position_y, width, width, 10, 10);
                context.strokeRoundRect(position_x, position_y, width, width, 10, 10);
            }
        }


        context.setStroke(Color.RED);
        context.setLineWidth(5);
        context.strokeRoundRect(player_selected_col * 55 + 2, player_selected_row * 55 + 2, 46, 46, 10, 10);

        int[][] initial = gameboard.getInitial();
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                int position_y = row * 55 + 30;
                int position_x = col * 55 + 20;
                context.setFill(Color.BLACK);
                context.setFont(new Font(20));
                if (initial[row][col] != 0) {
                    switch (initial[row][col]) {
                        case 10:
                            context.fillText("A ", position_x, position_y);
                            break;
                        case 11:
                            context.fillText("B ", position_x, position_y);
                            break;
                        case 12:
                            context.fillText("C ", position_x, position_y);
                            break;
                        case 13:
                            context.fillText("D ", position_x, position_y);
                            break;
                        case 14:
                            context.fillText("E ", position_x, position_y);
                            break;
                        case 15:
                            context.fillText("F ", position_x, position_y);
                            break;
                        case 16:
                            context.fillText("0 ", position_x, position_y);
                            break;
                        default:
                            context.fillText(initial[row][col] + "", position_x, position_y);
                            break;

                    }
                }
            }
        }

        int[][] player = gameboard.getPlayer();
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                int position_y = row * 55 + 30;
                int position_x = col * 55 + 20;
                context.setFill(Color.PURPLE);
                context.setFont(new Font(22));
                if (player[row][col] != 0) {
                    switch (player[row][col]) {
                        case 10:
                            context.fillText("A ", position_x, position_y);
                            break;
                        case 11:
                            context.fillText("B ", position_x, position_y);
                            break;
                        case 12:
                            context.fillText("C ", position_x, position_y);
                            break;
                        case 13:
                            context.fillText("D ", position_x, position_y);
                            break;
                        case 14:
                            context.fillText("E ", position_x, position_y);
                            break;
                        case 15:
                            context.fillText("F ", position_x, position_y);
                            break;
                        case 16:
                            context.fillText("0 ", position_x, position_y);
                            break;
                        default:
                            context.fillText(player[row][col] + "", position_x, position_y);
                            break;

                    }

                }
            }
        }

        if (gameboard.checkForSuccessGeneral() == true) {
            try {
                timeline.stop();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sucesso.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.NONE);
                stage.setTitle("Sucesso");
                stage.setScene(new Scene(root));
                stage.show();
                sucesso sucesso=fxmlLoader.getController();
                sucesso.labelreset(timer.getText());
                
                

                Stage thisStage = (Stage) canvas.getScene().getWindow();
                thisStage.close();
                thisStage = null;
            } catch (IOException e) {
            }
            
            try {
            writeToFile();
            }catch (IOException e) {
            }
            System.out.println(user);
        }

    }

    public void canvasMouseClicked() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                int mouse_x = (int) event.getX();
                int mouse_y = (int) event.getY();

                player_selected_row = (int) (mouse_y / 55);
                player_selected_col = (int) (mouse_x / 55);

                drawOnCanvas(canvas.getGraphicsContext2D());
            }
        });
    }

    public void buttonOnePressed() {
        gameboard.modifyPlayer(1, player_selected_row, player_selected_col);
        
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonTwoPressed() {
        gameboard.modifyPlayer(2, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonThreePressed() {
        gameboard.modifyPlayer(3, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

  
    public void buttonFourPressed() {
        gameboard.modifyPlayer(4, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }


    public void buttonFivePressed() {
        gameboard.modifyPlayer(5, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }


    public void buttonSixPressed() {
        gameboard.modifyPlayer(6, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonSevenPressed() {
        gameboard.modifyPlayer(7, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonEightPressed() {
        gameboard.modifyPlayer(8, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonNinePressed() {
        gameboard.modifyPlayer(9, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonAPressed() {
        gameboard.modifyPlayer(10, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonBPressed() {
        gameboard.modifyPlayer(11, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonCPressed() {
        gameboard.modifyPlayer(12, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonDPressed() {
        gameboard.modifyPlayer(13, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonEPressed() {
        gameboard.modifyPlayer(14, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonFPressed() {
        gameboard.modifyPlayer(15, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonZEROPressed() {
        gameboard.modifyPlayer(16, player_selected_row, player_selected_col);
        drawOnCanvas(canvas.getGraphicsContext2D());
    }

    public void buttonMenuPressed() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Niveiscene.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.setTitle("Dificuldade");
            stage.setScene(new Scene(root));
            stage.show();

            Stage thisStage = (Stage) canvas.getScene().getWindow();
            thisStage.close();
            thisStage = null;
        } catch (IOException e) {
        }
    }

    public void buttonPausaPressed() {
        if (currentState == RUNNING) {
            timeline.stop();
            currentState = PAUSED;
        } else if (currentState == PAUSED) {
            timeline.playFromStart();
            currentState = RUNNING;
        }
        if (whitePane.isVisible()){
            whitePane.setVisible(false);
        } else {
            whitePane.setVisible(true);
        }
    }
}

