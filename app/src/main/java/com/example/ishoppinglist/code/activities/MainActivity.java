package com.example.ishoppinglist.code.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.adapters.ProductAdapter;
import com.example.ishoppinglist.code.database.DataBase;
import com.example.ishoppinglist.code.models.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ajustamos los espacios para las barras del sistema (barra de estado, barra de navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos los componentes de la interfaz desde el layout
        TextView tvTitle = findViewById(R.id.TittleApp);  // Titulo de la app
        ListView lvProducts = findViewById(R.id.LvProducts);  // ListView para mostrar los productos
        Button btnAddNewProduct = findViewById(R.id.btnnew);  // Botón para agregar productos nuevos
        Button btnAddPendingProduct = findViewById(R.id.btnPending);  // Botón para productos pendientes

        // Inicializamos la lista de productos en la base de datos
        DataBase.inicializeList();

        // Creamos el adaptador y lo asignamos al ListView
        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, 0, DataBase.getProductsPending());
        lvProducts.setAdapter(productAdapter);

        // Manejamos los eventos de clic para los productos en el ListView
        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = DataBase.getProductsPending().get(position);
            Intent intentDetail = new Intent(MainActivity.this, DetailActivity.class);
            intentDetail.putExtra("product", selectedProduct);
            startActivity(intentDetail);
        });

        // Configuramos el OnClickListener para los botones usando if-else
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Usamos if-else en lugar de switch
                if (v.getId() == R.id.btnPending) {
                    // Vamos a la actividad de productos pendientes
                    startActivity(new Intent(MainActivity.this, ShoppingListActivity.class));
                } else if (v.getId() == R.id.btnnew) {
                    // Vamos a la actividad para agregar un nuevo producto
                    startActivity(new Intent(MainActivity.this, AddProductActivity.class));
                }
            }
        };

        // Asignamos el OnClickListener a los botones
        btnAddPendingProduct.setOnClickListener(buttonClickListener);
        btnAddNewProduct.setOnClickListener(buttonClickListener);
    }
}
