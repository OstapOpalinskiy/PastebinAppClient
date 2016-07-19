package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.screens.main_screen.presenter.MainScreenPresenter;
import com.opalinskiy.ostap.pastebin.screens.main_screen.view.NavigationDrawerActivity;
import com.opalinskiy.ostap.pastebin.screens.my_pastes_screen.presenter.MyPastesPresenter;
import com.opalinskiy.ostap.pastebin.screens.new_paste_screen.presenter.NewPastePresenter;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.presenter.ProfilePresenter;

import javax.inject.Singleton;

import dagger.Component;

@PerFragment
@Component(modules = MainPresenterModule.class, dependencies = AppComponent.class)
public interface MainPresenterComponent {
    void inject(MainScreenPresenter mainScreenPresenter);
    void inject(ProfilePresenter profilePresenter);
    void inject(MyPastesPresenter myPastesPresenter);
    void inject(NewPastePresenter newPastePresenter);
    void inject(NavigationDrawerActivity activity);
}