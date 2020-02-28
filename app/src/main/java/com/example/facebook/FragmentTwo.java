package com.example.facebook;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static com.example.facebook.DBHelper.DATABASE_NAME;
import static com.example.facebook.DBHelper.DB_VERSION;

public class FragmentTwo extends Fragment{
    DBHelper db;
    View view;
    String s[];
    ListView view1;


    public static FragmentTwo newInstance(){
        FragmentTwo fragmetTwo = new FragmentTwo();
        return fragmetTwo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DBHelper(getContext(), DATABASE_NAME, null, DB_VERSION);
        view1 = view.findViewById(R.id.view);
        view();
    }

    private void view() {
        Cursor rs = db.getAllDetails();
        ArrayList<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,data);
        if ((rs.getCount())==0){
            showMessage("Error","Nothing On the Database");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while (rs.moveToNext()){
            buffer.append("Name: "+rs.getString(1)+"\n");
            buffer.append("Age: "+rs.getString(2)+"\n");
            buffer.append("Mark: "+rs.getString(3)+"\n");
        }
        s = buffer.toString().split("\n\n");
        for (int i=0;i<(s.length);i++){
            data.add(s[i]);
        }
        view1.setAdapter(adapter);
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}