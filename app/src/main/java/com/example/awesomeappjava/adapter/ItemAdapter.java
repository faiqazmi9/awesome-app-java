package com.example.awesomeappjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.awesomeappjava.R;
import com.example.awesomeappjava.model.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_TWO = 2;

    private static final int VIEW_TYPE_SMALL = 1;
    private static final int VIEW_TYPE_BIG = 2;

    private List mItems;
    private GridLayoutManager mLayoutManager;
    private ItemOnClickListener itemOnClickListener;

    public ItemAdapter(List items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
    }

    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_ONE) {
            return VIEW_TYPE_BIG;
        } else {
            return VIEW_TYPE_SMALL;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = (Item) mItems.get(position);
        holder.title.setText(item.getTitle());
        holder.iv.setImageResource(item.getImgResId());
        holder.mView.setOnClickListener(view -> {
            if (itemOnClickListener != null) {
                itemOnClickListener.onClick(position, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title;
        ConstraintLayout mView;

        ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            mView = (ConstraintLayout) itemView.findViewById(R.id.view);
            iv = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public void itemOnClick(ItemOnClickListener listener) {
        this.itemOnClickListener = listener;
    }

    public interface ItemOnClickListener {
        void onClick(int pos, Item item);
    }
}
