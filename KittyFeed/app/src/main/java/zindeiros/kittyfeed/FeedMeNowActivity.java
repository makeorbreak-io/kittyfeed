package zindeiros.kittyfeed;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class FeedMeNowActivity extends AppCompatActivity {
    /**
     * Constant that represents the delay time (MS) between the changes of the kitty mood
     */
    private static final long KITTY_MOOD_DELAY_TIME=3000l;
    /**
     * ImageView with the Happy Kitty representation
     */
    private ImageView happyKittyImageView;
    /**
     * ImageView with the Sad Kitty representation
     */
    private ImageView sadKittyImageView;
    /**
     * ImageButton that controls the FeedMeNow functionality
     */
    private ImageButton feedMeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedmenow);
        configureImageViews();
        configureFeedMeButton();
    }

    /**
     * Configures current Activity ImageView's
     */
    private void configureImageViews(){
        happyKittyImageView=(ImageView)findViewById(R.id.happyKitty);
        sadKittyImageView=(ImageView)findViewById(R.id.sadKitty);
    }

    /**
     * Configures the FeedMeNow button
     */
    private void configureFeedMeButton(){
        feedMeButton=(ImageButton)findViewById(R.id.imageButton);
        feedMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(happyKittyImageView.getVisibility()!=View.VISIBLE){
                    changeKittyMood();
                }
            }
        });
    }

    /**
     * Changes current Kitty Mood
     */
    private void changeKittyMood(){
        changeToHappyKitty();
        happyKittyImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeToSadKitty();
                System.out.println("ok");
            }
        }, KITTY_MOOD_DELAY_TIME);
    }

    /**
     * Changes current Kitty mood to happy
     */
    private void changeToHappyKitty(){
        sadKittyImageView.setVisibility(View.INVISIBLE);
        happyKittyImageView.setVisibility(View.VISIBLE);
    }

    /**
     * Changes current Kitty mood to sad
     */
    private void changeToSadKitty(){
        happyKittyImageView.setVisibility(View.INVISIBLE);
        sadKittyImageView.setVisibility(View.VISIBLE);
    }
}
