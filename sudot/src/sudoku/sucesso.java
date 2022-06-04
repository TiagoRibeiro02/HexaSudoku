package sudoku;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class sucesso implements Initializable {
    
    @FXML
    public Label Sucesso;
    @FXML
    public Label Timer;
    @FXML
    public Button Menu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    
    public void buttonMenuPressed() throws Exception {
        try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Niveiscene.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.NONE);
                stage.setTitle("Dificuldade");
                stage.setScene(new Scene(root));
                stage.show();
                
                Stage thisStage = (Stage) Sucesso.getScene().getWindow();
                thisStage.close();
                thisStage = null;
            } 
            catch (IOException e) {}
    }
    public void labelreset(String time){
        Timer.setText(time);
    }
    
}
