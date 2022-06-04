package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //inicio do sudoku na página de login
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(login);
        stage.setTitle("login");
        stage.setScene(scene);
        stage.show();
    }
    /*
     * @param args designed without arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
