package com.opalinskiy.ostap.pastebin.interactor.dagger.components;


import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.DataModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MyPastesModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ParamsModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PrefsModule;
import com.opalinskiy.ostap.pastebin.screens.my_pastes_screen.view.MyPastesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PrefsModule.class, DataModule.class, ParamsModule.class,MyPastesModule.class})
public interface MyPastesComponent {
    void inject(MyPastesFragment myPastesFragment);
}
