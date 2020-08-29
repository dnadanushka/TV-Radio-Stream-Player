package com.example.youtube_streamer.models;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class WordModel implements SortedListAdapter.ViewModel {

    private final long mId;
    private final int mRank;
    private final String mWord;
    private final String mFilename;

    public WordModel(long id, int rank, String word,String filename) {
        mId = id;
        mRank = rank;
        mWord = word;
        mFilename = filename;
    }

    public long getId() {
        return mId;
    }

    public int getRank() {
        return mRank;
    }

    public String getWord() {
        return mWord;
    }

    public String getFilename() {return mFilename;}

    @Override
    public <T> boolean isSameModelAs(@NonNull T item) {
        if (item instanceof WordModel) {
            final WordModel wordModel = (WordModel) item;
            return wordModel.mId == mId;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T item) {
        if (item instanceof WordModel) {
            final WordModel other = (WordModel) item;
            if (mRank != other.mRank) {
                return false;
            }
            return mWord != null ? mWord.equals(other.mWord) : other.mWord == null;
        }
        return false;
    }
}