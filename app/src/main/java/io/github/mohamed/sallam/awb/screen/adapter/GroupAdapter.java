package io.github.mohamed.sallam.awb.screen.adapter;

import android.annotation.SuppressLint;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import io.github.mohamed.sallam.awb.R;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.databinding.GroupItemBinding;

/**
 * Adapter for groups `RecyclerView`.
 *
 * @author Abdurrahman Salah
 */
public class GroupAdapter extends ListAdapter<Group, GroupAdapter.GroupViewHolder> {
    private UUID selectedGroupUuid;
    private int selectedPosition = -1;
    private UUID longClickedGroupUuid;

    public GroupAdapter() {
        super(new Differ());
    }

    public UUID getLongClickedGroupUuid() {
        return longClickedGroupUuid;
    }

    public UUID getSelectedGroupUuid() {
        return selectedGroupUuid;
    }

    @Override
    public void onViewRecycled(@NonNull GroupViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        return new GroupViewHolder(GroupItemBinding
                                   .inflate(LayoutInflater
                                            .from(parent.getContext()), parent
                                            , false));
    }

    /**
     * Source: https://stackoverflow.com/a/51080712/20414197
     *
     * @author Yousef Ahmed
     * @author Mohamed Sallam
     */
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder,
                                 int position) {
        Group group = getItem(position);
        holder.bind(group);
        holder.itemView.setSelected(selectedPosition == position);
        holder.binding.groupNameEditText.setTextColor(ContextCompat
                .getColor(holder.binding.groupNameEditText.getContext()
                        ,R.color.white));
        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition);
            selectedPosition = holder.getBindingAdapterPosition();
            notifyItemChanged(selectedPosition);
            selectedGroupUuid = holder.binding.getGroup().uuid;
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickedGroupUuid = holder.binding.getGroup().uuid;
                return false;
            }
        });
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder
                        implements View.OnCreateContextMenuListener {
        public GroupItemBinding binding;

        public GroupViewHolder(GroupItemBinding views) {
            super(views.getRoot());
            this.binding = views;
            views.getRoot().setOnCreateContextMenuListener(this);
        }

        public void bind(Group group) {
            binding.setGroup(group);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(Menu.NONE, R.id.renameGroupOption,
                         0, R.string.rename);
            contextMenu.add(Menu.NONE, R.id.duplicateGroupOption,
                         1, R.string.duplicate);
            contextMenu.add(Menu.NONE, R.id.editGroupOption,
                         2, R.string.edit);
            contextMenu.add(Menu.NONE, R.id.renameGroupOption,
                         3, R.string.remove);
        }
    }

    public static class Differ extends DiffUtil.ItemCallback<Group> {

        @Override
        public boolean areItemsTheSame(@NonNull Group oldItem, @NonNull Group newItem) {
            return oldItem.uuid == newItem.uuid;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Group oldItem, @NonNull Group newItem) {
            return oldItem.equals(newItem);
        }
    }
}

