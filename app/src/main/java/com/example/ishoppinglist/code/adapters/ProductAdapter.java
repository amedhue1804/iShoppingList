package com.example.ishoppinglist.code.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.code.database.DataBase;
import com.example.ishoppinglist.code.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private List<Product> products;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = this.products.get(position);

        // Si la vista no ha sido creada, la creamos nosotros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Si la vista ya existe, reutilizamos la vista actual

        // Obtenemos los componentes de la vista

        TextView tvProductName = convertView.findViewById(R.id.tvNameProduct);

        // Asignamos los valores a los componentes

        tvProductName.setText(product.getName());

        // Devolvemos la vista al final
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = DataBase.getProductsNoPending().get(position);

        // Si la vista del dropdown no ha sido creada, la creamos nosotros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.drop_down_product, parent, false);
        }

        // Obtenemos los componentes y modificamos sus valores para mostrarlos en la vista
        TextView tvNoPendingId = convertView.findViewById(R.id.tvNoPendingId);
        TextView tvNoPendingName = convertView.findViewById(R.id.tvNoPendingName);
        TextView tvNoPendingDescription = convertView.findViewById(R.id.tvNoPendingDescription);

        tvNoPendingId.setText(String.valueOf(product.getId()));
        tvNoPendingName.setText(product.getName());
        tvNoPendingDescription.setText(product.getDescription());

        return convertView;
    }
}
