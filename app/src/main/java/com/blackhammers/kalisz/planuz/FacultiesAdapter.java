package com.blackhammers.kalisz.planuz;

/**
 * Created by kalis on 2018-04-09.
 */

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


public class FacultiesAdapter extends RecyclerView.Adapter<FacultiesAdapter.SingleFacultiesRowHolder> implements Filterable, onFacultiesAdapterListener {

    private Context context;
    private List<Faculties> facultiesList;
    private List<Faculties> filterList;
    private onFacultiesAdapterListener listener;

    @Override
    public void onFacultiesSelected(Faculties faculties) {

    }

    public class SingleFacultiesRowHolder extends RecyclerView.ViewHolder {

        TextView name, address;

        public SingleFacultiesRowHolder(View itemView, List<Faculties> list) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.facultiesNameId);
            address = (TextView) itemView.findViewById(R.id.addressId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFacultiesSelected(filterList.get(getAdapterPosition()));
                }
            });


        }
    }

    public FacultiesAdapter(Context context, List<Faculties> facultiesList, onFacultiesAdapterListener listener) {


        this.context = context;
        this.listener = listener;
        this.facultiesList = facultiesList;
        this.filterList = facultiesList;
    }


    @Override
    public SingleFacultiesRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculties_content_main, parent, false);

        SingleFacultiesRowHolder singleFacultiesRowHolder = new SingleFacultiesRowHolder(view, facultiesList);
        //sortowanie listy po name
        Collections.sort(facultiesList, new Comparator<Faculties>() {
            @Override
            public int compare(Faculties o1, Faculties o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        return singleFacultiesRowHolder;
    }


    @Override
    public void onBindViewHolder(final SingleFacultiesRowHolder holder, final int position) {
        final Faculties faculties = filterList.get(position);
        //holder.setIsRecyclable(false);

        holder.name.setText(faculties.getname());
        holder.address.setText(faculties.getaddress());
        //Glide  for images
    }


    @Override
    public int getItemCount() {
        return filterList == null ? 0 : filterList.size();
        }
        //return filterList.size();



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchText = constraint.toString();
                if (searchText.isEmpty()) {
                    filterList = facultiesList;
                } else {
                    List<Faculties> filteredList = new ArrayList<>();
                    for (Faculties faculties : facultiesList) {
                        if (faculties.name.toLowerCase().contains(searchText.toLowerCase())|| faculties.getname().toLowerCase().contains(searchText.toLowerCase())) {
                            filteredList.add(faculties);
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

                filterList = (ArrayList<Faculties>) results.values;
                notifyDataSetChanged();

            }
        };
    }

}
