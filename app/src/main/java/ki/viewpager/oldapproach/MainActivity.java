package ki.viewpager.oldapproach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ki.viewpager.oldapproach.fragment.BigPictureFragment;
import ki.viewpager.oldapproach.fragment.SmallPictureFragment;
import ki.viewpager.oldapproach.fragment.fakeroot.RootForBigPictureFragment;
import ki.viewpager.oldapproach.fragment.fakeroot.RootForSmallPictureFragment;
import ki.viewpageroldapproach.R;

public class MainActivity extends AppCompatActivity {

    public static final int PAGE_SMALL_CAT = 0;
    public static final int PAGE_BIG_CAT = 1;
    public static ViewPager viewPager;

    public final SmallPictureFragment small_picture_fragment = new SmallPictureFragment();
    public final BigPictureFragment big_picture_fragment = new BigPictureFragment();
    public final RootForSmallPictureFragment root_small_pic_fragment = new RootForSmallPictureFragment();
    public final RootForBigPictureFragment root_big_pic_fragment = new RootForBigPictureFragment();
    public final SmallPictureFragment small_picture_fragment_fake = new SmallPictureFragment();
    public final BigPictureFragment big_picture_fragment_fake = new BigPictureFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewPager = findViewById(R.id.main_viewPager);

        setupViewPager(this.viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        TestViewPager adapter = new TestViewPager(getSupportFragmentManager());

        adapter.addFragment(PAGE_SMALL_CAT, root_small_pic_fragment);
        adapter.addFragment(PAGE_BIG_CAT, root_big_pic_fragment);

        viewPager.setAdapter(adapter);
    }


    public class TestViewPager extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();

        public TestViewPager(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        public void addFragment(int index, Fragment fragment) {
            fragments.add(index, fragment);
        }

    }

}
