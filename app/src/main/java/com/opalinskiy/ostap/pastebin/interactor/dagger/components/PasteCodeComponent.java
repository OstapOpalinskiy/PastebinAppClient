package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.DataModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PasteCodeModule;
import com.opalinskiy.ostap.pastebin.screens.paste_code_screen.view.PasteCodeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {DataModule.class, PasteCodeModule.class} )
public interface PasteCodeComponent {
    void inject(PasteCodeFragment pasteCodeFragment);
}
