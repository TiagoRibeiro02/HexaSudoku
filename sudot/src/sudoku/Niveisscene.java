package sudoku;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Niveisscene implements Initializable {

    @FXML
    Button button_Facil;
    @FXML
    Button button_Dificil;
    @FXML
    Button button_Medio;
    @FXML
    Label label1;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    int dif;
    String user;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
    
    public void receiveuser(String user){
        this.user = user;
    }

    //passagem de scenes com dificuldade
    public void buttonFacilPressed(ActionEvent event) throws Exception {
        dif=1;
        primarypage primarypage=new primarypage(dif);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("primarypage.fxml"));
        loader.setController(primarypage);
        root=loader.load();
        
        primarypage.receiveuser(user);
        
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void buttonMedioPressed(ActionEvent event) throws IOException{
        dif=2;
        primarypage primarypage=new primarypage(dif);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("primarypage.fxml"));
        loader.setController(primarypage);
        root=loader.load();
        
        primarypage.receiveuser(user);
        
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void buttonDificilPressed(ActionEvent event) throws IOException {
        dif=3;
        primarypage primarypage=new primarypage(dif);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("primarypage.fxml"));
        loader.setController(primarypage);
        root=loader.load();
        
        primarypage.receiveuser(user);
        
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
