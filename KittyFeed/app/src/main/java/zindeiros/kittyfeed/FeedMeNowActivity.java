package zindeiros.kittyfeed;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FeedMeNowActivity extends AppCompatActivity {

    @Override
    /**
     * Creates an instance of FeedMeNowActivity.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedmenow);
        configureFeedMeButton();
    }

    /**
     * Configures the action listener for the "feed me" button.
     */
    private void configureFeedMeButton() {
        ImageButton feedMeButton = findViewById(R.id.imageButton);
        feedMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeKittyMood();
            }
        });
    }

    /**
     * Gets the sad kitty image view.
     *
     * @return the sad kitty image view
     */
    private ImageView getSadKittyImageView() {
        ImageView sadKittyImageView = (ImageView) findViewById(R.id.sadKitty);
        return sadKittyImageView;
    }

    /**
     * Gets the happy kitty image view.
     *
     * @return the happy kitty image view
     */
    private ImageView getHappyKittyImageView() {
        ImageView happyKittyImageView = (ImageView) findViewById(R.id.happyKitty);
        return happyKittyImageView;
    }

    /**
     * Changes the mood of the kitty from sad to happy, sleeps n milliseconds and changes the mood
     * once again, from happy to sad.
     */
    private void changeKittyMood() {
        int n = 3000;
        getSadKittyImageView().setVisibility(View.INVISIBLE);
        getHappyKittyImageView().setVisibility(View.VISIBLE);
        try {
            Thread.sleep(n);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        getHappyKittyImageView().setVisibility(View.INVISIBLE);
        getSadKittyImageView().setVisibility(View.VISIBLE);
    }
}
