package com.fpoly.lab5_ph32739;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListStudentAdapter extends BaseAdapter {
    Activity atv;
    ArrayList<Student> list;
public static String setEnableClick = "";
    public ListStudentAdapter(Activity atv, ArrayList<Student> list) {
        this.atv = atv;
        this.list = list;
    }

    public ListStudentAdapter() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = atv.getLayoutInflater();
        convertView = inflater.inflate(R.layout.editlistview, parent, false);

        Student std = list.get(position);

        TextView place = convertView.findViewById(R.id.txt_place);
        TextView name = convertView.findViewById(R.id.txt_nameStudent);
        TextView hometown = convertView.findViewById(R.id.txt_hometown);

        place.setText(std.getStudyAt());
        name.setText("Họ tên: " + std.getNameStd());
        hometown.setText("Địa chỉ: " + std.getHometown());

        Button btnRemove = convertView.findViewById(R.id.btn_remove);
        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirm = new AlertDialog.Builder(atv);
                confirm.setMessage("Bán có muốn xóa sinh viên này không ?");

                confirm.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(atv, "Xoá thành công !!!", Toast.LENGTH_SHORT).show();
                    }
                });
                confirm.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                confirm.show();
            }
        });

        Button btnUpdate = convertView.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setEnableClick ="enable";
            String updateName = name.getText().toString().trim();
            String updateHometown = hometown.getText().toString().trim();
            String updatePlace = place.getText().toString().trim();
            Intent intent = new Intent(atv, MainActivity.class);
            intent.putExtra("place",updatePlace);
            intent.putExtra("name",updateName.substring(updateName.indexOf("",8),updateName.length()));
            intent.putExtra("home",updateHometown.substring(updateHometown.indexOf("",8),updateHometown.length()));
            atv.startActivity(intent);

            }
        });



        return convertView;
    }

}
