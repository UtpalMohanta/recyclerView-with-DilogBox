package com.example.cardview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycularContactAdapter extends RecyclerView.Adapter<RecycularContactAdapter.ViewHolder>{

    Context context;
    ArrayList<ContactModel>arrContact;
    int lastposition=-1;
    RecycularContactAdapter(Context context,ArrayList<ContactModel>arrContact)
    {
        this.context=context;
        this.arrContact=arrContact;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder,int position ) {
        ContactModel contactModel=(ContactModel) arrContact.get(position);
       holder.img.setImageResource(contactModel.img);
       holder.name.setText(contactModel.name);
       holder.number.setText(contactModel.number);
       setAnimation(holder.itemView,position);

       holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Dialog dialog=new Dialog(context);
               dialog.setContentView(R.layout.add_update_lay);

               TextView textView=dialog.findViewById(R.id.tXt);
               EditText editText=dialog.findViewById(R.id.ediT1);
               EditText editText1=dialog.findViewById(R.id.ediT2);
               Button button=dialog.findViewById(R.id.bTN);

               textView.setText(R.string.update_contact);
               button.setText(R.string.update2);

               editText.setText(arrContact.get(position).name);
               editText1.setText(arrContact.get(position).number);
               button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       String name="",number="";
                       if(!editText.getText().toString().equals("")) {
                           name = editText.getText().toString();
                       }else
                       {
                           Toast.makeText(context,"Please Enter Contact name",Toast.LENGTH_SHORT).show();
                       }
                       if(!editText1.getText().toString().equals("")) {
                           number = editText1.getText().toString();
                       }else
                       {
                           Toast.makeText(context,"Please Enter Contact number",Toast.LENGTH_SHORT).show();
                       }
                    arrContact.set(position,new ContactModel(name,number));
                       notifyItemChanged(position);
                       dialog.dismiss();
                   }
               });
               dialog.show();
           }
       });
       holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {

               AlertDialog.Builder builder=new AlertDialog.Builder(context)
               .setTitle("Delete Contact")
                       .setMessage("Are you sure want to delete?")
                       .setIcon(R.drawable.delete)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               arrContact.remove(position);
                               notifyItemRemoved(position);
                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       });
               builder.show();
               return true;
           }
       });
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        ImageView img;
        TextView name,number;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.txt);
            number=itemView.findViewById(R.id.txt1);
            linearLayout=itemView.findViewById(R.id.ll);
        }
    }
    private void setAnimation(View view,int position)
    {
       if(position>lastposition)
       {
           Animation animation= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
           view.startAnimation(animation);
       }

    }
    /*
    kotlin
    class RecyclerContactAdapter(
    private val context: Context,
    private val arrContact: ArrayList<ContactModel>
) : RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>() {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactModel = arrContact[position]
        holder.img.setImageResource(contactModel.img)
        holder.name.text = contactModel.name
        holder.number.text = contactModel.number
        setAnimation(holder.itemView, position)

        // Click Listener for updating contact
        holder.linearLayout.setOnClickListener {
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.add_update_lay)

            val textView = dialog.findViewById<TextView>(R.id.tXt)
            val editText = dialog.findViewById<EditText>(R.id.ediT1)
            val editText1 = dialog.findViewById<EditText>(R.id.ediT2)
            val button = dialog.findViewById<Button>(R.id.bTN)

            textView.setText(R.string.update_contact)
            button.setText(R.string.update2)

            editText.setText(arrContact[position].name)
            editText1.setText(arrContact[position].number)

            button.setOnClickListener {
                val name = editText.text.toString().trim()
                val number = editText1.text.toString().trim()

                if (name.isEmpty()) {
                    Toast.makeText(context, "Please Enter Contact Name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (number.isEmpty()) {
                    Toast.makeText(context, "Please Enter Contact Number", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                arrContact[position] = ContactModel(name, number)
                notifyItemChanged(position)
                dialog.dismiss()
            }
            dialog.show()
        }

        // Long Click Listener for deleting contact
        holder.linearLayout.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete?")
                .setIcon(R.drawable.delete)
                .setPositiveButton("Yes") { _, _ ->
                    arrContact.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }

    override fun getItemCount(): Int = arrContact.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.txt)
        val number: TextView = itemView.findViewById(R.id.txt1)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.ll)
    }

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            view.startAnimation(animation)
        }
    }
     */
}
