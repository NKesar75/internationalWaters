package domain.internationalwaters.internationalwaters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class Home extends AppCompatActivity {

    private TextView distancelabel;
    private Button distancebuttonid;
    private TextView carlabel;
    private TextView walklabel;
    private TextView planelabel;
    private TextView timeTaken;
    private Location location;
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    Double lon;
    Double lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        distancebuttonid = findViewById(R.id.distance_button);

        distancelabel = findViewById(R.id.Distance_id);
        carlabel = findViewById(R.id.car_id);
        planelabel = findViewById(R.id.flight_id);
        walklabel = findViewById(R.id.walk_id);
        timeTaken = findViewById(R.id.timetaken_id);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();


        distancebuttonid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getLocation();
                if(location != null)
                {
                    Double wintlat = 37.139943;
                    Double wintlon = -152.201824;

                    Double eintlat = 35.868339;
                    Double eintlon = -38.471365;

                    double flat3 = ((wintlat - lat) * (Math.PI / 180.0f));
                    double flon3 = ((wintlon - lon) * (Math.PI / 180.0f));

                    double x = sin(flat3 / 2.0f) * sin(flat3 / 2.0f) + cos((lat) * (0.01745329251)) * cos((wintlat) * (0.01745329251)) * sin(flon3 / 2.0f) * sin(flon3 / 2.0f);
                    double west = (20903520.0f * (2.0f * atan2(sqrt(x), sqrt(1.0f - x))));
                    west = west * 0.000189394;

                    double West = roundTwoDecimals(west);


                    flat3 = ((eintlat - lat) * (Math.PI / 180.0f));
                    flon3 = ((eintlon - lon) * (Math.PI / 180.0f));

                    x = sin(flat3 / 2.0f) * sin(flat3 / 2.0f) + cos((lat) * (0.01745329251)) * cos((eintlat) * (0.01745329251)) * sin(flon3 / 2.0f) * sin(flon3 / 2.0f);

                    double east = (20903520.0f * (2.0f * atan2(sqrt(x), sqrt(1.0f - x))));
                    east = east * 0.000189394;


                    double East = roundTwoDecimals(east);


                    if (east > west) {
                        distancelabel.setText("Distance: " + West + " Miles");
                        timeTaken.setText("Approximate time to reach:");
                        walklabel.setText("Walk: " + roundTwoDecimals(West / 3) + " Hours");
                        carlabel.setText("Car: " + roundTwoDecimals(West / 70) + " Hours");
                        planelabel.setText("Plane: " + roundTwoDecimals(West / 575) + " Hours");
                    } else {
                        distancelabel.setText("Distance: " + East + " Miles");
                        timeTaken.setText("Approximate time to reach:");
                        walklabel.setText("Walk: " + roundTwoDecimals(East / 3) + " Hours");
                        carlabel.setText("Car: " + roundTwoDecimals(East / 70) + " Hours");
                        planelabel.setText("Plane: " + roundTwoDecimals(East / 575) + " Hours");
                    }
                }
                else
                {
                    Toast.makeText(Home.this, "Please Enable Your Location", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                lat = location.getLatitude();
                lon = location.getLongitude();
            }
            else
            {
                Toast.makeText(Home.this, "Please Enable Your Location", Toast.LENGTH_SHORT).show();
            }
        }
    }


        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            switch (requestCode) {
                case REQUEST_LOCATION:
                    getLocation();
                    break;
            }
        }

    }


