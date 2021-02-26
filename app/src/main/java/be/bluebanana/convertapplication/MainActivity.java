package be.bluebanana.convertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_a, fragmentA)
                .replace(R.id.layout_b, fragmentB)
                .commit();
    }

    @Override
    public void onInputASent(String input) {
        fragmentB.updateFahrenheit(input);
    }

    @Override
    public void onInputBSent(String input) {
        fragmentA.updateCelcius(input);
    }
}