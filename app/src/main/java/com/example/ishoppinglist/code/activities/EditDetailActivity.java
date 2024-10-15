package com.example.ishoppinglist.code.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.database.DataBase;
import com.example.ishoppinglist.code.models.Product;

public class EditDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Obtenemos los componentes desde el layout XML
        EditText nameEdit = findViewById(R.id.etNameEdit);
        EditText detailsEdit = findViewById(R.id.etDescriptionEdit);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch sw2 = findViewById(R.id.swChangueStatus);
        Button btnCancel = findViewById(R.id.btnCancel4);
        Button btnSave = findViewById(R.id.btnSave3);

        Intent intentDetails = getIntent();// Obtenemos el objeto Product del intent

        int idProduct = (int) ((Intent) intentDetails).getSerializableExtra("productid");
        Product edit = DataBase.getProductbyID(idProduct);

        // Si el producto no es nulo, rellenamos los campos con sus datos
        if (edit != null) {
            nameEdit.setText(edit.getName());
            detailsEdit.setText(edit.getDescription());
            sw2.setChecked(!edit.isState()); // Invertimos el estado para el Switch
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Al hacer clic en cancelar, regresamos a la pantalla principal
                Intent intent = new Intent(EditDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validamos que el nombre no esté vacío
                if (nameEdit.getText().toString().isEmpty()) {
                    Toast.makeText(EditDetailActivity.this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                } else if (detailsEdit.getText().toString().isEmpty()) {
                    Toast.makeText(EditDetailActivity.this, "La descripción no puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si el producto no es nulo, lo actualizamos
                if (edit != null) {
                    edit.setName(nameEdit.getText().toString());
                    edit.setDescription(detailsEdit.getText().toString());
                    edit.setState(!sw2.isChecked()); // Invertimos el estado para guardar
                    DataBase.updateProduct(edit); // Actualizamos el producto en la base de datos
                    Toast.makeText(EditDetailActivity.this, "Producto actualizado con éxito", Toast.LENGTH_SHORT).show();
                    Intent main = new Intent(EditDetailActivity.this, MainActivity.class);
                    startActivity(main);
                }
            }
        });
    }
}