package com.blackhammers.kalisz.planuz;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubjectsActivity extends AppCompatActivity implements onSubjectsAdapterListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Subjects> subjectsList;
    SubjectsListAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjcets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Przedmioty");
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutId);
        viewPager = (ViewPager) findViewById(R.id.viewPagerId);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerSubjectsView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        subjectsList = new ArrayList<>();
        adapter = new SubjectsListAdapter(subjectsList);
        SubjectsListFragment fragment;
        setSupportActionBar(toolbar);


        Intent intent = getIntent();

        String blabla = intent.getStringExtra("random");
        int coursesId = intent.getIntExtra("coursesId", 0);
        int facultiesId = intent.getIntExtra("facultiesId", 0);
        int groupsId = intent.getIntExtra("groupsId", 0);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SubjectsListFragment().newInstance(getMondayPlanFromFirebase(facultiesId, coursesId)), blabla);
        adapter.addFragment(new SubjectsListFragment().newInstance(getTuesdayPlanFromFirebase(facultiesId, coursesId, groupsId)), "WT");
        adapter.addFragment(new SubjectsListFragment().newInstance(getWednesdayPlanFromFirebase(facultiesId, coursesId)), "SR");
        adapter.addFragment(new SubjectsListFragment().newInstance(getThursdayPlanFromFirebase(facultiesId, coursesId, groupsId)), "CZ");
        adapter.addFragment(new SubjectsListFragment().newInstance(getFridayPlanFromFirebase(facultiesId, coursesId, groupsId)), "PT");
        adapter.addFragment(new SubjectsListFragment().newInstance(getSaturdayPlanFromFirebase(facultiesId, coursesId, groupsId)), "SB");
        adapter.addFragment(new SubjectsListFragment().newInstance(getSundayPlanFromFirebase(facultiesId, coursesId, groupsId)), "ND");
        adapter.addFragment(new SubjectsListFragment().newInstance(getSundayPlanFromFirebase(facultiesId, coursesId, groupsId)), "+");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_groups, menu);               // POPRAW !!!!
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager){



    }

    public List<Subjects> getMondayPlanFromFirebase(Integer facultiesKey, Integer coursesKey){

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("data/"+facultiesKey+"/courses/"+coursesKey+"/groups");

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Subjects subjects = dataSnapshot.getValue(Subjects.class);
                subjectsList.add(subjects);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       return  subjectsList;
    }
    public List<Subjects> getTuesdayPlanFromFirebase(Integer facultiesKey, Integer coursesKey, Integer groupsKey){
        final List<Subjects> subjectsList=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("data/"+facultiesKey+"/courses/"+coursesKey+"/groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator=  dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot snapshot=iterator.next();
                    subjectsList.add(snapshot.getValue(Subjects.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return  subjectsList;
    }
    public List<Subjects> getWednesdayPlanFromFirebase(Integer facultiesKey, Integer coursesKey){
        final List<Subjects> subjectsList=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("data/"+facultiesKey+"/courses/"+coursesKey+"/groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator=  dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot snapshot=iterator.next();
                    subjectsList.add(snapshot.getValue(Subjects.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //subjectsList.add(new Subjects("ram@gmail.com"));
        return subjectsList;
    }

    public List<Subjects> getThursdayPlanFromFirebase(Integer facultiesKey, Integer coursesKey, Integer groupsKey){
        final List<Subjects> subjectsList=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("data/"+facultiesKey+"/courses/"+coursesKey+"/groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator=  dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot snapshot=iterator.next();
                    subjectsList.add(snapshot.getValue(Subjects.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //subjectsList.add(new Subjects("ram@gmail.com"));
        return subjectsList;
    }

    public List<Subjects> getFridayPlanFromFirebase(Integer facultiesKey, Integer coursesKey, Integer groupsKey){
        final List<Subjects> subjectsList=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("data/"+facultiesKey+"/courses/"+coursesKey+"/groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator=  dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot snapshot=iterator.next();
                    subjectsList.add(snapshot.getValue(Subjects.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //subjectsList.add(new Subjects("ram@gmail.com"));
        return subjectsList;
    }

    public List<Subjects> getSaturdayPlanFromFirebase(Integer facultiesKey, Integer coursesKey, Integer groupsKey){
        final List<Subjects> subjectsList=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("data/"+facultiesKey+"/courses/"+coursesKey+"/groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator=  dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot snapshot=iterator.next();
                    subjectsList.add(snapshot.getValue(Subjects.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //subjectsList.add(new Subjects("ram@gmail.com"));
        return subjectsList;
    }

    public List<Subjects> getSundayPlanFromFirebase(Integer facultiesKey, Integer coursesKey, Integer groupsKey){
        final List<Subjects> subjectsList=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("data/"+facultiesKey+"/courses/"+coursesKey+"/groups").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator=  dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()){
                    DataSnapshot snapshot=iterator.next();
                    subjectsList.add(snapshot.getValue(Subjects.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //subjectsList.add(new Subjects("ram@gmail.com"));
        return subjectsList;
    }


    @Override
    public void onSubjectsAdapterListener(Subjects subjects) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
