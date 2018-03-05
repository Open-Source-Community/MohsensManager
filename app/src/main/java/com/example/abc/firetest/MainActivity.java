package com.example.abc.firetest;

import android.app.Activity;
import android.content.Context;
import android.os.Debug;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private FirebaseDatabase database;
    private DatabaseReference myref;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<Information> data;
    private Button btn ;
    Context thisCnxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisCnxt = this;

        data = new ArrayList<Information>();
        intiateData();
/**
        for (int i=0; i<data.size(); i++)
        {
            database= FirebaseDatabase.getInstance();
            myref = database.getReference();
            myref.child("Names").push().setValue(data.get(i));
        }
**/
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseDatabase db =FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference();
        dbref.child("Names").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                int i=0;
                for (DataSnapshot child : children)
                {
                    String Name = (String) child.child("name").getValue();
                    Long mohsens = (Long) child.child("mohsens").getValue();
                    String ID = (String) child.getKey();
                    data.set(i , new Information(data.get(i).id  ,(int) (long) mohsens, data.get(i).image , Name , ID));
                    Log.d(Name , "" +data.get(i).mohsens + " " + ID);
                    i++;
                }


                myAdapter.notifyDataSetChanged();
                Toast.makeText(thisCnxt, "Updates occurred",
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                myAdapter = new MyAdapter(thisCnxt, data);
                recyclerView.setAdapter(myAdapter);
            }
        }, 1000);



    }
public void intiateData()
{
    data.add(
            new Information(1 , 1 , R.drawable.nafe3 , "Ahmed Nafe3"));
    data.add(
            new Information(1 , 130 , R.drawable.ahmed_samir , "Ahmed Samir"));
    data.add(
            new Information(1 , 1 , R.drawable.mai , "Mai Elgendy"));
    data.add(
            new Information(1 , 1 , R.drawable.sara , "Sara Alaa"));
    data.add(
            new Information(1 , 1 , R.drawable.omar_alsawaf , "Omar Alsawaf"));
    data.add(
            new Information(1 , 1 , R.drawable.taher , "Amr Taher"));
    data.add(
            new Information(1 , 1 , R.drawable.saieed , "Saieed"));
    data.add(
            new Information(1 , 1 , R.drawable.mostafa , "Mostafa Mohamed"));
    data.add(
            new Information(1 , 1 , R.drawable.musta , "Musta Mohamed"));
    data.add(
            new Information(1 , 1 , R.drawable.ahmad_hussien , "Ahmad Hussien"));
    data.add(
            new Information(1 , 1 , R.drawable.mohamed_ashraf , "Mohamed Ashraf"));
    data.add(
            new Information(1 , 1 , R.drawable.abdo , "Abdelrahman Adel"));
    data.add(
            new Information(1 , 1 , R.drawable.rashad , "Mohamed Rashad"));
    data.add(
            new Information(1 , 1 , R.drawable.kamal , "Kamal Saad"));

    data.add(
            new Information(1 , 1 , R.drawable.tohamy , "Ahmed Tohamy"));

    data.add(
            new Information(1 , 1 , R.drawable.mohamed_amr , "Mohamed Amr"));


}

}
