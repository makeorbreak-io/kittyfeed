package zindeiros.kittyfeed;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import zindeiros.kittyfeed.feed.Feed;

public class MainActivity extends AppCompatActivity {

    /**
     * FeedMeNow Button Item
     */
    NavigationMenuItemView feedMeNowItem;

    /**
     * FeedMeLater Button Item
     */
    NavigationMenuItemView feedMeLaterItem;

    @Override
    /**
     * Instantiates MainActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //     startActivity(new Intent(MainActivity.this,FeedMeNowActivity.class));
        configureFeedMeItems();
        configureFeedMeNowOption();
        configureFeedMeLaterOption();
    }

    /**
     * Configures FeedMeNow MenuItems
     */
    private void configureFeedMeItems() {
        feedMeNowItem = (NavigationMenuItemView) findViewById(R.id.feed_me_now_item);
        feedMeLaterItem = (NavigationMenuItemView) findViewById(R.id.feed_me_later_item);
    }

    /**
     * Configures the FeedMeNow menu option
     */
    private void configureFeedMeNowOption() {
        feedMeNowItem.
        startActivity(new Intent(MainActivity.this, FeedMeNowActivity.class));
    }


    /**
     * Configures the FeedMeLater menu option
     */
    private void configureFeedMeLaterOption() {
        startActivity(new Intent(MainActivity.this, FeedMeLaterActivity.class));

    }
}
