package com.example.youtube_streamer.adapter.viewholder;

import androidx.annotation.NonNull;

import com.example.youtube_streamer.adapter.ExampleAdapter;
import com.example.youtube_streamer.databinding.ItemWordBinding;
import com.example.youtube_streamer.models.WordModel;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class WordViewHolder extends SortedListAdapter.ViewHolder<WordModel> {

    private final ItemWordBinding mBinding;

    public WordViewHolder(ItemWordBinding binding, ExampleAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);

        mBinding = binding;
    }

    @Override
    protected void performBind(@NonNull WordModel item) {
        mBinding.setModel(item);
    }
}

