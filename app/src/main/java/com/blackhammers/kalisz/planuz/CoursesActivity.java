package com.blackhammers.kalisz.planuz;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CoursesActivity extends AppCompatActivity  implements  onCoursesAdapterListener{

    RecyclerView recyclerView;
    CoursesAdapter adapter;
    List<Courses> coursesList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Integer> keys;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Intent intent = getIntent();
        int facultiesId = intent.getIntExtra("facultiesId", 0);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kierunki");
        recyclerView = findViewById(R.id.RecyclerViewCoursesID);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

        coursesList = new ArrayList<>();
        keys = new ArrayList<>();

        adapter = new CoursesAdapter(this, coursesList, this);
        recyclerView.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        getCoursesFromDatabase(facultiesId);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_courses, menu);
        MenuItem menuItem = menu.findItem(R.id.courses_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Wyszukaj kierunek");
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

        int id = item.getItemId();

        if(id == R.id.courses_search){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getCoursesFromDatabase(Integer key) {
        databaseReference = firebaseDatabase.getReference().child("data/"+key+"/courses");
        databaseReference.keepSynced(true);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Courses courses = dataSnapshot.getValue(Courses.class);
                coursesList.add(courses);

                adapter.notifyDataSetChanged();

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Courses courses = dataSnapshot.getValue(Courses.class);
                String key = dataSnapshot.getKey();

                int index = keys.indexOf(key);

                coursesList.set(index, courses);

                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();

                int index = keys.indexOf(key);

                coursesList.remove(index);

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


//        databaseReference = firebaseDatabase.getReference().child("data/"+key+"/courses");
//
//        ValueEventListener valueEventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                    for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    if(dataSnapshot.exists()){
//                        Courses courses = ds.getValue(Courses.class);
//                        coursesList.add(courses);
//                        recyclerView.setAdapter(adapter);
//                        Log.d("Courses name ", courses.getName());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        databaseReference.addValueEventListener(valueEventListener);
//        adapter.notifyDataSetChanged();
////        databaseReference = firebaseDatabase.getReference().child("data");
//        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }



    @Override
    public void onCoursesSelectedListener(Courses courses) {
        Intent intent2 = getIntent();
        int facultiesId = intent2.getIntExtra("facultiesId", 0);
        Intent intent = new Intent(getApplicationContext(), GroupsActivity.class);
        intent.putExtra("coursesId", courses.getId());
        intent.putExtra("facultiesId", facultiesId);
        startActivity(intent);

    }
}
