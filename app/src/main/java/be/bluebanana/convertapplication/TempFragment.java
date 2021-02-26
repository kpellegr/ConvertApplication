package be.bluebanana.convertapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TempFragment extends Fragment {

    public interface TempFragmentListener {
        void onInputSent(float temp);
    }

    private EditText etTemp;
    private TempFragmentListener listener;

    public TempFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_temp, container, false);

        etTemp = v.findViewById(R.id.et_temp);
        v.findViewById(R.id.button_convert).setOnClickListener(bv -> {
            String input = etTemp.getText().toString();
            listener.onInputSent(Float.parseFloat(input));
        });

        return v;
    }

    // Ontvangt data van buitenaf (bvb als er in Fragment B op "ok" wordt gedrukt
    public void updateTemp(float temp) {
        etTemp.setText(String.format("%.2f", temp));
    }

    public void setTempFragmentListener (TempFragmentListener tf_listener) {
        if (tf_listener instanceof TempFragmentListener) {
            this.listener = (TempFragmentListener)tf_listener;
        }
        else {
            throw new RuntimeException(
                    String.format("%s must implement TempFragmentListener", tf_listener.toString())
            );
        }
    }
}