package com.example.listycity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add_btn ;
    Button con_btn;
    String item;
    Button rm_btn ;
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int position=-1;
    String item1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.listView);
        Button add_btn = findViewById(R.id.edit_btn);

        con_btn = findViewById(R.id.confirm);


        add_btn.setOnClickListener(new View.OnClickListener() {
            EditText user_in = findViewById(R.id.user_in);

            @Override
            public void onClick(View view) {
                con_btn.setVisibility(View.VISIBLE);
                user_in.setVisibility(View.VISIBLE);

            }
        });
        con_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);



            }
        });





        dataList = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item1 = adapterView.getItemAtPosition(i).toString();
                item = adapterView.getItemAtPosition(i).toString() +" has been selected";
                position=i;
//                showCustomDialog();


                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.custom_dialog);


                EditText existing = dialog.findViewById(R.id.city_name);

                String exist = existing.getText().toString();
                existing.setText(item1);
                EditText edit_box = dialog.findViewById(R.id.initials);
                Button rm_btn = dialog.findViewById(R.id.rm_btn);


                Button edit_btn = dialog.findViewById(R.id.edit_btn);
                Button cancel_btn = dialog.findViewById(R.id.cancel_btn);
                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                edit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String user_edit = edit_box.getText().toString();

                        if (user_edit.length() == 0){
                            Toast.makeText(MainActivity.this,"Please write in the edit TextBox!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dataList.set(position,user_edit);
                            cityAdapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this,"Changes applied successfully!", Toast.LENGTH_SHORT).show();


                            dialog.dismiss();
                        }
                    }
                });
                rm_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(position == -1){
                            Toast.makeText(MainActivity.this,"Nothing to Delete!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dataList.remove(position);
                            cityAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                            position=-1;
                            Toast.makeText(MainActivity.this,"Deleted Successfully!",Toast.LENGTH_SHORT).show();

                        }

                    }

                });


                dialog.show();





                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();

            }
        });





    }


    private void addItem(View view) {
        EditText user_in = findViewById(R.id.user_in);

        EditText input = findViewById(R.id.user_in);
        String itemText = input.getText().toString();

        if (!(itemText).equals("")){
            cityAdapter.add(itemText);
            input.setText("");
            con_btn.setVisibility(View.INVISIBLE);
            user_in.setVisibility(View.INVISIBLE);
        }
        else{
            Toast.makeText(this, "Enter City!", Toast.LENGTH_LONG).show();

        }

    }
}