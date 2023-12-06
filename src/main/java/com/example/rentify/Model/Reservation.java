package com.example.rentify.Model;

public class Reservation {
    private int Idr;
    private String Details;
    Reservation(){}
    Reservation(int id , String det){
        this.Idr=id;
        this.Details=det;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public int getIdr() {
        return Idr;
    }

    public void setIdr(int idr) {
        Idr = idr;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "Idr=" + Idr +
                ", Details='" + Details + '\'' +
                '}';
    }
    public void AfficherR(){
        System.out.println(toString());
    }

}
