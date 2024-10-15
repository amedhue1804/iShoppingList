package com.example.ishoppinglist.code.database;
import android.widget.Toast;

import com.example.ishoppinglist.code.activities.AddProductActivity;
import com.example.ishoppinglist.code.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static List<Product> productsList;

    public static void inicializeList() {
        if (productsList == null) {
            // Si la lista esta vacia, la creamos y le metemos 30 productos
            productsList = new ArrayList<>();

            // Agregamos los 30 productos a la lista
            Product p1 = new Product(1, "Hamburguesa BBQ", "Bien jugosa y con BBQ", true);
            productsList.add(p1);
            Product p2 = new Product(2, "Alitas Picantes", "Para los amantes del picante", false);
            productsList.add(p2);
            Product p3 = new Product(3, "Tacos", "Con carne de res y bien sazonados", true);
            productsList.add(p3);
            Product p4 = new Product(4, "Pizza Hawaiana", "Piña y jamon, clasico polemico", true);
            productsList.add(p4);
            Product p5 = new Product(5, "Ensalada Fresca", "Lechuga, tomate y pepino", false);
            productsList.add(p5);
            Product p6 = new Product(6, "Sushi de Salmon", "Para los fans del sushi", true);
            productsList.add(p6);
            Product p7 = new Product(7, "Paella Mixta", "Mariscos y carne, bien variada", false);
            productsList.add(p7);
            Product p8 = new Product(8, "Burrito", "Con todo lo que quieras", true);
            productsList.add(p8);
            Product p9 = new Product(9, "Espagueti Carbonara", "Con crema y tocino", true);
            productsList.add(p9);
            Product p10 = new Product(10, "Sopa Ramen", "Bien calientita, directo de Japon", false);
            productsList.add(p10);
            Product p11 = new Product(11, "Sandwich de Pollo", "Para esos almuerzos rapidos", true);
            productsList.add(p11);
            Product p12 = new Product(12, "Empanada de Carne", "De carne bien sazonada", true);
            productsList.add(p12);
            Product p13 = new Product(13, "Hot Dog Clasico", "El tipico americano", false);
            productsList.add(p13);
            Product p14 = new Product(14, "Crepe Dulce", "Con Nutella y fresas", true);
            productsList.add(p14);
            Product p15 = new Product(15, "Risotto de Hongos", "Cremoso y delicioso", true);
            productsList.add(p15);
            Product p16 = new Product(16, "Gyro Griego", "Con salsa tzatziki", false);
            productsList.add(p16);
            Product p17 = new Product(17, "Chili con Carne", "Un toque picante y delicioso", true);
            productsList.add(p17);
            Product p18 = new Product(18, "Dumplings", "Rellenos de cerdo", true);
            productsList.add(p18);
            Product p19 = new Product(19, "Pan Bagel", "Con queso crema", false);
            productsList.add(p19);
            Product p20 = new Product(20, "Fideua", "Con mariscos, al estilo español", true);
            productsList.add(p20);
            Product p21 = new Product(21, "Croquetas", "Rellenas de jamon y queso", true);
            productsList.add(p21);
            Product p22 = new Product(22, "Bruschetta", "Pan tostado con tomate y albahaca", false);
            productsList.add(p22);
            Product p23 = new Product(23, "Ramen", "Sopa japonesa con fideos", true);
            productsList.add(p23);
            Product p24 = new Product(24, "Arepa", "Rellena de queso y carne", true);
            productsList.add(p24);
            Product p25 = new Product(25, "Musaka", "Lasaña griega con berenjena", false);
            productsList.add(p25);
            Product p26 = new Product(26, "Fish & Chips", "Clasico ingles con papas fritas", true);
            productsList.add(p26);
            Product p27 = new Product(27, "Pollo Tikka Masala", "Bien especiado y con arroz", true);
            productsList.add(p27);
            Product p28 = new Product(28, "Taco al Pastor", "Taco con carne de cerdo", false);
            productsList.add(p28);
            Product p29 = new Product(29, "Pad Thai", "Fideos tailandeses", true);
            productsList.add(p29);
            Product p30 = new Product(30, "Couscous", "Con verduras y especias", true);
            productsList.add(p30);
        }
    }

    // Metodo que nos da todos los productos pendientes
    public static ArrayList<Product> getProductsPending() {
        ArrayList<Product> productsPending = new ArrayList<>();
        for (Product p : productsList) {
            if (p.isState()) {
                productsPending.add(p);
            }
        }
        return productsPending;
    }

    // Metodo para traer todos los productos que NO estan pendientes
    public static ArrayList<Product> getProductsNoPending() {
        ArrayList<Product> productsNoPending = new ArrayList<>();
        for (Product p : productsList) {
            if (!p.isState()) {
                productsNoPending.add(p);
            }
        }
        return productsNoPending;
    }

    // Metodo para sacar el ultimo ID usado, mas 1, para los nuevos productos
    public static int getLastId() {
        int id = 0;
        for (Product p : productsList) {
            if (p.getId() > id) {
                id = p.getId();
            }
        }
        return id + 1;
    }

    public static void addProduct(Product p, AddProductActivity view) {
        for (Product product : productsList) {
            if (product.getName().equalsIgnoreCase(p.getName())) {
                Toast toast = new Toast(view);
                toast.setText("Este producto ya esta agregado");
                toast.show();
                return;
            }
        }
        Toast toastNew = new Toast(view);
        toastNew.setText("Producto agregado con exito");
        toastNew.show();
        productsList.add(p);
    }

    public static Product getProductbyID(int id) {
        for (Product p : productsList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public static void updateProduct(Product product) {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getId() == product.getId()) {
                productsList.set(i, product); // Reemplaza el producto antiguo con el actualizado
                break; // Sale del bucle una vez que se ha actualizado el producto
            }
        }
    }
}
