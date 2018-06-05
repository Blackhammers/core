package com.blackhammers.kalisz.planuz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kalis on 2018-05-25.
 */

public class SubjectsListAdapter extends RecyclerView.Adapter<SubjectsListAdapter.ViewHolder> {

    List<Subjects> subjectsList;


    public SubjectsListAdapter(List<Subjects> subjectsList) {
        this.subjectsList = subjectsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects_content,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindView(subjectsList.get(position));

    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Przedmiot, Nauczyciel, Do, Od, RZ, Sale, TerminyUwagi;

        public ViewHolder(View itemView) {
            super(itemView);
            Przedmiot = itemView.findViewById(R.id.subject_name_Id);
            Nauczyciel = itemView.findViewById(R.id.lecturer_nameID);
            Do = itemView.findViewById(R.id.doID);
            Od = itemView.findViewById(R.id.odID);
            RZ = itemView.findViewById(R.id.rodzajzajecID);
            Sale = itemView.findViewById(R.id.salaID);
            TerminyUwagi = itemView.findViewById(R.id.terminyUwagiID);

        }
        public void bindView(Subjects subjects) {
            Przedmiot.setText(subjects.getPrzedmiot());
            Nauczyciel.setText(subjects.getNauczyciel());
            Do.setText(subjects.getDo());
            Od.setText(subjects.getOd());
            RZ.setText(subjects.getRZ());
            Sale.setText(subjects.getSale());
            TerminyUwagi.setText(subjects.getTerminyUwagi());

        }
    }
}
