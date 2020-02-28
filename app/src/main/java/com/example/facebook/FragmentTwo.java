package com.example.facebook;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.facebook.DBHelper.DATABASE_NAME;


public class FragmentTwo extends Fragment{
    DBHelper myDb;


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
        myDb=new DBHelper(getContext(),DATABASE_NAME,null,1);





    }

}
