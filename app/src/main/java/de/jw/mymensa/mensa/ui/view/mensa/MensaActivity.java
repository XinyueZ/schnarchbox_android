package de.jw.mymensa.mensa.ui.view.mensa;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import de.jw.mymensa.R;
import de.jw.mymensa.mensa.AndroidApplication;
import de.jw.mymensa.mensa.data.entity.AdviceModel;
import de.jw.mymensa.mensa.data.entity.Untis;
import de.jw.mymensa.mensa.ui.adapter.MensaListAdapter;
import de.jw.mymensa.mensa.ui.presenter.MensaPresenter;
import de.jw.mymensa.mensa.ui.view.mensa.intro.MainIntroActivity;
import de.jw.mymensa.mensa.ui.view.mensa.preferences.PreferencesActivity;
import de.jw.mymensa.mosby.BaseActivity;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import rx.functions.Action1;

public class MensaActivity
        extends BaseActivity<MensaView, MensaPresenter>
        implements MensaView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.contentView)
    SwipeRefreshLayout contentView;

    public static Context context;

    MensaComponent component;
    MensaListAdapter adapter;

    @Override
    protected void createComponent() {
        component = DaggerMensaComponent.builder()
                .applicationComponent(((AndroidApplication) getApplication()).getComponent())
                .build();
    }

    @Override
    public MensaPresenter createPresenter() {
        return component.presenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensa);
        setSupportActionBar(toolbar);

        context = getApplicationContext();

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", true);
        if ( firstRun ) {
            startActivityForResult(
                    new Intent(MensaActivity.this, MainIntroActivity.class), 2);

        }

        adapter = component.adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        // see http://stackoverflow.com/questions/26858692/swiperefreshlayout-setrefreshing-not-showing-indicator-initially
        int offset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        contentView.setProgressViewOffset(false, 0, offset);
        contentView.setOnRefreshListener(this);

        loadData(false);
    }


    @Override
    public void showRepos(AdviceModel advices) {
        if(adapter.getItemCount() > 0) {
            adapter.clear();
        }
        adapter.newAddData(advices.getUntis());
        adapter.newAddData(advices.getWeather());

    }

    @Override
    public void clearRecyclerView() {
        adapter.clear();
    }

    @Override
    public void hideLoadingIndicator() {
        contentView.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        loadData(true);
    }


    void loadData(boolean isRefresh) {
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        getPresenter().loadDishesList();
        getPresenter().clearRecyclerView();
        if (!isRefresh) {
            contentView.setRefreshing(true);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 2) {
            SharedPreferences settings = getSharedPreferences("prefs", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
            finish();
            startActivity(getIntent());
        }
    }


    public static Context getContext() {
        return context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mensa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.about:
                new LibsBuilder()
                        .withActivityStyle(Libs.ActivityStyle.LIGHT)
                        //start the activity
                        .start(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
