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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TempFragment extends Fragment {

    public interface TempFragmentListener {
        void onInputSent(float temp);
    }

    private TextInputEditText etTemp;
    private TextInputLayout tilTemp;
    private TempFragmentListener listener;
    private String symbol;

    public TempFragment(String symbol_) {
        this.symbol = symbol_;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_temp, container, false);

        etTemp = v.findViewById(R.id.et_temp);
        tilTemp = v.findViewById(R.id.til_temp);
        tilTemp.setSuffixText(symbol);

        MyTextWatcher textWatcher = new MyTextWatcher(listener, tilTemp);

        etTemp.setOnFocusChangeListener((v1, hasFocus) -> {
            if (hasFocus) {
                etTemp.addTextChangedListener(textWatcher);
            }
            else {
                etTemp.removeTextChangedListener(textWatcher);
            }
        });

        return v;
    }

    // Ontvangt data van buitenaf
    public void updateTemp(float temp) {
        etTemp.setText(String.format("%.2f", temp));
        tilTemp.setError(null);
    }

    public void setTempFragmentListener (TempFragmentListener tf_listener) {
        this.listener = (TempFragmentListener)tf_listener;
    }

    static class MyTextWatcher implements TextWatcher {

        TempFragmentListener listener;
        TextInputLayout tilTemp;

        public MyTextWatcher (TempFragmentListener listener, TextInputLayout tilTemp) {
            this.listener = listener;
            this.tilTemp = tilTemp;
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
            tilTemp.setError(null);
            try {
                temp = Float.parseFloat(s.toString());
            }
            catch (NumberFormatException e) {
                tilTemp.setError("Gelieve een getal in te geven");
            }
            listener.onInputSent(temp);
        }
    }
}