package com.example.contacts_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 50, message = "Имя не длиннее 50 символов")
    private String name;

    @NotBlank(message = "Телефон не может быть пустым")
    private String phone;
    public Contact(){}
    public Contact(Integer id, String name, String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public Integer getId(){return id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}

    @Override
    public String toString(){
        return id + ". " + name + " | " + phone;
    }
}
