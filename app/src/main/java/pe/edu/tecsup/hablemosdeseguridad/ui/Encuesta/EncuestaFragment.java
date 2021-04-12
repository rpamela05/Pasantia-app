package pe.edu.tecsup.hablemosdeseguridad.ui.Encuesta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import pe.edu.tecsup.hablemosdeseguridad.R;

public class EncuestaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txt;
    private MaterialButton btnsgte;

    public EncuestaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EncuestaFragment newInstance(String param1, String param2) {
        EncuestaFragment fragment = new EncuestaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences preferences=getActivity().getSharedPreferences("Credencial", 0);
        String dni=preferences.getString("dni","");
        View v= inflater.inflate(R.layout.fragment_encuesta, container, false);
        btnsgte=v.findViewById(R.id.btnSiguiente);
        btnsgte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encuesta2Fragment fr=new Encuesta2Fragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,fr);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }

}