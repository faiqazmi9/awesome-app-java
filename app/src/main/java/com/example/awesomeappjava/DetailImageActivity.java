package com.example.awesomeappjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailImageActivity extends AppCompatActivity {

    private int img = 0;
    private String title = "";
    private String desc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        assert bundle != null;
        img = bundle.getInt("img", 0);
        title = bundle.getString("title", "");
        desc = bundle.getString("desc", "");

        ImageView mIv = findViewById(R.id.image);
        TextView mTvTitle = findViewById(R.id.title);
        TextView mTvDescription = findViewById(R.id.description);
        ImageView mIvBack = findViewById(R.id.iv_back);

        mIv.setImageResource(img);
        mTvTitle.setText(title);
        mTvDescription.setText(desc);

        mIvBack.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}