package domain.internationalwaters.internationalwaters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView distancelabel;
    private Button distancebuttonid;
    private Button directionbuttonid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        distancebuttonid = (Button) findViewById(R.id.distance_button);
        directionbuttonid = (Button) findViewById(R.id.directions_button);
        distancelabel = (TextView) findViewById(R.id.Distance_id);
    }


   

}
