package de.jw.mymensa.mensa.ui.view.mensa.intro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.heinrichreimersoftware.materialintro.app.SlideFragment;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import de.jw.mymensa.R;
import rx.Observable;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.functions.Func3;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by JW on 14.05.2016.
 */

public class LoginFragment extends SlideFragment {

    private EditText fakeUsername;

    private EditText fakePassword;

    public LoginFragment() { }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        fakeUsername = (EditText) root.findViewById(R.id.projekt);
        fakePassword = (EditText) root.findViewById(R.id.einrichtung);

        setupFields();

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean canGoForward() {
        if(StringUtils.isBlank(fakeUsername.getText()) | StringUtils.isBlank(fakePassword.getText())) {
            return false;
        }
        return true;
    }

    private void setupFields() {
        Observable.combineLatest(
                WidgetObservable.text(fakeUsername, false),//false - do not emit start value
                WidgetObservable.text(fakePassword, false),

                new Func2<OnTextChangeEvent, OnTextChangeEvent, String[]>() {
                    @Override
                    public String[] call(OnTextChangeEvent onTextChangeEvent, OnTextChangeEvent onTextChangeEvent2) {
                        CharSequence projekt = onTextChangeEvent.text();
                        CharSequence einrichtung = onTextChangeEvent2.text();
                        if (TextUtils.isEmpty(projekt) | TextUtils.isEmpty(einrichtung)) {
                            return new String[] {};
                        } else {
                            return new String[] {projekt.toString(), einrichtung.toString()};
                        }
                    }
                }
        ).subscribe(new Action1<String[]>() {
            @Override
            public void call(String[] result) {
                System.out.println(result[0]);
                System.out.println(result[1]);
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();//getActivity().getSharedPreferences("userdata", MODE_PRIVATE).edit();
                editor.putString("projekt", StringUtils.trim(result[0]));
                editor.putString("einrichtung", StringUtils.trim(result[1]));
                editor.apply();
            }
        });
    }
}
