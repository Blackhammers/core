package com.blackhammers.kalisz.planuz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kalis on 2018-04-24.
 */

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.SingleCoursesRowHolder> implements Filterable, onCoursesAdapterListener {
    private Context context;
    private List<Courses> coursesList;
    private List<Courses> filterList;
    private onCoursesAdapterListener listener;


    @Override
    public void onCoursesSelectedListener(Courses courses) {

    }

    public class SingleCoursesRowHolder extends RecyclerView.ViewHolder {

        TextView name;

        public SingleCoursesRowHolder(View itemView, List<Courses> list) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.coursesNameId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCoursesSelectedListener(filterList.get(getAdapterPosition()));
                }
            });


        }
    }

    public CoursesAdapter(Context context, List<Courses> coursesList, onCoursesAdapterListener listener) {
        this.context = context;
        this.coursesList = coursesList;
        this.filterList = coursesList;
        this.listener = listener;
    }

    @Override
    public SingleCoursesRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_content, parent, false);

        SingleCoursesRowHolder singleCoursesRowHolder = new SingleCoursesRowHolder(view, coursesList);
        //sortowanie listy po name
        Collections.sort(coursesList, new Comparator<Courses>() {
            @Override
            public int compare(Courses o1, Courses o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        return singleCoursesRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleCoursesRowHolder holder, int position) {
        final Courses courses = filterList.get(position);
        holder.name.setText(courses.getName());
        //Glide  for images

    }

    @Override
    public int getItemCount() {
        return filterList == null ? 0 : filterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = constraint.toString();
                if (searchText.isEmpty()) {
                    filterList = coursesList;
                } else {
                    List<Courses> filteredList = new ArrayList<>();
                    for (Courses courses : coursesList) {
                        if (courses.name.toLowerCase().contains(searchText.toLowerCase())|| courses.getName().toLowerCase().contains(searchText.toLowerCase())) {
                            filteredList.add(courses);
                        }
                    }
                    filterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filterList = (ArrayList<Courses>) results.values;
                notifyDataSetChanged();

            }
        };
    }
    }

