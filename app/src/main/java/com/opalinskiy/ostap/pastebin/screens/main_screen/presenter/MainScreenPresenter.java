package com.opalinskiy.ostap.pastebin.screens.main_screen.presenter;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.opalinskiy.ostap.pastebin.Application;
import com.opalinskiy.ostap.pastebin.global.Constants;
import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.OnLoadFinishedListener;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.interactor.models.User;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;

import javax.inject.Inject;

public class MainScreenPresenter implements IMainScreen.IPresenter {
    private IMainScreen.IView view;

    @Inject
    IDataInteractor model;
    @Inject
    RequestParams parameters;
    @Inject
    SharedPreferences prefs;

    public MainScreenPresenter(final IMainScreen.IView view) {
        this.view = view;
        Application.getComponent().inject(this);

    }

    @Override
    public void loadUser(final boolean userChanged) {
        model.getUser(parameters.getUserParams(), new OnLoadFinishedListener<User>() {
            @Override
            public void onSuccess(User user) {
                Log.d(Constants.TAG, "loadUser() onSuccess():  " + user);
                if (user != null) {
                    setUserInfo(user);
                }
                if (userChanged) {
                    view.setProfileScreen();
                }
                prefs.edit()
                        .putBoolean(Constants.IS_REGISTERED_KEY, true)
                        .apply();
            }

            @Override
            public void onFailure(String msg) {
                view.showMessage(msg);
            }
        });
    }


    @Override
    public void loadUserKey(final boolean userChanged) {
        model.getUserKey(parameters.getUserKeyParams(), new OnLoadFinishedListener<String>() {
            @Override
            public void onSuccess(String userKey) {
                if (userKey.equals(Constants.WRONG_PASSWORD_RESPONSE)) {
                    view.showMessage(userKey);
                } else {
                    prefs.edit().putString(Constants.USER_KEY_TAG, userKey)
                            .apply();
                    loadUser(userChanged);
                }
            }

            @Override
            public void onFailure(String msg) {
                view.showMessage(msg);
            }
        });
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void setUserInfo(User user) {
        view.setAvatar(user.getUserAvatarUrl());
        view.setUserName(user.getUserName());
    }

    @Override
    public void setData() {
        if (prefs.getBoolean(Constants.IS_REGISTERED_KEY, false)) {
            loadUser(false);
        } else {
            view.setGuest();
        }
    }

    @Override
    public void onLogout() {
        Log.d(Constants.TAG, "onLogout");
        view.setLoginScreen();
        prefs.edit().putBoolean(Constants.IS_REGISTERED_KEY, false)
                .apply();

    }

    @Override
    public void onLogin(String login, String password) {
        if (!(TextUtils.isEmpty(login) && TextUtils.isEmpty(password))) {
            SharedPreferences.Editor ed = prefs.edit();
            ed.putString(Constants.LOGIN_KEY, login);
            ed.putString(Constants.PASSWORD_KEY, password);
            ed.putBoolean(Constants.IS_REGISTERED_KEY, true);
            ed.apply();
        }

        loadUserKey(true);
    }
}
