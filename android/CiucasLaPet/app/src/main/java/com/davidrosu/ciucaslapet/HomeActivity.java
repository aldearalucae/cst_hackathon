package com.davidrosu.ciucaslapet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

public class HomeActivity extends AppCompatActivity {
    static final int WORKING = 1;
    static final int STOPPED = 0;

    static int STATE = STOPPED;

    DataMiner dataMiner;
    public static TextView counterView;
    public static TextView totalCounterView;

    public static String username;

    public static String start_timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button button = (Button) findViewById(R.id.startButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (STATE == STOPPED) {
//                    counterView.setText("0");

                    DataMiner.isAlive = true;
                    STATE = WORKING;

                    dataMiner = new DataMiner(MainActivity.BLUETOOTH_SOCKET);
                    dataMiner.start();

                    button.setText("STOP");

                    HomeActivity.start_timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

                } else {
                    DataMiner.isAlive = false;
                    STATE = STOPPED;
                    button.setText("START");

                    Training training = new Training(HomeActivity.start_timestamp, username, Integer.valueOf(counterView.getText().toString()), Integer.valueOf(counterView.getText().toString()), 1);

                    TrainingCall trainingCall = new TrainingCall();

                    try {
                        String e = trainingCall.execute(training.toString()).get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    totalCounterView.setText(String.valueOf(Integer.parseInt(totalCounterView.getText().toString()) + Integer.parseInt(counterView.getText().toString())));

                    Weight weight = new Weight(HomeActivity.username);

                    WeightCall weightCall = new WeightCall();

                    try {
                        int w = Integer.parseInt(weightCall.execute(weight.toString()).get());
                        String stop_timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

                        double time = (double) (Long.parseLong(stop_timestamp) - Long.parseLong(start_timestamp)) / 1000;

                        double calories = round(0.0175 / 60 * time * 8 * w, 2);

                        Toast.makeText(HomeActivity.this, "Calorii arse: " + String.valueOf(calories), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        counterView = findViewById(R.id.textView);
        totalCounterView = findViewById(R.id.textView3);

        TotalCountCall totalCountCall = new TotalCountCall();

        try {
            String e = totalCountCall.execute(new TotalCount(username).toString()).get();
            totalCounterView.setText(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
