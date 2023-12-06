package com.example.rentify.ViewController;

import com.example.rentify.Controller.LoginController;
import com.example.rentify.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

public class LoginForm {

    @FXML
    TextField txtUser;
    @FXML
    TextField txtPass;
    @FXML
    Label invalid;
    @FXML
    Button loginButton;

    Users u= new Users();
    LoginController lg = new LoginController();

    public void isSign(ActionEvent e) throws SQLException, IOException {
        u.setUsername(txtUser.getText());
        u.setPassword(txtPass.getText());
        if(lg.isLogin(u))
        {
            Node node = (Node) e.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else
            invalid.setText("Invalid Login. Please try again");

    }
}
