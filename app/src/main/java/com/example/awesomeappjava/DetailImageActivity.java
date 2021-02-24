package com.example.awesomeappjava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.apache.commons.lang3.StringUtils;

import timber.log.Timber;

public class DetailImageActivity extends AppCompatActivity {

    private String img = "";
    private String title = "";
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        assert bundle != null;
        img = bundle.getString("img", "0");
        title = bundle.getString("title", "");
        url = bundle.getString("url", "");

        ImageView mIv = findViewById(R.id.image);
        TextView mTvTitle = findViewById(R.id.title);
        TextView mTvDescription = findViewById(R.id.description);
        ImageView mIvBack = findViewById(R.id.iv_back);

        if (StringUtils.isNotEmpty(img)) {
            try {
                Glide.with(this).load(img)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(mIv);
            } catch (Exception ex) {
                Timber.e(ex);
            }
        } else {
            try {
                Glide.with(this).load(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(mIv);
            } catch (Exception ex) {
                Timber.e(ex);
            }
        }
        mTvTitle.setText(title);
        mTvDescription.setText(url);

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