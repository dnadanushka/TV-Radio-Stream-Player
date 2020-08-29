package com.example.youtube_streamer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.youtube_streamer.adapter.viewholder.WordViewHolder;
import com.example.youtube_streamer.databinding.ItemWordBinding;
import com.example.youtube_streamer.models.WordModel;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Comparator;

public class ExampleAdapter extends SortedListAdapter<WordModel> {

    public interface Listener {
        void onExampleModelClicked(WordModel model);
    }

    private final Listener mListener;

    public ExampleAdapter(Context context, Comparator<WordModel> comparator, Listener listener) {
        super(context, WordModel.class, comparator);
        mListener = listener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends WordModel> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final ItemWordBinding binding = ItemWordBinding.inflate(inflater, parent, false);
        return new WordViewHolder(binding, mListener);
    }
}
