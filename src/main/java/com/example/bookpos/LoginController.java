package com.example.bookpos;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label welcomeText;

//    private Main main;
//    public void setMain(Main main){
//        this.main = main;
//    }

    @FXML
    private void handleLogin(){
        String usernameInput = usernameField.getText();
        String passwordInput = passwordField.getText();

        if (checkCredentials(usernameInput, passwordInput)) {
            System.out.println("Login successful!");
            openNewWindow();
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    private boolean checkCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("userCredentials.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String storedUsername = parts[0].trim();
                    String storedPassword = parts[1].trim();
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void openNewWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("menuView.fxml"));
            Parent root = loader.load();

            Stage loginScene = (Stage) usernameField.getScene().getWindow();
            loginScene.close();

            Stage menuScene = new Stage();
            menuScene.setTitle("Menu");
            menuScene.setScene(new Scene(root, 1000, 500));
            menuScene.initModality(Modality.APPLICATION_MODAL);
            menuScene.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
