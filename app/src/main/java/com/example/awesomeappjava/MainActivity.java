package com.example.awesomeappjava;

import android.content.Intent;
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
        itemAdapter.itemOnClick((pos, item) -> {
            Intent intent = new Intent(this, DetailImageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", item.getTitle());
            bundle.putString("desc", item.getDescription());
            bundle.putInt("img", item.getImgResId());
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        });
    }

    private void initItemsData() {
        items = new ArrayList<>();
        items.add(new Item(R.drawable.bird, "Bird", "This picture was taken in Punta del Este, Uruguay"));
        items.add(new Item(R.drawable.bird1, "Bird 1", "Preety little owl"));
        items.add(new Item(R.drawable.bird2, "Bird 2", "Owl serious mode on"));
        items.add(new Item(R.drawable.butterfly, "Butterfly", "Very Beautifull Butterfly"));
        items.add(new Item(R.drawable.cactus, "Cactus", "A cactus is a member of the plant family Cactaceae"));
        items.add(new Item(R.drawable.fish, "Fish", "This picture was taken in Indonesia"));
        items.add(new Item(R.drawable.flower, "Flower", "The Red Flower"));
        items.add(new Item(R.drawable.flower1, "Flower 1", "Beautifull Flower in London, United Kingdom"));
        items.add(new Item(R.drawable.flower2, "Flower 2", "Calm flower"));
        items.add(new Item(R.drawable.flower3, "Flower 3", "Beautifull pinky flower"));
        items.add(new Item(R.drawable.lake, "Lake", "Lake create the reflection"));
        items.add(new Item(R.drawable.leaf, "Leaf", "Green in the sky"));
        items.add(new Item(R.drawable.leaf1, "Leaf 1", "In the morning with breezy air"));
        items.add(new Item(R.drawable.leaf2, "Leaf 2", "Leaf with darkness"));
        items.add(new Item(R.drawable.leaf3, "Leaf 3", "Mexican leaf"));
        items.add(new Item(R.drawable.moon, "Moon", "Reaching the moon"));
        items.add(new Item(R.drawable.mountain, "Mountain", "Great mount with lake"));
        items.add(new Item(R.drawable.mountain1, "Mountain 1", "the blue dew faded at the horizon"));
        items.add(new Item(R.drawable.mountain2, "Mountain 2", "cheerful camper"));
        items.add(new Item(R.drawable.mountain3, "Mountain 3", "United States Great Mount"));
        items.add(new Item(R.drawable.mushroom, "Moshroom", "Li'l Mushroom"));
        items.add(new Item(R.drawable.ocean, "Ocean", "Oceanic animal"));
        items.add(new Item(R.drawable.rainbow, "Rainbow", "Happy rainbow"));
        items.add(new Item(R.drawable.tree, "Tree", "Holaa Beach ..."));
        items.add(new Item(R.drawable.waterfall, "Waterfall", "Amazing waterfall"));
        items.add(new Item(R.drawable.wave, "Wave", "Wave the ocean"));
        items.add(new Item(R.drawable.wave1, "Wave 1", "Hi beautifull beach"));
        items.add(new Item(R.drawable.wave2, "Wave 2", "Lets Surf!"));
    }
}