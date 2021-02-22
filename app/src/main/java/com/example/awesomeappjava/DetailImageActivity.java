package com.example.awesomeappjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import timber.log.Timber;

public class DetailImageActivity extends AppCompatActivity {

    private int img = 0;
    private String title = "";
    private String desc = "";

    Context mContext;

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

        if (img != 0) {
            try {
                Glide.with(mContext).load(img)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(mIv);
            } catch (Exception ex) {
                Timber.e(ex);
            }
        } else {
            try {
                Glide.with(mContext).load(R.drawable.ic_launcher_foreground)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(mIv);
            } catch (Exception ex) {
                Timber.e(ex);
            }
        }
        mTvTitle.setText(title);
        mTvDescription.setText(desc);
    }
}