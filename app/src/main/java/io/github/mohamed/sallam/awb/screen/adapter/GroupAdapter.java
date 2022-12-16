package io.github.mohamed.sallam.awb.screen.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.databinding.GroupItemBinding;

/**
 * Adapter for groups `RecyclerView`.
 *
 * @author Abdurrahman Salah
 */
public class GroupAdapter extends ListAdapter<Group, GroupAdapter.GroupViewHolder> {

    protected GroupAdapter() {
        // TODO create new object from Differ class and pass it in the super method [super(new Differ()]
        super();
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        // TODO add parent to root View argument and false to attachToRoot argument
        return new GroupViewHolder(GroupItemBinding
                                   .inflate(LayoutInflater
                                            .from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder,
                                 int position) {
        Group item = getItem(position);
        holder.bind(item);
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        GroupItemBinding binding;

        public GroupViewHolder(GroupItemBinding views) {
            super(views.getRoot());
            this.binding = views;
        }

        void bind(Group group) {
            // TODO bind the group with the group in the data binding
        }
    }

    // TODO create Differ class that inherit from DiffUtil.ItemCallback<Group>
}

