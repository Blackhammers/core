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
 * Created by kalis on 2018-05-15.
 */

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.SingleGroupsRowHolder> implements onGroupsAdapterListener, Filterable{

    private Context context;
    private List<Groups> groupsList;
    private List<Groups> filterList;
    private onGroupsAdapterListener listener;

    @Override
    public void onGroupsSelectedListener(Groups groups) {

    }

    public class SingleGroupsRowHolder extends RecyclerView.ViewHolder {
        TextView name;
        public SingleGroupsRowHolder(View itemView, List<Groups> groupsList) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.groupsNameId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onGroupsSelectedListener(filterList.get(getAdapterPosition()));
                }
            });


        }
    }

    public GroupsAdapter(Context context, List<Groups> groupsList, onGroupsAdapterListener listener) {
        this.context = context;
        this.groupsList = groupsList;
        this.filterList = groupsList;
        this.listener = listener;
    }

    @Override
    public SingleGroupsRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_content, parent, false);

        SingleGroupsRowHolder singleGroupsRowHolder = new SingleGroupsRowHolder(view, groupsList);
        //sortowanie listy po name
        Collections.sort(groupsList, new Comparator<Groups>() {
            @Override
            public int compare(Groups o1, Groups o2) {
                return o1.name.compareTo(o2.name);
            }


        });
        return singleGroupsRowHolder;
    }

    @Override
    public void onBindViewHolder(GroupsAdapter.SingleGroupsRowHolder holder, int position) {
        final Groups groups = filterList.get(position);
        //holder.setIsRecyclable(false);

        holder.name.setText(groups.getName());

    }

    @Override
    public int getItemCount() {return filterList == null ? 0 : filterList.size();}

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = constraint.toString();
                if (searchText.isEmpty()) {
                    filterList = groupsList;
                } else {
                    List<Groups> filteredList = new ArrayList<>();
                    for (Groups groups : groupsList) {
                        if (groups.name.toLowerCase().contains(searchText.toLowerCase())|| groups.getName().toLowerCase().contains(searchText.toLowerCase())) {
                            filteredList.add(groups);
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

                filterList = (ArrayList<Groups>) results.values;
                notifyDataSetChanged();

            }
        };
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String searchText = constraint.toString();
//                if (searchText.isEmpty()) {
//                    filterList = c;
//                } else {
//                    List<Courses> filteredList = new ArrayList<>();
//                    for (Courses faculties : cour) {
//                        if (faculties.name.toLowerCase().contains(searchText.toLowerCase())|| faculties.getname().toLowerCase().contains(searchText.toLowerCase())) {
//                            filteredList.add(faculties);
//                        }
//                    }
//                    filterList = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = filterList;
//                return filterResults;
//
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                filterList = (ArrayList<Faculties>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//    }




}

