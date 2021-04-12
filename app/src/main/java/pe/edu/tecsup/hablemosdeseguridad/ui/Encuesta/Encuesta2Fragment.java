package pe.edu.tecsup.hablemosdeseguridad.ui.Encuesta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import pe.edu.tecsup.hablemosdeseguridad.R;
import pe.edu.tecsup.hablemosdeseguridad.model.Video;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Encuesta2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Encuesta2Fragment extends Fragment {

    // Data Spinner Firebase
    Spinner mSpinnerVideos;
    DatabaseReference mDatabase;

    ArrayList<String> arrayList = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MaterialButton btnSgte;

    public Encuesta2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Encuesta2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Encuesta2Fragment newInstance(String param1, String param2) {
        Encuesta2Fragment fragment = new Encuesta2Fragment();
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

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_encuesta2, container, false);

        // Show database spinner Firebase
        mSpinnerVideos = v.findViewById(R.id.spinner_video);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        showDatabaseSpinner();

        btnSgte=v.findViewById(R.id.btnSiguiente);
        btnSgte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Encuesta3Fragment fr=new Encuesta3Fragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment,fr);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }

    public void showDatabaseSpinner() {
        final List<Video> videos = new ArrayList<>();
        mDatabase.child("Video").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds: snapshot.getChildren()) {
                            String id = ds.getKey();
                            String title = ds.child("title").getValue().toString();
                            String videoId = ds.child("videoId").getValue().toString();
                            videos.add(new Video(id, title, videoId));
                    }

                    ArrayAdapter<Video> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, videos);
                    mSpinnerVideos.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}