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
    /*
    data class ContactModel(
    val img: Int? = null,  // Nullable, since not all constructors provide an image
    val name: String,
    val number: String
)
     */
}
