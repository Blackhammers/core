package com.blackhammers.kalisz.planuz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FacultiesActivity extends AppCompatActivity implements onFacultiesAdapterListener {

    RecyclerView recyclerView;
    FacultiesAdapter adapter;
    List<Faculties> facultiesList;
    List<String> keys;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculties);



        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
//    getSupportActionBar().setDisplayShowHomeEnabled(true);
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Wydziały");



        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        Context context;
//        getSupportActionBar().setIcon(R.drawable.classicon);

        facultiesList = new ArrayList<>();
        keys = new ArrayList<>();

        adapter = new FacultiesAdapter( this, facultiesList, this);
        recyclerView.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();


        getFacultiesFromDatabase();

        adapter.notifyDataSetChanged();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_faculties, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Wyszukaj wydziału");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_search:
                return  true;

            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(FacultiesActivity.this, MainMenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }




    public void getFacultiesFromDatabase() {


        databaseReference = firebaseDatabase.getReference().child("data");
        databaseReference.keepSynced(true);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Faculties faculties = dataSnapshot.getValue(Faculties.class);
                // add data from model to arraylist

                facultiesList.add(faculties);

                //add arraylist to adapter/recyclerView

                adapter.notifyDataSetChanged();

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Faculties faculties = dataSnapshot.getValue(Faculties.class);
                String key = dataSnapshot.getKey();

                int index = keys.indexOf(key);

                facultiesList.set(index, faculties);

                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();

                int index = keys.indexOf(key);

                facultiesList.remove(index);

                adapter.notifyDataSetChanged();

                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onFacultiesSelected(Faculties faculties) {

        Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
        intent.putExtra("facultiesId", faculties.getId());
        startActivity(intent);

    }

}

