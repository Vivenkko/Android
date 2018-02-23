package vivenkko.vweather;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import vivenkko.vweather.model.Fragments.ForecastInfoFragment;
import vivenkko.vweather.model.Fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity implements WeatherFragment.OnFragmentInteractionListener {
cd de   re  cd
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fM = getSupportFragmentManager();
            FragmentTransaction transaction = fM.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_weather:
                    transaction.replace(R.id.containerFragments, new WeatherFragment()).commit();
                    return true;
                case R.id.navigation_forecast:
                    transaction.replace(R.id.containerFragments, new ForecastInfoFragment()).commit();
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerFragments, new WeatherFragment())
                .commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
