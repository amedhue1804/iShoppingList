package com.example.ishoppinglist.code.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.models.Product;

public class DetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos los componentes de la vista
        TextView titleDetails = findViewById(R.id.tvTittleDetails);
        TextView productIdDetails = findViewById(R.id.tvIdDetails);
        TextView productNameDetails = findViewById(R.id.tvNameDetails);
        TextView productDescriptionDetails = findViewById(R.id.tvDescriptionDetails);
        TextView productStateDetails = findViewById(R.id.tvStateDetails);
        Button editButton = findViewById(R.id.btnEdit);
        Button cancelButton = findViewById(R.id.btnCancel);

        // Obtenemos el intent que inició esta actividad
        Intent intent = getIntent();
        // Recuperamos los datos del intent usando una instancia de Product
        Product product = (Product) intent.getSerializableExtra("product");

        // Si el producto no es nulo, asignamos los valores a los campos correspondientes
        if (product != null) {
            productIdDetails.setText(String.valueOf(product.getId()));
            productNameDetails.setText(product.getName());
            productDescriptionDetails.setText(product.getDescription());

            if (product.isState()) {
                productStateDetails.setText("Pendiente de compra");
            } else {
                productStateDetails.setText("No pendiente");
            }
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos el intent para regresar a la actividad principal
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, EditDetailActivity.class);
                if (product != null) {
                    intent.putExtra("productid", product.getId());
                    startActivity(intent);
                }

            }
        });
    }
}
