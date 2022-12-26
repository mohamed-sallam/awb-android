package io.github.mohamed.sallam.awb.screen.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import io.github.mohamed.sallam.awb.databinding.DeviceWithGroupsItemBinding;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;

public class DeviceWithGroupsAdapter extends ListAdapter<DeviceWithGroups,
        DeviceWithGroupsAdapter.ViewHolder> {

    protected DeviceWithGroupsAdapter() {
        super(new DeviceWithGroupsAdapter.Differ());
    }

    @NonNull
    @Override
    public DeviceWithGroupsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                 int viewType) {
        return new DeviceWithGroupsAdapter.ViewHolder(DeviceWithGroupsItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceWithGroupsAdapter.ViewHolder holder,
                                 int position) {
        DeviceWithGroups deviceWithGroups = getItem(position);
        holder.bind(deviceWithGroups);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        DeviceWithGroupsItemBinding binding;

        public ViewHolder(@NonNull DeviceWithGroupsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(DeviceWithGroups deviceWithGroups) {
            binding.setDeviceWithGroups(deviceWithGroups);
            GroupAdapter adapter = new GroupAdapter();
            adapter.submitList(deviceWithGroups.groups);
            binding.groupRV.setAdapter(adapter);
        }
    }

    static class Differ extends DiffUtil.ItemCallback<DeviceWithGroups> {

        @Override
        public boolean areItemsTheSame(@NonNull DeviceWithGroups oldItem
                , @NonNull DeviceWithGroups newItem) {
            return oldItem.device.uuid.equals(newItem.device.uuid);
        }

        @Override
        public boolean areContentsTheSame(@NonNull DeviceWithGroups oldItem
                , @NonNull DeviceWithGroups newItem) {
            return oldItem.equals(newItem);
        }
    }
}
