package com.opalinskiy.ostap.pastebin.interactor.dagger.components;

import com.opalinskiy.ostap.pastebin.interactor.dagger.PerFragment;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PasteCodeModule;
import com.opalinskiy.ostap.pastebin.screens.paste_code_screen.view.PasteCodeFragment;

import dagger.Component;

@PerFragment
@Component (modules = PasteCodeModule.class, dependencies = AppComponent.class )
public interface PasteCodeComponent {
    void inject(PasteCodeFragment pasteCodeFragment);
}
