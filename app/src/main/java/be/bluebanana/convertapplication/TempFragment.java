package be.bluebanana.convertapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
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

        MyTextWatcher textWatcher = new MyTextWatcher(listener);

        etTemp.setOnFocusChangeListener((v1, hasFocus) -> {
            if (hasFocus) {
                etTemp.addTextChangedListener(textWatcher);
            }
            else {
                etTemp.removeTextChangedListener(textWatcher);
            }
        });

        v.findViewById(R.id.button_convert).setOnClickListener(bv -> {
            String input = etTemp.getText().toString();
            listener.onInputSent(Float.parseFloat(input));
        });

        return v;
    }

    // Ontvangt data van buitenaf
    public void updateTemp(float temp) {
        etTemp.setText(String.format("%.2f", temp));
    }

    public void setTempFragmentListener (TempFragmentListener tf_listener) {
        this.listener = (TempFragmentListener)tf_listener;
    }

    static class MyTextWatcher implements TextWatcher {

        TempFragmentListener listener;

        public MyTextWatcher (TempFragmentListener listener) {
            this.listener = listener;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // intentionally left blank
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // intentionally left blank
        }

        @Override
        public void afterTextChanged(Editable s) {
            float temp = 0f;
            try {
                temp = Float.parseFloat(s.toString());
            }
            catch (NumberFormatException ignored) {}
            listener.onInputSent(temp);
        }
    }
}