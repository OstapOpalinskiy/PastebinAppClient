package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.NewPasteModule;
import com.opalinskiy.ostap.pastebin.screens.new_paste_screen.view.NewPasteFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NewPasteModule.class})
public interface NewPasteComponent {
void inject(NewPasteFragment newPasteFragment);
}
