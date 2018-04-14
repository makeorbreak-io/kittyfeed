package zindeiros.kittyfeed;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import zindeiros.kittyfeed.feed.Feed;

public class FeedMeNowActivity extends AppCompatActivity {
    /**
     * Constant that represents the delay time (MS) between the changes of the kitty mood
     */
    private static final long KITTY_MOOD_DELAY_TIME = 3000l;
    /**
     * ImageView with the Happy Kitty representation
     */
    private ImageView happyKittyImageView;
    /**
     * ImageView with the Sad Kitty representation
     */
    private ImageView sadKittyImageView;
    /**
     * TextView with the Feed Me Now representation
     */
    private TextView feedMeNowTextView;
    /**
     * TextView with the Feed Me Later representation
     */
    private TextView feedMeLaterTextView;

    /**
     * ImageButton that controls the FeedMeNow functionality
     */
    private ImageButton feedMeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedmenow);
        configureImageViews();
        configureTextViews();
        configureFeedMeButton();
    }

    /**
     * Configures current Activity ImageView's
     */
    private void configureImageViews() {
        happyKittyImageView = (ImageView) findViewById(R.id.happyKitty);
        sadKittyImageView = (ImageView) findViewById(R.id.sadKitty);
    }

    /**
     * Configures current Activity TextView's
     */
    private void configureTextViews() {
        feedMeNowTextView = (TextView) findViewById(R.id.feed_me_now);
        feedMeLaterTextView = (TextView) findViewById(R.id.feed_me_later);
    }

    /**
     * Configures the FeedMeNow button
     */
    private void configureFeedMeButton() {
        feedMeButton = (ImageButton) findViewById(R.id.imageButton);
        feedMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (happyKittyImageView.getVisibility() != View.VISIBLE &&
                        feedMeLaterTextView.getVisibility() != View.VISIBLE) {
                    Feed.feedMeNow(FeedMeNowActivity.this.getApplicationContext());
                    changeKittyMood();
                    changeKittyHungerStatus();
                }
            }
        });
    }

    /**
     * Changes current Kitty Mood
     */
    private void changeKittyMood() {
        changeToHappyKitty();
        happyKittyImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeToSadKitty();
            }
        }, KITTY_MOOD_DELAY_TIME);
    }

    /**
     * Changes the current hunger status of the Kitty
     */
    private void changeKittyHungerStatus() {
        changeToFeedMeLater();
        feedMeLaterTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeToFeedMeNow();
            }
        }, KITTY_MOOD_DELAY_TIME);
    }


    /**
     * Changes current Kitty mood to happy
     */
    private void changeToHappyKitty() {
        sadKittyImageView.setVisibility(View.INVISIBLE);
        happyKittyImageView.setVisibility(View.VISIBLE);
    }

    /**
     * Changes current Kitty mood to sad
     */
    private void changeToSadKitty() {
        happyKittyImageView.setVisibility(View.INVISIBLE);
        sadKittyImageView.setVisibility(View.VISIBLE);
    }

    /**
     * Changes current Kitty status to feed me now
     */
    private void changeToFeedMeNow() {
        feedMeNowTextView.setVisibility(View.VISIBLE);
        feedMeLaterTextView.setVisibility(View.INVISIBLE);
    }

    /**
     * Changes current Kitty status to feed me later
     */
    private void changeToFeedMeLater() {
        feedMeLaterTextView.setVisibility(View.VISIBLE);
        feedMeNowTextView.setVisibility(View.INVISIBLE);
    }
}
