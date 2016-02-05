package com.river.ilikeit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.river.ilikeit.chat.ChatServiceManager;
import com.river.ilikeit.main.MainSectionsPagerAdapter;
import com.river.ilikeit.main.contact.ContactsFragment;

public class MainActivity extends AppCompatActivity implements ContactsFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    MainSectionsPagerAdapter mMainSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mMainSectionsPagerAdapter = new MainSectionsPagerAdapter(getSupportFragmentManager(), this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mMainSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                testSendMSG();
                break;
            case R.id.action_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContactFragmentInteraction(Uri uri) {
    }

    private void logout() {
        try {
            ChatServiceManager.getInstance(this).getRemoteService().logout();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Intent intentLogin = new Intent(this, SplashActivity.class);
        intentLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentLogin);
    }

    private void testSendMSG() {
        try {
//                String to = "1pn37r05x8ey508k3i9vh2tbsg@public.talk.google.com";
            String to = "hoanggiang262@gmail.com";
            ChatServiceManager.getInstance(this).getRemoteService().sendMessage(to, "Test 123");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
