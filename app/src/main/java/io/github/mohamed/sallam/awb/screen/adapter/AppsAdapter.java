package io.github.mohamed.sallam.awb.screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.databinding.ItemAppBinding;

public class AppsAdapter extends ListAdapter<App, AppsAdapter.ViewHolder> {

    private final OnAppListener onAppListener;
    private final Context context;

    public AppsAdapter(Context context, OnAppListener onAppListener) {
        super(new Differ());
        this.context = context;
        this.onAppListener = onAppListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemAppBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent,
                        false),
                onAppListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        App app = getItem(position);
        holder.bind(app);
        holder.validateBackgroundColor(app);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ItemAppBinding itemBinding;
        OnAppListener onAppListener;

        public ViewHolder(ItemAppBinding itemBinding, OnAppListener onAppListener) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.onAppListener = onAppListener;
            itemBinding.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAppListener.onClick(itemBinding.getAppItem());
                    validateBackgroundColor(itemBinding.getAppItem());
                }
            });
        }

        private void validateBackgroundColor(App app) {
            if (app.isSelected()){
                itemBinding.item.setBackgroundColor(context.getResources()
                        .getColor(R.color.flat_green));
            } else {
                itemBinding.item.setBackgroundColor(context.getResources()
                        .getColor(R.color.flat_red));
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
