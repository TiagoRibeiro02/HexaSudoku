package sudoku;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class login {

    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    @FXML
    public TextField errorField;
    @FXML
    private PasswordField hiddenPasswordTextField;
    @FXML
    private CheckBox showPassword;
    
    //.csv (ficheiro que guarda informação guardada por virgulas
    File file = new File("loginData.csv");
    
    HashMap<String, String> loginInfo = new HashMap<>();
    
    Encryptor encryptor = new Encryptor();
    
    @FXML
    void changeVisibility(ActionEvent event) {
        if(showPassword.isSelected()){
            passwordTextField.setText(hiddenPasswordTextField.getText());
            passwordTextField.setVisible(true);
            hiddenPasswordTextField.setVisible(false);
            //return;
        } else {
        hiddenPasswordTextField.setText(passwordTextField.getText());
        hiddenPasswordTextField.setVisible(true);
        passwordTextField.setVisible(false);
        }
    }
    
    @FXML
    private String getPassword() {
        if(passwordTextField.isVisible()){
            return passwordTextField.getText();
        } else {
            return hiddenPasswordTextField.getText();
        }
    }
    
    @FXML
    void loginHandler(ActionEvent event) throws IOException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String username = usernameTextField.getText();
        String password = getPassword();
        updateUserAndPass();
        
        String encryptedPass = loginInfo.get(username);
        if (encryptor.encryptString(password).equals(encryptedPass)){
            System.out.println("sucss login");
            //mudar de scene
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Niveiscene.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.NONE);
                stage.setTitle("Dificuldade");
                stage.setScene(new Scene(root));
                stage.show();
                
                Niveisscene niveis = fxmlLoader.getController();
                niveis.receiveuser(username);
                
                Stage thisStage = (Stage) usernameTextField.getScene().getWindow();
                thisStage.close();
                thisStage = null;
            } 
            catch (IOException e) {}
                
            
        }
        else {
            errorField.setVisible(true);
        }
        
    }
    
    private void updateUserAndPass() throws IOException {
        Scanner scanner = new Scanner(file);
        loginInfo.clear();
        loginInfo = new HashMap<>();
        while (scanner.hasNext()){
            String[] userAndPass = scanner.nextLine().split(",");
            loginInfo.put(userAndPass[0], userAndPass[1]);
        }
    }
    
    private void writeToFile() throws IOException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String username = usernameTextField.getText();
        String password = getPassword();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        
        writer.write(username + "," + encryptor.encryptString(password) + "\n");
        writer.close();
    }
    
    @FXML
    void createAccount(ActionEvent event) throws IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        writeToFile();
    }
    
        
}
