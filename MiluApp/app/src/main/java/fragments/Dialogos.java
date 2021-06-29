package fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miluapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dialogos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dialogos extends Fragment {

    View view;
    Context context;
    Button btds;
    Button btdc;

    public Dialogos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dialogos.
     */
    // TODO: Rename and change types and number of parameters
    public static Dialogos newInstance(String param1, String param2) {
        Dialogos fragment = new Dialogos();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dialogos, container, false);
        context = view.getContext();

        lounchWidgets();
        lounEvents();
        return view;


    }

    public void lounchWidgets() {

        btds = view.findViewById(R.id.bt_ds);
        btdc = view.findViewById(R.id.bt_dc);
    }

    public void lounEvents() {
        btds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "DIALOGO SIMPLE",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("DIALOGO SIMPLE 1")
                        .setTitle("PRIMER DIALOGO")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "ACTIVO", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "DESACTIVADO", Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();
            }
        });
        btdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "PERSONALIZADO", Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = getLayoutInflater();

                View vw = inflater.inflate(R.layout.dialogo, null);
                final EditText et_cd = vw.findViewById(R.id.td);

                builder.setView(vw)
                        .setTitle("DIALOGO PERSONALIZADO")
                        .setMessage("DIALOGO NUEVO")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, et_cd.getText().toString(), Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "GRACIAS", Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();

            }
        });

    }

}