package com.opalinskiy.ostap.pastebin.screens.main_screen;

import android.content.SharedPreferences;

import com.opalinskiy.ostap.pastebin.interactor.models.User;


public interface IMainScreen {

    interface IPresenter {
        void loadUser(boolean userChanged);

        void loadUserKey(boolean userChanged);

        void onDestroy();

        void setUserInfo(User user);

        void setData();

        void onLogout();

        void onLogin(String login, String password);
    }

    interface IView {

        void setAvatar(String url);

        void setUserName(String name);

        void setLoginScreen();

        void setProfileScreen();

        void showMessage(String msg);

        void setGuest();

    }
}
