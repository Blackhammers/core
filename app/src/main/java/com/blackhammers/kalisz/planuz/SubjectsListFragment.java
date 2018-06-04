package com.blackhammers.kalisz.planuz;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubjectsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubjectsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubjectsListFragment extends Fragment {
    RecyclerView showUserList;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM_SUBJECTS_LIST = "subjectsList";
    private List<Subjects> subjectsList;



   //private onSubjectsAdapterListener mListener;

    public SubjectsListFragment() {
    }

    public SubjectsListFragment newInstance(List<Subjects> subjectsList) {
        SubjectsListFragment fragment = new SubjectsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_SUBJECTS_LIST, (Serializable) subjectsList);



        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            subjectsList = (List<Subjects>) getArguments().getSerializable(ARG_PARAM_SUBJECTS_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_subjects_list, container, false);
        defineView(view);
        return view;
    }

    private void defineView(View view){
        showUserList=view.findViewById(R.id.recyclerSubjectsView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        showUserList.setLayoutManager(layoutManager);

        SubjectsListAdapter listAdapter=new SubjectsListAdapter(subjectsList);
        showUserList.setAdapter(listAdapter);
    }


    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }



//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
