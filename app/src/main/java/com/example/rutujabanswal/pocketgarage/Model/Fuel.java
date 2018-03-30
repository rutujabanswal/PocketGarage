package com.example.rutujabanswal.pocketgarage.Model;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rutujabanswal.pocketgarage.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fuel extends Fragment {

    public Fuel() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fuel, container, false);
    }

}
