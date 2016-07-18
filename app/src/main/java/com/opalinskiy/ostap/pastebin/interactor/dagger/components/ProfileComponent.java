package com.opalinskiy.ostap.pastebin.interactor.dagger.components;


import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.DataModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ParamsModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PrefsModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ProfileModule;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.view.ProfileFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PrefsModule.class, DataModule.class, ParamsModule.class,
        MainPresenterModule.class, ProfileModule.class})
public interface ProfileComponent {
    void inject(ProfileFragment profileFragment);
}
