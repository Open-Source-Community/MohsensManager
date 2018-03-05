package com.example.abc.firetest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;
import java.util.Collections;
import java.util.List;

/**
 * Created by 'abc on 3/4/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mCtx;
    public List<Information> data = Collections.emptyList();

    public MyAdapter(Context mCtx, List<Information> data) {
        this.mCtx = mCtx;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.custome_row , null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Information info = data.get(position);
        holder.Name.setText(info.name);
        int temp = info.mohsens;
        String temp2 = ""+temp;
        holder.Mohsens.setText(temp2);
        holder.image.setImageDrawable(mCtx.getResources().getDrawable(info.image));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!holder.editText.getText().toString().matches("")) {
                    String UserID = data.get(position).UID;
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    int answer = data.get(position).mohsens + Integer.parseInt(holder.editText.getText().toString());
                    DatabaseReference dbref = db.getReference();
                    String query = "Names/" + UserID + "/mohsens";
                    try {
                        dbref.child(query).setValue(answer);
                    } catch (Exception e) {
                        Log.i("failture", e.getMessage());
                    }
                }
                else
                {
                    //
                    Toast.makeText(mCtx, "This is my Toast message!",
                            Toast.LENGTH_LONG).show();
                }
                holder.editText.setText("");
            }

        });

          return;
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name , Mohsens ;
        ImageView image;
        EditText editText;
        Button button;
        public MyViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.name);
            Mohsens = (TextView) itemView.findViewById(R.id.mohsens);
            image = (ImageView) itemView.findViewById(R.id.image);
            editText = (EditText) itemView.findViewById(R.id.num);
            button = (Button) itemView.findViewById(R.id.plus);
        }
    }
}
