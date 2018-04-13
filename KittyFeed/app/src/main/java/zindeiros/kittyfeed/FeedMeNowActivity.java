package zindeiros.kittyfeed;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FeedMeNowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedmenow);
        configureFeedMeButton();
    }
    private void configureFeedMeButton(){
        ImageButton feedMeButton=findViewById(R.id.imageButton);
        feedMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSadKittyImageView().setVisibility(View.INVISIBLE);
                getHappyKittyImageView().setVisibility(View.VISIBLE);
            }
        });
    }
    private ImageView getSadKittyImageView(){
        ImageView sadKittyImageView=(ImageView)findViewById(R.id.sadKitty);
        return sadKittyImageView;
    }
    private ImageView getHappyKittyImageView(){
        ImageView happyKittyImageView=(ImageView)findViewById(R.id.happyKitty);
        return happyKittyImageView;
    }
}
