package com.example.facebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.facebook.DBHelper.DATABASE_NAME;
import static com.example.facebook.DBHelper.DB_VERSION;


public class FragmetOne extends Fragment {
    DBHelper myDb;
    EditText E_name,E_age,E_marks;
    Button Button1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }


    public static FragmetOne newInstance(){

        FragmetOne fragmetOne = new FragmetOne();
        return fragmetOne;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_one,container,false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDb = new DBHelper(getContext(),DATABASE_NAME,null,DB_VERSION);

        E_name =(EditText) view.findViewById(R.id.name);
        E_age=(EditText)view.findViewById(R.id.age);
        E_marks=(EditText)view.findViewById(R.id.marks);
        Button1=(Button)view.findViewById(R.id.btn);
        addData();


    }//--

    public void addData(){
        Button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInsert= myDb.insertData(E_name.getText().toString(),E_age.getText().toString(),E_marks.getText().toString());
                        if (isInsert=true)
                            Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
