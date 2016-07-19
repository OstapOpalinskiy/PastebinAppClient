package com.opalinskiy.ostap.pastebin.interactor.dagger.components;


import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ProfileModule;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.view.ProfileFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,MainPresenterModule.class, ProfileModule.class})
public interface ProfileComponent {
    void inject(ProfileFragment profileFragment);
}
