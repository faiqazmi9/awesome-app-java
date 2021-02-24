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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.awesomeappjava.adapter.ItemAdapter;
import com.example.awesomeappjava.model.Item;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.awesomeappjava.adapter.ItemAdapter.SPAN_COUNT_ONE;
import static com.example.awesomeappjava.adapter.ItemAdapter.SPAN_COUNT_TWO;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Item> items;

    MaterialToolbar mToolbar;

    /**
     * Trigger when swipe.
     */
    private boolean isRefresh = false;

    /**
     * Total number items on Adapter after last load.
     */
    private int previousTotal = 0;

    int pageInit = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setupToolbar();
        setAdapter();
        fetchItem(pageInit);
    }

    protected void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = this.getSupportActionBar();
        assert mActionBar != null;
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mActionBar.setTitle("Awesome App");
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
        items = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT_ONE);
        itemAdapter = new ItemAdapter(items, this, gridLayoutManager);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        itemAdapter.itemOnClick((pos, item) -> {
            Intent intent = new Intent(this, DetailImageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", item.getId());
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        });
    }

    private void fetchItem(int page) {
        StringRequest request = new StringRequest(Request.Method.GET,
                "https://api.pexels.com/v1/curated/?page=" + page + "&per_page=10", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("photos");

                    int length = jsonArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        int id = object.getInt("id");
                        int width = object.getInt("width");
                        int height = object.getInt("height");
                        String url = object.getString("url");
                        String photographer = object.getString("photographer");
                        String photographerUrl = object.getString("photographer_url");
                        int photographerId = object.getInt("photographer_id");
                        String avgColor = object.getString("avg_color");

                        JSONObject objectImages = object.getJSONObject("src");
                        String original = objectImages.getString("original");
                        String large2x = objectImages.getString("large2x");
                        String large = objectImages.getString("large");
                        String medium = objectImages.getString("medium");
                        String small = objectImages.getString("small");
                        String portrait = objectImages.getString("portrait");
                        String landscape = objectImages.getString("landscape");
                        String tiny = objectImages.getString("tiny");

                        Item item = new Item(id, width, height, url, photographer, photographerUrl, photographerId,
                                avgColor, original, large2x, large, medium, small, portrait, landscape, tiny);
                        items.add(item);
                    }
                    itemAdapter.notifyDataSetChanged();
                } catch (JSONException e) {

                }
                pageInit = 2;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "563492ad6f91700001000001f6b8454bfc70405ab842826d3658ae80");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}