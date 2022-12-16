package io.github.mohamed.sallam.awb.screen.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.databinding.AppItemBinding;

public class AppsAdapter extends ListAdapter<App, AppsAdapter.ViewHolder> {

    private final OnAppListener onAppListener;

    public AppsAdapter(OnAppListener onAppListener) {
        super(new Differ());
        this.onAppListener = onAppListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                AppItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent,
                        false),
                onAppListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        App app = getItem(position);
        holder.bind(app);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        AppItemBinding itemBinding;
        OnAppListener onAppListener;

        public ViewHolder(AppItemBinding itemBinding, OnAppListener onAppListener) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.onAppListener = onAppListener;
            validateBackgroundColor(itemBinding.getAppItem());
            itemBinding.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAppListener.onClick(itemBinding.getAppItem());
                }
            });
        }

        void validateBackgroundColor(App app) {
            if (app.isSelected()) {
                itemBinding.item.setBackgroundColor(Color.GREEN);
            } else {
                itemBinding.item.setBackgroundColor(Color.RED);
            }
        }

        void bind(App app) {
            itemBinding.setAppItem(app);
        }
    }

    public interface OnAppListener {
        void onClick(App app);
    }

    private static class Differ extends DiffUtil.ItemCallback<App> {

        @Override
        public boolean areItemsTheSame(@NonNull App oldItem, @NonNull App newItem) {
            return oldItem.getPackageName().equals(newItem.getPackageName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull App oldItem, @NonNull App newItem) {
            return true;
        }
    }
}
