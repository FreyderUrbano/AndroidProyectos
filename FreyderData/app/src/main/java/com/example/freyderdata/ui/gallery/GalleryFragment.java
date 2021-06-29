package com.example.freyderdata.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.freyderdata.MainActivity;
import com.example.freyderdata.R;
import com.example.freyderdata.models.Student;
import com.example.freyderdata.utils.DBM;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    TextView textView;
    Context context;
    DBM dbm;
    String data;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_gallery, container, false);

        lounchData();
        lounchWidgets();
        lounchEvents();


        return root;
    }

    public void lounchWidgets() {

        textView = root.findViewById(R.id.text_gallery);
        textView.setText(data);

    }

    public void lounchEvents() {

    }

    public void lounchData() {
        context = root.getContext();
        dbm = new DBM(context);
        data = "";

        ArrayList<Student> l = dbm.listStudent();
        for (int i = 0; i < l.size();i++) {
            data += "\n\t***\tDatos del estudiante " + (i + 1) + "\t***\n";
            data += l.get(i).toString();
        }
    }
}