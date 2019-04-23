package com.example.bilal.androidproject;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {
    public MyPageAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return new Frag1();
            case 1:
                return new Frag2();
            case 2:
                return new Frag3();

//                return PlaceholderFragment.newInstance(R.layout.fragment_app1);
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
