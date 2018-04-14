package zindeiros.kittyfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import zindeiros.kittyfeed.feed.Feed;

public class MainActivity extends AppCompatActivity {
    /**
     * ImageButton that represents the Feed Me Now! functionality
     */
    private ImageButton imageButtonFeedMeNow;
    /**
     * ImageButton that represents the Feed Me Later functionality
     */
    private ImageButton imageButtonFeedMeLater;
    /*
     * Instantiates MainActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureFeedMeNowImageButton();
        configureFeedMeLaterImageButton();
    }

    /**
     * Configures the Feed Me Now image button
     */
    private void configureFeedMeNowImageButton(){
        imageButtonFeedMeNow=findViewById(R.id.btnFeedMeNow);
        addChangeActivityOnClick(imageButtonFeedMeNow,FeedMeNowActivity.class);
    }

    /**
     * Configures the Feed Me Later image button
     */
    private void configureFeedMeLaterImageButton(){
        imageButtonFeedMeLater=findViewById(R.id.btnFeedMeLater);
        addChangeActivityOnClick(imageButtonFeedMeLater,FeedMeLaterActivity.class);
    }

    /**
     * Adds a change activity listener on a certain ImageButton
     * @param button ImageButton with the image button that is going to be added the change listener
     * @param activityClass Class with the activity class that is going to be changed
     */
    private void addChangeActivityOnClick(ImageButton button, final Class activityClass){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,activityClass));
            }
        });
    }
}