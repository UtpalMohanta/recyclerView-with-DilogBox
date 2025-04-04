package com.example.cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecycularContactAdapter adapter;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ArrayList<ContactModel>arrContact=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler);
        floatingActionButton=findViewById(R.id.bTn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);

                EditText editText=dialog.findViewById(R.id.ediT1);
                EditText editText1=dialog.findViewById(R.id.ediT2);
                Button button=dialog.findViewById(R.id.bTN);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",number="";
                        if(!editText.getText().toString().equals("")) {
                            name = editText.getText().toString();
                        }else
                        {
                            Toast.makeText(MainActivity.this,"Please Enter Contact name",Toast.LENGTH_SHORT).show();
                        }
                        if(!editText1.getText().toString().equals("")) {
                            number = editText1.getText().toString();
                        }else
                        {
                            Toast.makeText(MainActivity.this,"Please Enter Contact number",Toast.LENGTH_SHORT).show();
                        }

                        arrContact.add(new ContactModel(name,number));
                        adapter.notifyItemInserted(arrContact.size()-1);
                        recyclerView.scrollToPosition(arrContact.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        arrContact.add(new ContactModel(R.drawable.a,"Utpal","01761089484"));
        arrContact.add(new ContactModel(R.drawable.b,"Haripada","01728503019"));
        arrContact.add(new ContactModel(R.drawable.c,"Basanty","017610878911"));
        arrContact.add(new ContactModel(R.drawable.d,"Utpal1","01761089484"));
        arrContact.add(new ContactModel(R.drawable.e,"Haripada1","01728503019"));
        arrContact.add(new ContactModel(R.drawable.f,"Basanty1","017610878911"));
        arrContact.add(new ContactModel(R.drawable.g,"Utpal2","01761089484"));
        arrContact.add(new ContactModel(R.drawable.h,"Haripada2","01728503019"));
        arrContact.add(new ContactModel(R.drawable.i,"Basanty2","017610878911"));
        arrContact.add(new ContactModel(R.drawable.j,"Utpal3","01761089484"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecycularContactAdapter(this,arrContact);
        recyclerView.setAdapter(adapter);


        /*
        kotlin

        private lateinit var adapter: RecyclerContactAdapter
        private lateinit var floatingActionButton: FloatingActionButton
        private lateinit var recyclerView: RecyclerView
        private val arrContact = ArrayList<ContactModel>()

        recyclerView = findViewById(R.id.recyclerView)
        floatingActionButton = findViewById(R.id.bTn)

        floatingActionButton.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_update_lay)

            val editText = dialog.findViewById<EditText>(R.id.ediT1)
            val editText1 = dialog.findViewById<EditText>(R.id.ediT2)
            val button = dialog.findViewById<Button>(R.id.bTN)

            button.setOnClickListener {
                val name = editText.text.toString().trim()
                val number = editText1.text.toString().trim()

                if (name.isEmpty()) {
                    Toast.makeText(this, "Please Enter Contact Name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (number.isEmpty()) {
                    Toast.makeText(this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                arrContact.add(ContactModel(name, number))
                adapter.notifyItemInserted(arrContact.size - 1)
                recyclerView.scrollToPosition(arrContact.size - 1)
                dialog.dismiss()
            }
            dialog.show()
        }

        // Adding sample contacts
        arrContact.apply {
            add(ContactModel(R.drawable.a, "Utpal", "01761089484"))
            add(ContactModel(R.drawable.b, "Haripada", "01728503019"))
            add(ContactModel(R.drawable.c, "Basanty", "017610878911"))
            add(ContactModel(R.drawable.d, "Utpal1", "01761089484"))
            add(ContactModel(R.drawable.e, "Haripada1", "01728503019"))
            add(ContactModel(R.drawable.f, "Basanty1", "017610878911"))
            add(ContactModel(R.drawable.g, "Utpal2", "01761089484"))
            add(ContactModel(R.drawable.h, "Haripada2", "01728503019"))
            add(ContactModel(R.drawable.i, "Basanty2", "017610878911"))
            add(ContactModel(R.drawable.j, "Utpal3", "01761089484"))
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerContactAdapter(this, arrContact)
        recyclerView.adapter = adapter
         */
    }
}