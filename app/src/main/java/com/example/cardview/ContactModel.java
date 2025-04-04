package com.example.cardview;

public class ContactModel {

    int img;                 // Ati akti Stracture class
    String name,number;
    ContactModel(int img,String name,String number)
    {
        this.img=img;
        this.name=name;
        this.number=number;
    }
    ContactModel(String name,String number)
    {
        this.name=name;
        this.number=number;
    }
}
