package com.example.rentify.Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static com.example.rentify.Model.ConnexDB.*;

public class Voiture {
    private String MatriculeV;
    private Date dateVisite;
    private Date dateAssu;
    private String Marque;
    private String Modele;
    private Double PrixParjr;
    private String imageV;

    Voiture(){}
    Voiture(String Mtv  ,  Date dv , Date da ,String marque , String Modele , Double price ,String img){
        this.MatriculeV=Mtv;
        this.dateVisite=dv;
        this.dateAssu=da;
        this.Marque=marque;
        this.Modele=Modele;
        this.PrixParjr=price;
        this.imageV=img;
    }

    public Date getDateAssu() {
        return dateAssu;
    }

    public void setDateAssu(Date dateAssu) {
        this.dateAssu = dateAssu;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public String getImageV() {
        return imageV;
    }

    public void setImageV(String imageV) {
        this.imageV = imageV;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public String getMatriculeV() {
        return MatriculeV;
    }

    public void setMatriculeV(String matriculeV) {
        this.MatriculeV = matriculeV;
    }

    public String getModele() {
        return Modele;
    }

    public void setModele(String modele) {
        Modele = modele;
    }

    public Double getPrixParjr() {
        return PrixParjr;
    }

    public void setPrixParjr(Double prixParjr) {
        PrixParjr = prixParjr;
    }






// ajouter une voiture dans la base de donne , une condition , il faut que cette voiture n existe pas deja dans DB   //
    public void AjouterV(String matricule , String datev , String dateA, String marque ,String Modele ,  Double prx, String img) throws SQLException {
        try {
            GetConnexion();
            String qr = "SELECT MatriculeV FROM voiture WHERE MatriculeV = ?";
            prep=mycnx.prepareStatement(qr);
            prep.setString(1,matricule );
            rs = prep.executeQuery();
            if(rs.next()){
                String matri=rs.getString("MatriculeV");
                if(!matri.equals(matricule)){
                    System.out.println("Ce matricule n existe pas dans DB , donc on peut l ajouter ");
                    PreparedStatement prep=mycnx.prepareStatement("INSERT into voiture ('MatriculeV' , `DateVisite`, `DateAssu`, `Marque`, `Modele`, `PrixParJour`, `image`)" + " VALUES (?,?,?,?,?,?,?)");
                    prep.setString(1,matricule);
                    prep.setString(2,datev);
                    prep.setString(3,dateA);
                    prep.setString(4,marque);
                    prep.setString(5,Modele);
                    prep.setDouble(6,prx);
                    prep.setString(7,img);

                }
                else {
                    if(matri.equals(matricule)){
                        System.out.println("Ce matricule existe deja dans la base de donne ! vous ne pouvez pas ajouter cette voiture ");
                    }
                }
            }


        }catch (SQLException e){
            System.out.println(e);
        }
        Deconnexion();
    }
    // Methode pour modifier les champs voulues dans DB mzyana :))  5asha gha nt2kd meli kninserer haja jdida mktzadsh I m gonna fix it //

        public void ModifierVoiture(String matricule , String datev, String dateA,double PrixPj) throws SQLException {
        try {
            GetConnexion();
            // on va s assurer si matricule existe //
            String qr = "SELECT * FROM voiture WHERE MatriculeV=? ";
            prep=mycnx.prepareStatement(qr);
            prep.setString(1,matricule );
            System.out.println("Hey");
            rs = prep.executeQuery();

            while(rs.next()){
                System.out.println("Hey");
                String matri=rs.getString("MatriculeV");
                if(matri.equals(matricule)) // si le maricule existe on va modifier les infos //
                {
                    String query="UPDATE voiture SET DateVisite=? , DateAssu =? , PrixParJour=? WHERE MatriculeV=?";
                    prep=mycnx.prepareStatement(query);
                    prep.setString(4,matri);
                    prep.setString(1,datev);
                    prep.setString(2,dateA);
                    prep.setDouble(3,PrixPj);
                    int n = prep.executeUpdate();
                    System.out.println("Cette voiture existe dans la base de donne , elle est bien modifie !");
                }
                else{
                    System.out.println("Cette voiture n existe pas dans la base de donne ");
                }
            }


        }catch (SQLException sq){
            System.out.println(sq);
        }

        }

// methode pour supprimer Voiture  mzyana :) //
    public void Supprimer(String matriculeV) throws SQLException {
        try {
            GetConnexion();
            String qr = "SELECT * FROM voiture WHERE MatriculeV=? ";
            prep=mycnx.prepareStatement(qr);
            prep.setString(1,matriculeV );
            rs = prep.executeQuery();

            while(rs.next()){

                String matri=rs.getString("MatriculeV");
                if(matri.equals(matriculeV)) // si le maricule existe on va supprimerVoiture //
                {
                    String quee="DELETE from voiture WHERE MatriculeV=?";
                    prep= mycnx.prepareStatement(quee);
                    prep.setString(1,matri);
                    prep.executeUpdate();
                    System.out.println("La voiture est supprime avec succes ! ");

                }else{
                    System.out.println("Matricule n existe pas , pas de suppression ! ");
                }
            }


        }catch (SQLException sq){
            System.out.println(sq);
        }

    }







    public static void main(String[] args) throws SQLException {
        Voiture v = new Voiture();
//        v.ModifierVoiture("A12438-32","2019-11-1" , "2019-2-21",1000.78);
//        v.Supprimer("B12390-45");
        v.AjouterV("B12438-32", "2022-02-21","2002-3-21","BWM serie E 12","BWM",1289.56,"urlImg");

    }
}
