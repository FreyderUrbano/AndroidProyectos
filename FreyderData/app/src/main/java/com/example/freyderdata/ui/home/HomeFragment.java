package com.example.freyderdata.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.freyderdata.MainActivity;
import com.example.freyderdata.R;
import com.example.freyderdata.models.Student;
import com.example.freyderdata.utils.DBM;

public class HomeFragment extends Fragment {

    View root;

    EditText idET;
    EditText nameEt;
    RadioGroup genderRg;
    EditText ageEt;
    RadioButton mEt;
    RadioButton fEt;
    EditText codeEt;
    EditText semesterEt;
    Button btnsave;

    Student student;
    Context context;
    DBM dbm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        lounchWidgets();
        lounchEvents();

        return root;
    }

    public void lounchWidgets() {

        context = root.getContext();
        dbm = new DBM(context);
        idET = root.findViewById(R.id.et_id);
        nameEt = root.findViewById(R.id.et_name);
        genderRg = root.findViewById(R.id.et_gender);
        mEt = root.findViewById(R.id.m_rb);
        fEt = root.findViewById(R.id.f_rb);
        ageEt = root.findViewById(R.id.et_age);
        codeEt = root.findViewById(R.id.et_code);
        semesterEt = root.findViewById(R.id.et_semester);
        btnsave = root.findViewById(R.id.btn_save);
    }

    public void lounchEvents(){

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = new Student(
                        Integer.parseInt(idET.getText().toString()),
                        nameEt.getText().toString(),
                        mEt.isChecked()==true?true:false,
                        Short.parseShort(ageEt.getText().toString()),
                        codeEt.getText().toString(),
                        Integer.parseInt(semesterEt.getText().toString())
                );
                //MainActivity.studentslist.add(student);
                boolean ret = dbm.insertStudentTuple(student);
                if (ret == true){
                    Toast.makeText(context, "REGISTRO GUARDADO CON EXITO", Toast.LENGTH_SHORT).show();
                    idET.setText("");
                    nameEt.setText("");
                    mEt.setChecked(false);
                    fEt.setChecked(false);
                    ageEt.setText("");
                    codeEt.setText("");
                    semesterEt.setText("");
                }
                else{
                    Toast.makeText(context, "ERROR AL GUARDAR", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}