package de.jw.mymensa.mensa.ui.view.mensa.intro;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.NavigationPolicy;
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.heinrichreimersoftware.materialintro.slide.Slide;

import de.jw.mymensa.R;


public class MainIntroActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setFullscreen(true);
        //No title bar is set for the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Full screen is set for the Window
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setSkipEnabled(false);
        setFinishEnabled(true);

        addSlide(new SimpleSlide.Builder()
                .title(R.string.welcome_slide_title)
                .description(R.string.welcome_slide_text)
                .image(R.drawable.mensa_intro)
                .background(R.color.color_material_metaphor)
                .backgroundDark(R.color.color_dark_material_metaphor)
                .scrollable(false)
                .build());

        final Slide loginSlide;
        loginSlide = new FragmentSlide.Builder()
                .background(R.color.color_dark_permissions)
                .backgroundDark(R.color.color_material_metaphor)
                .fragment(LoginFragment.newInstance())
                .build();
        addSlide(loginSlide);

        //Feel free to add a navigation policy to define when users can go forward/backward
        /*setNavigationPolicy(new NavigationPolicy() {
            @Override
            public boolean canGoForward(int position) {
                return false;
            }
            @Override
            public boolean canGoBackward(int position) {
                return true;
            }
        });*/



        addOnNavigationBlockedListener(new OnNavigationBlockedListener() {
            @Override
            public void onNavigationBlocked(int position, int direction) {
                View contentView = findViewById(android.R.id.content);
                Slide slide = getSlide(position);

            }
        });

        //Feel free to add and remove page change listeners
        /*
        addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        */
    }

    @Override
    public void onBackPressed() {
        return;
    }

}

