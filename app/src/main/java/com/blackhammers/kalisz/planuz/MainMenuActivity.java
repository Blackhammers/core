package com.blackhammers.kalisz.planuz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

public class MainMenuActivity extends AppCompatActivity {
    CardView groupsPlan, lecturerRooms, lecturers, events, maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        groupsPlan = (CardView) findViewById(R.id.groupsPlanId);
        lecturerRooms = (CardView) findViewById(R.id.lecturerRoomsId);
        lecturers = (CardView) findViewById(R.id.lecturersId);
        events = (CardView) findViewById(R.id.eventsId);
        maps = (CardView) findViewById(R.id.mapsId);

        groupsPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, FacultiesActivity.class);
                startActivity(intent);
            }
        });
        lecturerRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, LecturerRoomsActivity.class);
                startActivity(intent);
            }
        });
        lecturers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, LecturersActivity.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
