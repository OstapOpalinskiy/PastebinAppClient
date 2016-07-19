package com.opalinskiy.ostap.pastebin.interactor.dagger.components;


import com.opalinskiy.ostap.pastebin.interactor.dagger.PerFragment;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ProfileModule;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.view.ProfileFragment;

import dagger.Component;

@PerFragment
@Component(modules = {MainPresenterModule.class, ProfileModule.class}, dependencies = AppComponent.class)
public interface ProfileComponent {
    void inject(ProfileFragment profileFragment);
}
