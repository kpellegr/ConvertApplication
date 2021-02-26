package be.bluebanana.convertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TempFragment fragmentCelsius;
    private TempFragment fragmentFahrenheit;
    private TempFragment fragmentKelvin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCelsius = new TempFragment();
        fragmentFahrenheit = new TempFragment();
        fragmentKelvin = new TempFragment();

        fragmentCelsius.setTempFragmentListener(input -> {
            fragmentFahrenheit.updateTemp(input * 9f / 5f + 32f);
            fragmentKelvin.updateTemp(input + 273.15f);
        });
        fragmentFahrenheit.setTempFragmentListener(input -> {
            fragmentCelsius.updateTemp((input - 32f) * 5f / 9f);
            fragmentKelvin.updateTemp((input - 32f) * 5f / 9f + 273.15f);
        });
        fragmentKelvin.setTempFragmentListener(input -> {
            fragmentCelsius.updateTemp(input - 273.15f);
            fragmentFahrenheit.updateTemp((input - 273.15f) * 9f / 5f + 32f);
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_a, fragmentCelsius)
                .replace(R.id.layout_b, fragmentFahrenheit)
                .replace(R.id.layout_c, fragmentKelvin)
                .commit();
    }

}