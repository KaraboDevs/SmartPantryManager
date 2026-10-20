package com.karabodevs.smartpantry;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddEditItemActivity extends AppCompatActivity {
    private TextInputEditText inputName, inputQty, inputUnit;
    private DatabaseHelper db;
    private int itemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_item);
        db = new DatabaseHelper(this);

        inputName = findViewById(R.id.inputName);
        inputQty = findViewById(R.id.inputQty);
        inputUnit = findViewById(R.id.inputUnit);

        if (getIntent().hasExtra("item_id")) {
            itemId = getIntent().getIntExtra("item_id", -1);
            PantryItem item = db.getPantryItemById(itemId);
            if (item != null) {
                inputName.setText(item.getName());
                inputQty.setText(String.valueOf((int)item.getQuantity()));
                inputUnit.setText(item.getUnit());
            }
        }

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String n = inputName.getText().toString().trim();
            String q = inputQty.getText().toString().trim();
            String u = inputUnit.getText().toString().trim();

            if (n.isEmpty() || q.isEmpty() || u.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            double qty = Double.parseDouble(q);

            if (itemId == -1) db.addPantryItem(n, qty, u);
            else db.updatePantryItem(itemId, n, qty, u);

            finish();
        });
    }
}
