package com.example.ishoppinglist.code.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.database.DataBase;
import com.example.ishoppinglist.code.models.Product;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos los componentes desde la vista
        TextView newProductId = findViewById(R.id.tvIdNewProduct);
        EditText productNameInput = findViewById(R.id.etNewName);
        EditText productDescriptionInput = findViewById(R.id.etNewDescription);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch productStateSwitch = findViewById(R.id.sw1);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch productGlutenSwitch = findViewById(R.id.sw2);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch productLactosaSwitch = findViewById(R.id.sw3);
        Button saveButton = findViewById(R.id.btnSave2);
        Button cancelButton = findViewById(R.id.btnCancel3);

        // Obtenemos el primer ID disponible para asignarlo al nuevo producto y lo mostramos en el TextView
        newProductId.setText(String.valueOf(DataBase.getLastId()));

        // Implementamos la funcionalidad de los botones
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al hacer clic en cancelar, regresamos a la pantalla principal
                Intent backIntent = new Intent(AddProductActivity.this, MainActivity.class);
                Toast message = new Toast(AddProductActivity.this);
                message.setText("Regresando a la página principal...");
                message.show();
                startActivity(backIntent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos un nuevo producto
                Product newProduct = new Product();
                newProduct.setId(Integer.parseInt(newProductId.getText().toString()));
                newProduct.setName(productNameInput.getText().toString());
                newProduct.setDescription(productDescriptionInput.getText().toString());
                newProduct.setState(productStateSwitch.isChecked());
                newProduct.setGluten(productGlutenSwitch.isChecked());
                newProduct.setLactosa(productLactosaSwitch.isChecked());

                // Validamos que el nombre no esté vacío
                if (newProduct.getName().isEmpty()){
                    Toast nameErrorToast = new Toast(AddProductActivity.this);
                    nameErrorToast.setText("El nombre no puede estar vacío");
                    nameErrorToast.show();
                } else if (newProduct.getDescription().isEmpty()){
                    // Validamos que la descripción no esté vacía
                    Toast descriptionErrorToast = new Toast(AddProductActivity.this);
                    descriptionErrorToast.setText("La descripción no puede estar vacía");
                    descriptionErrorToast.show();
                } else {
                    // Agregamos el producto a la base de datos
                    DataBase.addProduct(newProduct, AddProductActivity.this);
                    newProductId.setText(String.valueOf(DataBase.getLastId()));
                    productNameInput.setText("");
                    productDescriptionInput.setText("");
                    productStateSwitch.setChecked(false);
                    productGlutenSwitch.setChecked(false);
                    productLactosaSwitch.setChecked(false);
                    Toast saveToast = new Toast(AddProductActivity.this);
                    saveToast.setText("Producto guardado");
                    saveToast.show();
                    Intent returnIntent = new Intent(AddProductActivity.this, MainActivity.class);
                    startActivity(returnIntent);
                }
            }
        });
    }
}
