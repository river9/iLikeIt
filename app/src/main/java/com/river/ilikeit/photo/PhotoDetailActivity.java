package com.river.ilikeit.photo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.river.ilikeit.Constant;
import com.river.ilikeit.R;

public class PhotoDetailActivity extends AppCompatActivity {
    PhotoSectionsPagerAdapter mPhotoSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPhotoSectionsPagerAdapter = new PhotoSectionsPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPhotoSectionsPagerAdapter);

        if (savedInstanceState == null) {
            savedInstanceState = getIntent().getExtras();
        }
        mViewPager.setCurrentItem(savedInstanceState.getInt(Constant.ARG_SECTION_NUMBER));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
