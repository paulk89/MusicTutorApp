package lessons;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;


import tests.TestActivity;
import com.example.paulk.MusicTutorApp.R;

import java.util.List;
import java.util.Vector;

import fragments.Level1IntroFragment;
import fragments.Level1Lesson1Fragment;
import fragments.Level1Lesson2Fragment;
import fragments.Level1Lesson3Fragment;
import fragments.Level1Lesson4Fragment;
import fragments.Level1Lesson5Fragment;
import fragments.Level2IntroFragment;
import fragments.Level2Lesson1Fragment;
import fragments.Level2Lesson2Fragment;
import fragments.Level2Lesson3Fragment;
import fragments.Level2Lesson4Fragment;
import fragments.Level2Lesson5Fragment;
import fragments.Level3IntroFragment;
import fragments.Level3Lesson1Fragment;
import fragments.Level3Lesson2Fragment;
import fragments.Level3Lesson3Fragment;
import fragments.Level3Lesson4Fragment;
import fragments.Level3Lesson5Fragment;
import fragments.Level4IntroFragment;
import fragments.Level4Lesson1Fragment;
import fragments.Level4Lesson2Fragment;
import fragments.Level4Lesson3Fragment;
import fragments.Level4Lesson4Fragment;
import fragments.Level4Lesson5Fragment;
import fragments.Level5IntroFragment;
import fragments.Level5Lesson1Fragment;
import fragments.Level5Lesson2Fragment;
import fragments.Level5Lesson3Fragment;
import fragments.Level5Lesson4Fragment;
import fragments.Level5Lesson5Fragment;

/**
 * Created by paulk on 25/06/2016.
 */
public class LessonsActivity extends FragmentActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 6;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }*/
    private int buttonLevelClicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_screen_slide);
        buttonLevelClicked = getIntent().getExtras().getInt("buttonclick");

        //initialsie the view pager
        this.initialisePaging(buttonLevelClicked);
    }

    // load the approriate level of lessons depending on what level the user has selected
    private void initialisePaging(int buttonClicked) {

        switch (buttonClicked) {

            case 1:

                List<Fragment> level1_fragments = new Vector<Fragment>();
                level1_fragments.add(Fragment.instantiate(this, Level1IntroFragment.class.getName()));
                level1_fragments.add(Fragment.instantiate(this, Level1Lesson1Fragment.class.getName()));
                level1_fragments.add(Fragment.instantiate(this, Level1Lesson2Fragment.class.getName()));
                level1_fragments.add(Fragment.instantiate(this, Level1Lesson3Fragment.class.getName()));
                level1_fragments.add(Fragment.instantiate(this, Level1Lesson4Fragment.class.getName()));
                level1_fragments.add(Fragment.instantiate(this, Level1Lesson5Fragment.class.getName()));
                mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), level1_fragments);
                //
                mPager = (ViewPager) findViewById(R.id.pager);
                mPager.setAdapter(mPagerAdapter);

                break;

            case 2:

                List<Fragment> level2_fragments = new Vector<Fragment>();
                level2_fragments.add(Fragment.instantiate(this, Level2IntroFragment.class.getName()));
                level2_fragments.add(Fragment.instantiate(this, Level2Lesson1Fragment.class.getName()));
                level2_fragments.add(Fragment.instantiate(this, Level2Lesson2Fragment.class.getName()));
                level2_fragments.add(Fragment.instantiate(this, Level2Lesson3Fragment.class.getName()));
                level2_fragments.add(Fragment.instantiate(this, Level2Lesson4Fragment.class.getName()));
                level2_fragments.add(Fragment.instantiate(this, Level2Lesson5Fragment.class.getName()));
                mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), level2_fragments);
                //
                mPager = (ViewPager) findViewById(R.id.pager);
                mPager.setAdapter(mPagerAdapter);

                break;

            case 3:

                List<Fragment> level3_fragments = new Vector<Fragment>();
                level3_fragments.add(Fragment.instantiate(this, Level3IntroFragment.class.getName()));
                level3_fragments.add(Fragment.instantiate(this, Level3Lesson1Fragment.class.getName()));
                level3_fragments.add(Fragment.instantiate(this, Level3Lesson2Fragment.class.getName()));
                level3_fragments.add(Fragment.instantiate(this, Level3Lesson3Fragment.class.getName()));
                level3_fragments.add(Fragment.instantiate(this, Level3Lesson4Fragment.class.getName()));
                level3_fragments.add(Fragment.instantiate(this, Level3Lesson5Fragment.class.getName()));
                mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), level3_fragments);
                //
                mPager = (ViewPager) findViewById(R.id.pager);
                mPager.setAdapter(mPagerAdapter);

                break;

            case 4:

                List<Fragment> level4_fragments = new Vector<Fragment>();
                level4_fragments.add(Fragment.instantiate(this, Level4IntroFragment.class.getName()));
                level4_fragments.add(Fragment.instantiate(this, Level4Lesson1Fragment.class.getName()));
                level4_fragments.add(Fragment.instantiate(this, Level4Lesson2Fragment.class.getName()));
                level4_fragments.add(Fragment.instantiate(this, Level4Lesson3Fragment.class.getName()));
                level4_fragments.add(Fragment.instantiate(this, Level4Lesson4Fragment.class.getName()));
                level4_fragments.add(Fragment.instantiate(this, Level4Lesson5Fragment.class.getName()));
                mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), level4_fragments);
                //
                mPager = (ViewPager) findViewById(R.id.pager);
                mPager.setAdapter(mPagerAdapter);

                break;

            case 5:

                List<Fragment> level5_fragments = new Vector<Fragment>();
                level5_fragments.add(Fragment.instantiate(this, Level5IntroFragment.class.getName()));
                level5_fragments.add(Fragment.instantiate(this, Level5Lesson1Fragment.class.getName()));
                level5_fragments.add(Fragment.instantiate(this, Level5Lesson2Fragment.class.getName()));
                level5_fragments.add(Fragment.instantiate(this, Level5Lesson3Fragment.class.getName()));
                level5_fragments.add(Fragment.instantiate(this, Level5Lesson4Fragment.class.getName()));
                level5_fragments.add(Fragment.instantiate(this, Level5Lesson5Fragment.class.getName()));
                mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), level5_fragments);
                //
                mPager = (ViewPager) findViewById(R.id.pager);
                mPager.setAdapter(mPagerAdapter);

                break;

            }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void level1Test(View view){
        Intent i = new Intent(LessonsActivity.this,
                TestActivity.class);
        i.putExtra("level", buttonLevelClicked);
        startActivity(i);
        finish();
    }

    //launch test activity and pass the appropriate test level value so the appropriate level
    //of questions can be loaded form the database
    public void launchTest(View v){

                Intent i = new Intent(LessonsActivity.this,
                        TestActivity.class);
                i.putExtra("level", buttonLevelClicked);
                startActivity(i);
                finish();
    }

    //page adapter claass that handles the swiping of the fragments in the lessons
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
