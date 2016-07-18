package com.opalinskiy.ostap.pastebin.screens.profile_screen.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.opalinskiy.ostap.pastebin.global.Constants;
import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.OnLoadFinishedListener;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.interactor.models.User;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.IProfileScreen;

import javax.inject.Inject;


public class ProfilePresenter implements IProfileScreen.IPresenter {
    private IMainScreen.IPresenter mainPresenter;
    private IProfileScreen.IProfileView view;
    private IDataInteractor model;
    private RequestParams parameters;
    private SharedPreferences prefs;

    @Inject
    public ProfilePresenter(IMainScreen.IPresenter mainPresenter, IProfileScreen.IProfileView view,
                            IDataInteractor model, RequestParams parameters, SharedPreferences prefs) {
        this.mainPresenter = mainPresenter;
        this.view = view;
        this.model = model;
        this.parameters = parameters;
        this.prefs = prefs;
    }

    @Override
    public void onLogout() {
        mainPresenter.onLogout();
    }


    private void loadUser(final SharedPreferences prefs) {
        view.startProgress("Please wait...", "User data is loading");
        model.getUser(parameters.getUserParams(), new OnLoadFinishedListener<User>() {
            @Override
            public void onSuccess(User user) {
                if (user != null) {
                    view.showUser(user);
                    SharedPreferences.Editor ed = prefs.edit();
                    ed.putBoolean(Constants.IS_REGISTERED_KEY, true);
                    ed.apply();
                    view.stopProgress();
                }
            }

            @Override
            public void onFailure(String msg) {
                view.stopProgress();
                view.showMessage(msg);
            }
        });
    }

    @Override
    public void loadData() {
        boolean isRegistered = prefs.getBoolean(Constants.IS_REGISTERED_KEY, false);
        Log.d(Constants.TAG, "is Registered:  in profile:" + isRegistered);
        if (isRegistered) {
            loadUser(prefs);
        } else {
            view.showGuest();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
