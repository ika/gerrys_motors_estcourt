package org.armstrong.ika.gerrys_motors_natal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;
/**
 * Created by ika on 10/20/16.
 */

public class LoadActivity extends ActionBarMenuActivity {

    private static LoadActivity instance;
    FragmentPagerAdapter adapterViewPager;
    //private Fragment frag;
    private int NUM_ITEMS = 7;

    public static Context getContext() {

        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        instance = this;

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        Bundle extras = getIntent().getExtras();
        String CASE = extras.getString("CASE");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(R.string.app_name);

        int C = Integer.parseInt(CASE);
        vpPager.setCurrentItem(C,false);

        MainActivity.backButtonCount = 0;

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {

            int C = position;

            String file = C + ".txt";
            String content = LoadAsset(file);
            String image = SectionImages.IMAGES[C];

            return LoadFragment.newInstance(C, content, image);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }


    public String LoadAsset(String inputFile) {

        String mAsset = null;

        try {
            InputStream stream = getAssets().open(inputFile);

            int size = stream.available();

            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();

            mAsset = new String(buffer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mAsset;

    }

}