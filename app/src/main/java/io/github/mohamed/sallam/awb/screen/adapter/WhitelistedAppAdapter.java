package io.github.mohamed.sallam.awb.screen.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.databinding.ItemWhitelistedAppBinding;

public class WhitelistedAppAdapter extends ListAdapter<App, WhitelistedAppAdapter.ViewHolder> {
    private final Context context;

    public WhitelistedAppAdapter(Context context) {
        super(new WhitelistedAppAdapter.Differ());
        this.context = context;
    }

    @NonNull
    @Override
    public WhitelistedAppAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WhitelistedAppAdapter.ViewHolder(
                ItemWhitelistedAppBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull WhitelistedAppAdapter.ViewHolder holder, int position) {
        App app = getItem(position);
        holder.bind(app);
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ItemWhitelistedAppBinding itemBinding;

        public ViewHolder(ItemWhitelistedAppBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

            itemBinding.item.setOnClickListener(v -> {
                Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(getItem(getBindingAdapterPosition()).getPackageName());
                context.startActivity(launchIntent);
            });
        }

        void bind(App app) {
            itemBinding.setAppItem(app);
        }
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
