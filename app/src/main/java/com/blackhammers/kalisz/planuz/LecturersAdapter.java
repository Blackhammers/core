package com.blackhammers.kalisz.planuz;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kalis on 2018-06-02.
 */

public class LecturersAdapter extends RecyclerView.Adapter<LecturersAdapter.SingleLecturersRowHolder> {

    public class SingleLecturersRowHolder extends RecyclerView.ViewHolder {

        TextView name, surname, room;

        public SingleLecturersRowHolder(View itemView) {
            super(itemView);

        }
    }

    @Override
    public SingleLecturersRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(LecturersAdapter.SingleLecturersRowHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
