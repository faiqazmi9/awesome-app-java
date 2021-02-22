package com.example.awesomeappjava;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.awesomeappjava.adapter.ItemAdapter;
import com.example.awesomeappjava.model.Item;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import static com.example.awesomeappjava.adapter.ItemAdapter.SPAN_COUNT_ONE;
import static com.example.awesomeappjava.adapter.ItemAdapter.SPAN_COUNT_TWO;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List items;

    MaterialToolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setupToolbar();
        initItemsData();
        setAdapter();
    }

    protected void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = this.getSupportActionBar();
        assert mActionBar != null;
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mActionBar.setTitle("Nature");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
                itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                return true;
            case R.id.action_grid:
                gridLayoutManager.setSpanCount(SPAN_COUNT_TWO);
                itemAdapter.notifyItemRangeChanged(0, itemAdapter.getItemCount());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setAdapter() {
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        itemAdapter = new ItemAdapter(items, gridLayoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initItemsData() {
        items = new ArrayList<>();
        items.add(new Item(R.drawable.bird, "Bird"));
        items.add(new Item(R.drawable.bird1, "Bird 1"));
        items.add(new Item(R.drawable.bird2, "Bird 2"));
        items.add(new Item(R.drawable.butterfly, "Butterfly"));
        items.add(new Item(R.drawable.cactus, "Cactus"));
        items.add(new Item(R.drawable.fish, "Fish"));
        items.add(new Item(R.drawable.flower, "Flower"));
        items.add(new Item(R.drawable.flower1, "Flower 1"));
        items.add(new Item(R.drawable.flower2, "Flower 2"));
        items.add(new Item(R.drawable.flower3, "Flower 3"));
        items.add(new Item(R.drawable.lake, "Lake"));
        items.add(new Item(R.drawable.leaf, "Leaf"));
        items.add(new Item(R.drawable.leaf1, "Leaf 1"));
        items.add(new Item(R.drawable.leaf2, "Leaf 2"));
        items.add(new Item(R.drawable.leaf3, "Leaf 3"));
        items.add(new Item(R.drawable.moon, "Moon"));
        items.add(new Item(R.drawable.mountain, "Mountain"));
        items.add(new Item(R.drawable.mountain1, "Mountain 1"));
        items.add(new Item(R.drawable.mountain2, "Mountain 2"));
        items.add(new Item(R.drawable.mountain3, "Mountain 3"));
        items.add(new Item(R.drawable.mushroom, "Moshroom"));
        items.add(new Item(R.drawable.ocean, "Ocean"));
        items.add(new Item(R.drawable.rainbow, "Rainbow"));
        items.add(new Item(R.drawable.tree, "Tree"));
        items.add(new Item(R.drawable.waterfall, "Waterfall"));
        items.add(new Item(R.drawable.wave, "Wave"));
        items.add(new Item(R.drawable.wave1, "Wave 1"));
        items.add(new Item(R.drawable.wave2, "Wave 2"));
    }
}