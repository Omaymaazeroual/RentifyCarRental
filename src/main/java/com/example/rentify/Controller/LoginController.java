package com.example.rentify.Controller;

import com.example.rentify.Model.ConnexDB;
import com.example.rentify.Model.Users;


import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {


    Statement st;
     public void LoginControl(Users u) throws SQLException {
        try {
            if(u.getIsAdmin()){
                System.out.println("L admin existe dans la DB");
                System.out.println("Logged In ! ");
            }
            else{
                System.out.println("Invalid Login! Please try again");
            }
        } catch (Exception e) {
             throw new RuntimeException(e);
        }

     }

     public boolean isLogin(Users u) throws SQLException
     {
         st = ConnexDB.GetConnexion().createStatement();
         ResultSet res= st.executeQuery("select * from users where username ='" +u.getUsername()+ "' and password ='" +u.getPassword()+"'");
         if(res.next())
             return true;
         return false;
     }
}