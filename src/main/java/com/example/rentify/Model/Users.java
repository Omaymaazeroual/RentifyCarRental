package com.example.rentify.Model;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static com.example.rentify.Model.ConnexDB.*;


public class Users {
    private String username; // if username==root -> admin else user
    private String password;
    private String CIN;
    private String prenom;
    private String nom ;
    private int age ;
    private int N_permis;
    private Date date_finVali;
    private boolean isAdmin=false;

    public Users(){}
    Users(String username , String CIN , String password , String prenom , String nom , int age , int N_permis , Date date_finV){
        this.username=username;
        this.CIN=CIN;
        this.prenom=prenom;
        this.nom=nom;
        this.age=age;
        this.password=password;
        this.N_permis=N_permis;
        this.date_finVali= date_finV;
        this.isAdmin=false;
    }
    // constructeur d admin
    Users(String username , String password){
        if(username =="root" && password=="root"){
            this.username="root";
            this.password="root";
            this.prenom="";
            this.CIN="";
            this.N_permis= 0;
            this.date_finVali=null;
            this.isAdmin=true; // if true --> admin
        }

    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(isAdmin==false){
            if(age>18)
            {
            this.age = age;
            }else
                this.age=18;
        }
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        if(isAdmin==false)
            this.CIN = CIN;
    }

    public Date getDate_finVali() {
        return date_finVali;
    }

    public void setDate_finVali(Date nvdate) {
        if(isAdmin==false ) { // uen autre condition sur la date
            this.date_finVali=nvdate;
    }}

    public int getN_permis() {

        return N_permis;
    }

    public void setN_permis(int n_permis) {
        if(isAdmin==false) // une autre condiiton :  selon la date de permis si il est perime
            N_permis = n_permis;
    }

    public String getPassword() {
        return password; // une recherche dans la DB si le mot de passe existe
    }

    public void setPassword(String password) {
        if(isAdmin==false )
            this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if(isAdmin==false)
            this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUsername(String username) {
        this.username = username; // une recherche dans la base de donne si username n existe pas
    }

    public String getUsername() {
        return username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    // fct qui retourne l ensemble des infos //
    public  static void ReturnInfos() throws SQLException{
        String username=rs.getString("username");
        String CIN = rs.getString("CIN");
        String prenom = rs.getString("prenom");
        String nom = rs.getString("nom");
        int age = rs.getInt("age");
        String password = rs.getString("password");
        String N_permis = rs.getString("N_permis");
        Date date_finVali = rs.getDate("date_finVali");
        System.out.println( "le nom du client : " + nom +"\n"+ " le Prenom du client : " + prenom + "\n"+ "Le Cin du Client :" + CIN + "\n"+ "L'age du client :  "+ age+  "\n" + " Le numero de permis : "+ N_permis +"\n" +" La date de fin de validite du permis :"  + date_finVali);
        System.out.println();
    }

// methode d admin pour voir tous les clients de l application : mzyana :) //
    public  void DisplayClients() throws SQLException {
        if(isAdmin) // cad isAdmin==true//
        {
            try {
                GetConnexion();//cnx avec la base de donne
                String query="Select * from users WHERE username != 'root' AND password != 'root' "; // pr ne pas selectionner l admin
                // open statement//
                state=mycnx.createStatement();
                rs = state.executeQuery(query);
                while(rs.next()){
                    ReturnInfos();
                }
            }catch (SQLException ex){
                System.out.println(ex);
            }

        }
        Deconnexion();
    }

    // methode d admin pour rechercher un client par CIN mzyana :) //
    public void SearchClient (String cin ) throws SQLException{
        try {
            if(isAdmin)
            {
            GetConnexion(); // faire la cnx avec DB
            String query = ("SELECT * FROM users WHERE CIN = ? ");
            //prep.clearParameters();
            prep = mycnx.prepareStatement(query);
            prep.setString(1, cin);
            rs = prep.executeQuery();
            while (rs.next())
            {
                String CIN = rs.getString("CIN");
                if (CIN.equals(cin)) // on compare les deux chaines de caracteres //
                {
                    ReturnInfos();
                }
                else
                {
                    System.out.println("Le Cin que vous avez entrez ne correspond a aucun client ! ");
                }
            }
            }
        } catch (SQLException sq )
        {
            System.out.println(sq);
        }
        Deconnexion();


    }








// log in  : check if user exists ou nn dans la BD si oui log in si nn msg d erreur // 8adi tkoun f login.controller
  //  public void Login(Users u) throws SQLException{
//        try {
//            if(u.isAdmin=true){
//                System.out.println("L admin existe dans la DB");
//                System.out.println("Logged In ! ");
//            }
//            else{
//
//            }
//        }

    //}
// fct qui checke if user exist or no //
//    public Boolean checkUser(Users u ) throws SQLException{
//        boolean check = false;
//        if(u.isAdmin=true){
//            System.out.println("L admin existe dans la DB");
//            check=true;
//            return check;
//        }
//        try {
//            GetConnexion();
//            prep=mycnx.prepareStatement("SELECT * FROM users WHERE CIN =? ");
//            //prep.clearParameters();
//            String cin = u.getCIN();
//            prep.setString(1,cin);
//            rs = prep.executeQuery();
//            while (rs.next())
//            {
//                String CIN = rs.getString("CIN");
//                if (CIN.equals(cin)) // on compare les deux chaines de caracteres //
//                {
//                    System.out.println("Cet utilisateur existe ! ");
//                    check=true;
//                    return check;
//                }
//                else{
//                    System.out.println("Cet utilsateur n existe pas dans la DB ! ");
//                    check=false;
//                    return check;
//                }
//            }
//
//        }catch (SQLException ex){
//            System.out.println(ex);
//        }
//
//
//        return check;
//    }

    public static void main(String[] args) throws SQLException {
        Users u=new Users("root","root");
//        Users uu = new Users("zineb","");
//        u.DisplayClients();
//        u.SearchClient("1234");
//        Users s2= new Users("mouad" , "D4092" , "222","MOUAD","qabouch",24,4378 ,new Date(2022,9,12));
//        u.checkUser(s2);
    }

}




    // checker si username existe deja lors de l 'inscription  //
//    public  boolean CheckUsername(String username){
//
//          boolean checkUser=false;
//            String query=" SELECT 'username' FROM users  WHERE 'username'= ?";
//            try {
//                GetConnexion();
//                prep=mycnx.prepareStatement(query);
//                prep.setString(1,username);
//                rs=prep.executeQuery();
//                while(rs.next()){
//                    if(rs.getString("username")==username){
//                    System.out.println("ce nom d'utilisateur n existe pas vous pouvez l utiliser");
//
//                }
//                else{
//                    System.out.println("ce nom d'utilisateur est deja existant !");
//                    checkUser=true;
//                }
//            }
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//            return checkUser;
//
//        }
//
//
// // on peut modifier l age , CIN , prenom , nom de user , username POUR USER si il  existe  dans notre table seulement
//    // pn peut modifier N permis et la date de fin de validite ssi le permis est perime //
//
//

    // methode pour modifier le mot d pass //
//    public void ModifierMotdpasse(String Newpwd){
//        if(isAdmin==false){
//            if()
//        }
//    }






//    public void logOut(){
//            this.username="";
//            this.password="";
//        System.out.println("logged out !");
//
//    }
//        public void FaireRes(){};
//        public void ModifierReserv(){};
//        public void AnnulerReser(){};
//        public void RercherVoiture(){};
//        public void Payer(){};
//        public void AjouterFeedBack(){};
//
//    public void Afficher(){
//        if(isAdmin){
//            System.out.println("Vous etes l admin de cette App");
//        }else{
//            System.out.println("Vous etes le client de cette app ");
//            System.out.println(toString());
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return "Users{" +
//                "username='" + getUsername() + '\'' +
//                ", password='" + password + '\'' +
//                ", CIN='" + getCIN() + '\'' +
//                ", prenom='" + getPrenom() + '\'' +
//                ", nom='" + getNom()+ '\'' +
//                ", age=" + getAge() +
//                ", N_permis=" + getN_permis() +
//                ", date_finVali=" + getDate_finVali()
//                ;
//    }
//
//
//
//    public static void main(String[] args) {
//        Users s1= new Users("root" , "root");
////        GetConnexion();
////        s1.Afficher();
//        Users s2 = new Users("meryemEL" ,"D583016" , "1234" , "meryem","elhajoui",21 , 1233, new Date(2022,02,21));
////        s2.Afficher();
//        s1.DisplayClients();

//        Users s2= new Users("mouad" , "D4092" , "222","MOUAD","qabouch",24,4378 ,new Date(2022,06,23));
//       s2.Afficher();
//       s1.DisplayClients();
//        s1.CheckUser("rimael","123");
//        s1.CheckUsername("mimiel");






//    }}



