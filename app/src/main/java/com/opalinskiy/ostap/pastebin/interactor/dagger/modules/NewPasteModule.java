package com.opalinskiy.ostap.pastebin.interactor.dagger.modules;

import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.screens.new_paste_screen.INewPaste;
import com.opalinskiy.ostap.pastebin.screens.new_paste_screen.presenter.NewPastePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class NewPasteModule {
    INewPaste.IView view;

    public NewPasteModule(INewPaste.IView view) {
        this.view = view;
    }
    @Provides
    INewPaste.IPresenter provideNewPastePresenter(IDataInteractor model, RequestParams parameters){
        return new NewPastePresenter(view, model, parameters);
    }
}
