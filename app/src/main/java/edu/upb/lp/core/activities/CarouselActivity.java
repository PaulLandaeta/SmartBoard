package edu.upb.lp.core.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import edu.upb.lp.genericgame.R;
import edu.upb.lp.core.adapter.CarouselAdapter;
import edu.upb.lp.core.model.ScreenData;

public class CarouselActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        ViewPager2 viewPager = findViewById(R.id.viewPager);

        List<ScreenData> screens = new ArrayList<>();
        screens.add(new ScreenData(
                "Travel Your Way",
                "Travel the world by air and rail with Trip.com!",
                R.drawable.bugs_dead_bug,
                android.R.color.holo_blue_light,
                false,
                false
        ));
        screens.add(new ScreenData(
                "Stay Your Way",
                "With over 1.2 million hotels worldwide, you're sure to find the perfect accommodation!",
                R.drawable.bugs_happy_bug,
                android.R.color.holo_blue_dark,
                false,
                false
        ));
        screens.add(new ScreenData(
                "Discover Your Way",
                "Explore your destination with car rentals, attraction tickets, and things to do!",
                R.drawable.bugs_hungry_bug,
                android.R.color.holo_orange_light,
                false,
                false
        ));
        screens.add(new ScreenData(
                "Find Out What's Nearby",
                "Allowing the app access to your location makes searching for things nearby easier!",
                R.drawable.bugs_old_bug,
                android.R.color.holo_green_light,
                true,
                true
        ));


        CarouselAdapter adapter = new CarouselAdapter(this, screens);
        viewPager.setAdapter(adapter);
    }
}
