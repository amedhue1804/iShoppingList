package com.example.ishoppinglist.code.models;

import java.io.Serializable;

public class Product implements Serializable {

    // Aqui van los atributos del producto
    private int id;
    private String name;
    private String description;
    private boolean state;

    // Constructor por defecto
    public Product (){
        super();
    }

    // Constructor con todos los parametros del producto
    public Product (int id, String name, String description, boolean state){
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
    }

    // Getters y setters para cada atributo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    // Metodo toString para imprimir el objeto Product como un string
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }
}
