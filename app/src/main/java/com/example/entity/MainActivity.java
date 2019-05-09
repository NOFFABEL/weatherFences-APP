package com.example.entity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umons.fpms.model.WeatherDB;
import com.umons.fpms.model.WeatherHttpClient;
import com.umons.fpms.utils.WeatherParser;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txt_location, txt_date, txt_desc, txt_temp, txt_temp_max, txt_temp_min, txt_humidity;
    TextView txt_pressure, txt_wind, txt_username, txt_email;
    ImageView img_temp;

    private int user_id;
    Intent iUser;
    boolean canSave = true;
    Fragment weatherFragment;
    Weather weather  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String city = "Mons,BE";

        txt_date = (TextView) findViewById(R.id.lbl_date);
        txt_desc = (TextView) findViewById(R.id.lbl_desc);
        txt_humidity = (TextView) findViewById(R.id.lbl_humidity_value);
        txt_location = (TextView) findViewById(R.id.lbl_location);
        txt_temp = (TextView) findViewById(R.id.lbl_temp);
        txt_pressure = (TextView) findViewById(R.id.lbl_pressure_value);
        txt_temp_max = (TextView) findViewById(R.id.lbl_max_temp_value);
        txt_temp_min = (TextView) findViewById(R.id.lbl_min_temp_value);
        txt_wind = (TextView) findViewById(R.id.lbl_wind_value);
        img_temp = (ImageView) findViewById(R.id.img_temp);

        //btn_save = (Button) findViewById(R.id.act_save);

        /*btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Insert new WeatherDB into DB.
                if(canSave) {
                    btn_save.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "I may Save in DB.", Toast.LENGTH_SHORT).show();
                    btn_save.setEnabled(true);
                } else {
                    Toast.makeText(getApplicationContext(), "This may not do anything.", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txt_username = headerView.findViewById(R.id.lbl_username);
        txt_email = headerView.findViewById(R.id.lbl_email);

        weather = new Weather();
        WeatherTask task = new WeatherTask();
        task.execute(city);
        iUser = getIntent();

        /*
        weatherFragment = new WeatherCall();
        weatherFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, weatherFragment)
                .addToBackStack(null)
                .commit();
        */
        /*
        txt_username.setText( iUser.getStringExtra("com.example.entity.username") );
        txt_email.setText(iUser.getStringExtra("com.example.entity.email"));
        */
        user_id = iUser.getIntExtra("com.example.entity.id", 1);
        //String usr_str = iUser.getStringExtra("com.example.entity.username") +
         //       " " + iUser.getStringExtra("com.example.entity.email");
        //Toast.makeText(getApplicationContext(), usr_str, Toast.LENGTH_SHORT).show();
        ///Log.i("INTENT USER_ID", "**************   " + usr_str);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.act_save) {
            item.setVisible(false);
            item.setEnabled(false);
            if(weather != null) {
                Toast.makeText(getApplicationContext(), "I may Save in DB.", Toast.LENGTH_SHORT).show();
                insert(weather);
                item.setEnabled(true);
                item.setVisible(true);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_historic) {
            //btn_save.setVisibility(View.INVISIBLE);
            canSave = false;
            weatherFragment = new WeatherHisto();

            FrameLayout mainFrame = (FrameLayout) findViewById(R.id.frame);
            LayoutInflater infalter = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View newContent = infalter.inflate(R.layout.weather_histo, mainFrame, true);
            mainFrame.removeAllViews();
            mainFrame.addView(newContent);
            Toast.makeText(this, "Historic action OK.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_weather) {
            //btn_save.setVisibility(View.VISIBLE);
            canSave = true;
            weatherFragment = new WeatherCall();
        } else if (id == R.id.nav_out) {
            // Clear session before
            iUser = null;
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        if(weatherFragment != null) {
            weatherFragment.setArguments(getIntent().getExtras());
            /*getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, weatherFragment)
                    .addToBackStack(null)
                    .commit();*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void insert(Weather weather) {
        WeatherDB dbWeather = new WeatherDB(user_id, weather.getTemperature().getTemp(),
                weather.getTemperature().getTemp_min(), weather.getTemperature().getTemp_max(),
                weather.getCity(), weather.getCountry(), weather.getDate_in().toString(),
                weather.getIcon());

        Log.i("WeatherDB - Insert  ", "******* " + dbWeather.toString());
    }

    /**
     * InnerClass
     */
    @SuppressLint("StaticFieldLeak")
    private class WeatherTask extends AsyncTask<String, Void, Weather> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param args The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Weather doInBackground(String... args) {
            WeatherHttpClient http = new WeatherHttpClient();
            String data = http.getWeatherData(args[0]);

            try {
                weather = WeatherParser.getWeather(data);
                //insert(weather);
                // Now let's take the iconData corresponding to the code in weather.getIcon()
                weather.setIconData(http.getImageFromCode(weather.getIcon()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("Weather", "doInBackground: " + weather.toString());

            return weather;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         *
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param weather The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(final Weather weather) {
            super.onPostExecute(weather);

            if( weather.getIconData() != null ) {
                //img_temp.setImageAlpha(25);
                img_temp.setImageBitmap(null);
                Bitmap bitmap = Bitmap.createScaledBitmap(weather.getIconData(), 280, 220, true);
                img_temp.setImageBitmap(bitmap);
                //bitmap.recycle();
                Log.i("getIcon", "onPostExecute: Weather.getIcon() OK !!!!!!!");
            }

            txt_location.setText(String.format("%s, %s", weather.getCity(), weather.getCountry()));
            txt_date.setText(weather.getDate_in().toString());
            txt_desc.setText(weather.getDesc());
            txt_humidity.setText(String.format("%s%%", String.valueOf(weather.getTemperature().getHumidity())));
            txt_temp.setText(String.format(Locale.FRANCE,"%.0f", weather.getTemperature().getTemp ()));
            txt_temp_max.setText(String.format("%s°C", String.format(Locale.FRANCE, "%.1f", weather.getTemperature().getTemp_max())));
            txt_temp_min.setText(String.format("%s°C", String.format(Locale.FRANCE, "%.1f", weather.getTemperature().getTemp_min())));
            txt_pressure.setText(String.format("%s hPa", String.format(Locale.FRANCE, "%.1f", weather.getTemperature().getPressure())));

            StringBuilder deg = new StringBuilder();
            deg.append(String.format(Locale.FRANCE,"%.1f", weather.getWind().getDeg()));
            if(deg != null) deg.append("° ");
            txt_wind.setText(String.format("%s%s mps ", deg, String.format(Locale.FRANCE, "%.1f", weather.getWind().getSpeed())));
        }
    }


}