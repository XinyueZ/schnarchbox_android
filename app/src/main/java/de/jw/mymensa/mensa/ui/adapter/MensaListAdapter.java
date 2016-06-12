package de.jw.mymensa.mensa.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.pwittchen.weathericonview.WeatherIconView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import de.jw.mymensa.R;
import de.jw.mymensa.mensa.data.entity.Untis;
import de.jw.mymensa.mensa.data.entity.Weather;
import de.jw.mymensa.mensa.ui.view.mensa.MensaActivity;
import de.jw.mymensa.mensa.ui.view.mensa.intro.MainIntroActivity;

/**
 * Created by JW on 19.05.2016.
 */

public class MensaListAdapter extends RecyclerView.Adapter<MensaListAdapter.MensaViewHolder> {
    public List<Object> mItems;

    @Inject
    public MensaListAdapter() {
        super();
        mItems = new ArrayList<Object>();
    }

    public void newAddData(Object advices) {
        mItems.add(advices);
        notifyItemInserted(mItems.size());
        //notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public int getWeatherIcon(Integer weathercode) {
        switch(weathercode) {
            case 0:
            case 1:
            case 2:
                return R.string.wi_tornado;
            case 3:
            case 4:
                return R.string.wi_thunderstorm;
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 18:
            case 46:
            case 13:
            case 14:
            case 15:
            case 16:
            case 41:
            case 43:
                return R.string.wi_day_snow;
            case 9:
            case 11:
            case 12:
            case 45:
                return R.string.wi_rain;
            case 17:
            case 35:
                return R.string.wi_hail;
            case 19:
            case 20:
            case 21:
            case 22:
                return R.string.wi_dust;
            case 23:
            case 24:
                return R.string.wi_windy;
            case 25:
                return R.string.wi_snowflake_cold;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 44:
                return R.string.wi_cloud;
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
                return R.string.wi_day_sunny;
            case 37:
            case 38:
            case 39:
            case 40:
            case 42:
            case 47:
                return R.string.wi_rain_mix;
        }
        return 0;
    }


    @Override
    public MensaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(mItems.get(viewType).getClass().equals(Untis.class) ? R.layout.view_untis_row : R.layout.view_mensa_row, viewGroup, false);
        MensaViewHolder viewHolder = new MensaViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MensaViewHolder viewHolder, int viewType) {
        Object advice =  mItems.get(viewType);
        if (advice.getClass().equals(Untis.class)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            if (calendar.DAY_OF_WEEK - 1 == 6 | calendar.DAY_OF_WEEK - 1 == 7) {
                viewHolder.title.setText("It's " + new SimpleDateFormat("EEEE").format(new Date()) + "! You can keep on sleeping! :-)");
            } else {
                viewHolder.title.setText(((Untis) advice).getAdvices().getCansleeplonger().equals(false) ? "It's " + new SimpleDateFormat("EEEE").format(new Date()) + "! You can keep on sleeping! :-)" : "You gotta get up now :(");
                if (((Untis) advice).getAdvices().getCansleeplonger().equals(0.45)) {
                    viewHolder.title.setText("First lesson cancelled! Should I wake you up 45 minutes later instead?");
                } else if (((Untis) advice).getAdvices().getCansleeplonger().equals(1.35)) {
                    viewHolder.title.setText("First two lessons cancelled! Should I wake you up 1.5 hours later instead?");
                } else if(((Untis) advice).getAdvices().getCansleeplonger().getClass().equals(String.class)) {
                    viewHolder.title.setText(((Untis) advice).getAdvices().getCansleeplonger().toString());
                }
            }
        } else if (advice.getClass().equals(Weather.class)) {
            viewHolder.weatherIconView.setIconResource(MensaActivity.getContext().getResources().getString(getWeatherIcon(Integer.valueOf(((Weather) advice).getCode()))));
            viewHolder.title.setText(((Weather) advice).getAdvicetext());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MensaViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public WeatherIconView weatherIconView;

        public MensaViewHolder(View itemView) {
            super(itemView);
            //ButterKnife.bind(this, itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            try {
                weatherIconView = (WeatherIconView) itemView.findViewById(R.id.my_weather_icon);
            } catch (Exception e) {

            }
        }
    }

}
