package com.example.ishoppinglist.code.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.adapters.ProductAdapter;
import com.example.ishoppinglist.code.database.DataBase;
import com.example.ishoppinglist.code.models.Product;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_list);

        // Ajustamos los espacios para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos los componentes de la interfaz desde el layout
        TextView titleNoPending = findViewById(R.id.tvProductsPending);  // Titulo para los productos pendientes
        Spinner spProductsNoPending = findViewById(R.id.spProductsnoPending);  // Spinner para productos no pendientes
        Button btnCancel = findViewById(R.id.btnCancel2);  // Botón de cancelar
        Button btnSave = findViewById(R.id.btnSave1);  // Botón de guardar

        // Creamos el adaptador y lo asociamos con el spinner
        ProductAdapter productAdapter = new ProductAdapter(ShoppingListActivity.this, 0, DataBase.getProductsNoPending());
        spProductsNoPending.setAdapter(productAdapter);

        // Funcionalidades de los botones
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(ShoppingListActivity.this, MainActivity.class);
                startActivity(intentBack);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product selectedProduct = (Product) spProductsNoPending.getSelectedItem();  // Obtenemos el producto seleccionado
                selectedProduct.setState(true);  // Marcamos el producto como pendiente
                Intent intentMain = new Intent(ShoppingListActivity.this, MainActivity.class);

                // Mostramos un mensaje en forma de toast
                Toast.makeText(ShoppingListActivity.this, "Este producto ahora estará pendiente. Gracias por el cambio.", Toast.LENGTH_SHORT).show();

                startActivity(intentMain);  // Regresamos a la actividad principal
            }
        });
    }
}
