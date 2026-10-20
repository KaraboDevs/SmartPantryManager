package com.karabodevs.smartpantry;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PantryFragment extends Fragment {
    private DatabaseHelper db;
    private PantryAdapter adapter;
    private RecyclerView recycler;
    private TextView emptyState;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pantry, container, false);
        db = new DatabaseHelper(getContext());
        recycler = v.findViewById(R.id.recyclerPantry);
        emptyState = v.findViewById(R.id.emptyState);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PantryAdapter(db, this);
        recycler.setAdapter(adapter);

        v.findViewById(R.id.fabAdd).setOnClickListener(view ->
                startActivity(new Intent(getContext(), AddEditItemActivity.class)));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<PantryItem> items = db.getAllPantryItems();
        adapter.setItems(items);
        emptyState.setVisibility(items.isEmpty() ? View.VISIBLE : View.GONE);
        recycler.setVisibility(items.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
