package zindeiros.kittyfeed;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import zindeiros.kittyfeed.feed.Feed;

public class FeedMeLaterActivity extends AppCompatActivity {

    /**
     * ImageButton that confirms the changes
     */
    private ImageButton okButton;

    /**
     * EditText that allows the user to insert the beginning hour
     */
    private EditText timeTxtField;

    /**
     * Spinner that contains all the timing options for feeding the kitty
     */
    private Spinner mySpinner;

    /**
     * Timing splitter (HH:MM)
     */
    private static final String SPLITTER = ":";

    /**
     * Maximum hours in a day
     */
    private static final int MAX_HOURS = 24;

    /**
     * Maximum minutes in an hour
     */
    private static final int MAX_MINS = 59;

    /**
     * Minimum hours and minutes
     */
    private static final int ZERO_TIME = 0;

    /**
     * Length of the array to split (timing array)
     */
    private static final int ARRAY_LENGTH = 2;

    /**
     * One time only option
     */
    private static final String ONE_TIME = "Just this time";

    /**
     * Eight in eight hours option
     */
    private static final String EIGHT_TIMES = "8 in 8 hours";

    /**
     * Four in four hours option
     */
    private static final String FOUR_TIMES = "4 in 4 hours";

    /**
     * Twelve in twelve hours option
     */
    private static final String TWELVE_TIMES = "12 in 12 hours";

    @Override
    /**
     * Instantiates FeedMeLaterActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_me_later);
        configureOkButton();
    }

    /**
     * Configures the OK button
     */
    private void configureOkButton() {
        okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateTimeTextField()) {
                    configureMySpinner();

                    AlertDialog dialog = showPopUpDialog("Success!", "Your cat will be fed " +
                            "starting at " + timeTxtField.getText() + ".");

                    dialog.show();

                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            startActivity(new Intent(FeedMeLaterActivity.this, MainActivity.class));
                        }
                    });
                } else {
                    showPopUpDialog("Invalid time!", "The time you inserted " + timeTxtField.getText()
                            + " is not valid. Please insert a valid one to proceed.").show();
                }
            }
        });
    }

    /**
     * Displays a pop up dialog with a certain title and message
     *
     * @param title Title of the alert dialog
     * @param msg   Message to display
     */
    private AlertDialog showPopUpDialog(String title, String msg) {
        return new AlertDialog.Builder(FeedMeLaterActivity.this, R.style.Theme_AppCompat_Light_Dialog)
                .setTitle(title).setMessage(msg).setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
    }

    /**
     * Verifies the time text field
     */
    private boolean validateTimeTextField() {
        timeTxtField = findViewById(R.id.timeTxtField);
        String insertedTime = timeTxtField.getText().toString();
        if (!insertedTime.contains(":")) return false;
        String[] splitted = insertedTime.split(SPLITTER);
        if (splitted.length != ARRAY_LENGTH) return false;
        int hours = Integer.parseInt(splitted[0]);
        if (hours < ZERO_TIME || hours >= MAX_HOURS) return false;
        int minutes = Integer.parseInt(splitted[1]);
        if (minutes < ZERO_TIME || minutes > MAX_MINS) return false;
        return true;
    }

    /**
     * Configures the spinner with the timing options
     */
    private void configureMySpinner() {
        mySpinner = findViewById(R.id.spinner);
        String option = mySpinner.getSelectedItem().toString();
        int numberOfTimes = 0;

        if (option.equalsIgnoreCase(ONE_TIME)) {
            numberOfTimes = 24;
        } else if (option.equalsIgnoreCase(FOUR_TIMES)) {
            numberOfTimes = 4;
        } else if (option.equalsIgnoreCase(EIGHT_TIMES)) {
            numberOfTimes = 8;
        } else if (option.equalsIgnoreCase(TWELVE_TIMES)) {
            numberOfTimes = 12;
        }

        Feed.feedMeLater(FeedMeLaterActivity.this.getApplicationContext()
                , buildJSONArray(numberOfTimes, timeTxtField.getText().toString()));
    }

    /**
     * Builds the array with the feed timing
     *
     * @param numberOfTimes Number of times to feed the kitty
     * @param startHour     Start hour
     * @return String with the information for JSON
     */
    private String buildJSONArray(int numberOfTimes, String startHour) {
        StringBuilder json = new StringBuilder("{\n\t\"reset\" : \"true\",\n\t\"horas\" : [");
        int hours = Integer.parseInt(startHour.split(SPLITTER)[0]);
        int minutes = Integer.parseInt(startHour.split(SPLITTER)[1]);
        List<Integer> list = new ArrayList<>();
        boolean catched = false;
        while (!catched) {
            json.append("\"" + hours + ":" + minutes + "\" ,");
            hours += numberOfTimes;
            System.out.println(hours);
            if (hours > 23) catched = true;
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]\n}");
        System.out.println(json.toString());
        return json.toString();
    }
}
