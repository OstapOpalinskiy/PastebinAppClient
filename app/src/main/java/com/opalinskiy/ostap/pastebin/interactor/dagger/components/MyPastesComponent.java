package com.opalinskiy.ostap.pastebin.interactor.dagger.components;


import com.opalinskiy.ostap.pastebin.interactor.dagger.PerFragment;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MyPastesModule;
import com.opalinskiy.ostap.pastebin.screens.my_pastes_screen.view.MyPastesFragment;

import dagger.Component;

@PerFragment
@Component(modules = MyPastesModule.class, dependencies = AppComponent.class)
public interface MyPastesComponent {
    void inject(MyPastesFragment myPastesFragment);
}
