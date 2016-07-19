package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.PerFragment;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.NewPasteModule;
import com.opalinskiy.ostap.pastebin.screens.new_paste_screen.view.NewPasteFragment;

import dagger.Component;

@PerFragment
@Component(modules = NewPasteModule.class, dependencies = AppComponent.class)
public interface NewPasteComponent {
void inject(NewPasteFragment newPasteFragment);
}
