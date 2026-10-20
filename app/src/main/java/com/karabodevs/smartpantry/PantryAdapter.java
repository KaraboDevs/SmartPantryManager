package com.karabodevs.smartpantry;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.VH> {
    private List<PantryItem> items = new ArrayList<>();
    private final DatabaseHelper db;
    private final Fragment fragment;

    public PantryAdapter(DatabaseHelper db, Fragment fragment) {
        this.db = db; this.fragment = fragment;
    }

    public void setItems(List<PantryItem> items) {
        this.items = items; notifyDataSetChanged();
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pantry, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        PantryItem item = items.get(position);
        h.name.setText(item.getName());
        h.qty.setText((int)item.getQuantity() + " " + item.getUnit());

        h.edit.setOnClickListener(v -> {
            Intent i = new Intent(fragment.getContext(), AddEditItemActivity.class);
            i.putExtra("item_id", item.getId());
            fragment.startActivity(i);
        });

        h.del.setOnClickListener(v -> {
            db.deletePantryItem(item.getId());
            fragment.onResume(); // Refresh list
        });
    }

    @Override public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, qty; ImageButton edit, del;
        VH(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.itemName);
            qty = v.findViewById(R.id.itemQty);
            edit = v.findViewById(R.id.btnEdit);
            del = v.findViewById(R.id.btnDelete);
        }
    }
}
