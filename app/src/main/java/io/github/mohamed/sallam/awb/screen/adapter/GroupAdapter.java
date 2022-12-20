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

    private final OnLongPressListener onLongPressListener;
    private final OnClickListener onClickListener;
    //private int selectedPosition = 0;

    public GroupAdapter(OnLongPressListener onLongPressListener,
                           OnClickListener onClickListener) {
        super(new Differ());
        this.onClickListener = onClickListener;
        this.onLongPressListener = onLongPressListener;
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        return new GroupViewHolder(GroupItemBinding
                                   .inflate(LayoutInflater
                                            .from(parent.getContext()), parent
                                            , false)
                                            , onLongPressListener
                                            , onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder,
                                 int position) {
        Group item = getItem(position);
        holder.bind(item);
        holder.validateBackgroundColor(item);
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {
        GroupItemBinding binding;

        public GroupViewHolder(GroupItemBinding views,
                               OnLongPressListener onLongPressListener,
                               OnClickListener onClickListener) {
            super(views.getRoot());
            this.binding = views;
            this.binding.groupItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onLongPressListener.onLongPress(binding.getGroup());
                    return false;
                }
            });
            this.binding.groupItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(binding.getGroup());
                    validateBackgroundColor(binding.getGroup());
                }
            });
        }

        void bind(Group group) {
            binding.setGroup(group);
        }

        public void validateBackgroundColor(Group item) {
            if (item.isSelected()) {
                binding.groupItem.setBackgroundColor(Color.GREEN);
                binding.getGroup().setSelected(false);
            } else {
                binding.groupItem.setBackgroundColor(Color.DKGRAY);
                binding.getGroup().setSelected(true);
            }
        }
    }

    static class Differ extends DiffUtil.ItemCallback<Group> {

        @Override
        public boolean areItemsTheSame(@NonNull Group oldItem, @NonNull Group newItem) {
            return oldItem.uuid == newItem.uuid;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Group oldItem, @NonNull Group newItem) {
            return oldItem.equals(newItem);
        }
    }

    public interface OnLongPressListener {
        void onLongPress(Group group);
    }

    public interface OnClickListener {
        void onClick(Group group);
    }

}

