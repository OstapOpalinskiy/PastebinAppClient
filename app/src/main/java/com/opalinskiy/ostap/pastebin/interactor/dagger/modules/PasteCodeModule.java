package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.screens.paste_code_screen.IPasteCodeScreen;
import com.opalinskiy.ostap.pastebin.screens.paste_code_screen.presenter.PasteCodePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PasteCodeModule {
    IPasteCodeScreen.IView view;

    public PasteCodeModule(IPasteCodeScreen.IView view) {
        this.view = view;
    }

    @Provides
    IPasteCodeScreen.IPresenter providePasteCodePresenter(IDataInteractor model){
        return new PasteCodePresenter(model,view);
    }

}
