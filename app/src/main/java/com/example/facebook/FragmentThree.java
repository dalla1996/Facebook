package com.example.facebook;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import static com.example.facebook.DBHelper.DB_NAME;
import static com.example.facebook.DBHelper.DB_VERSION;

public class FragmentThree extends Fragment {
    DBHelper mydb;
    SearchView viewS;
    ListView name;
    String s[] ;

    public static FragmentThree newInstance(){
        FragmentThree fragmentTwo=new FragmentThree();
        return fragmentTwo;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mydb = new DBHelper(getContext(), DB_NAME, null, DB_VERSION);

        viewS = view.findViewById(R.id.searchDt);
        name = view.findViewById(R.id.name);

        viewMen();


    }

    public void viewMen(){
        Cursor rs = mydb.getWordMatches();
        ArrayList<String> data = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,data);



        StringBuffer buffer = new StringBuffer();

        while (rs.moveToNext()){
            buffer.append("Name: "+rs.getString(1)+"\n");
            buffer.append("Age : "+rs.getString(2)+"\n");
            buffer.append("Mark: "+rs.getString(3)+"\n\n\n");
        }

        s = buffer.toString().split("\n\n");
        for(int i=0;i<(s.length);i++){
            data.add(s[i]);
        }

        name.setAdapter(adapter);

        viewS.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });
    }

}

