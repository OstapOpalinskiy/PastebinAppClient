package com.opalinskiy.ostap.pastebin.screens.new_paste_screen.presenter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.opalinskiy.ostap.pastebin.global.Constants;
import com.opalinskiy.ostap.pastebin.interactor.IDataInteractor;
import com.opalinskiy.ostap.pastebin.interactor.OnLoadFinishedListener;
import com.opalinskiy.ostap.pastebin.interactor.RequestParams;
import com.opalinskiy.ostap.pastebin.interactor.models.PasteCodeParams;
import com.opalinskiy.ostap.pastebin.screens.new_paste_screen.INewPaste;

import javax.inject.Inject;


public class NewPastePresenter implements INewPaste.IPresenter {
    private INewPaste.IView view;
    @Inject
    IDataInteractor model;
    @Inject
    RequestParams parameters;

    public NewPastePresenter(INewPaste.IView view) {
        this.view = view;
       // Application.getComponent().inject(this);
    }

    @Override
    public void restoreState(Bundle bundle) {
        if (bundle != null) {
            boolean isLinkShown = bundle.getBoolean(Constants.IS_LINK_SHOWN_KEY);
            if (isLinkShown) {
                view.showLink(bundle.getString(Constants.LINK_KEY));
            }
        }
    }

    @Override
    public void onPostPaste(PasteCodeParams params) {
        if (!TextUtils.isEmpty(params.getPasteCode())) {
            view.startProgress("Please wait...", "Paste is sending to the server.");

            model.postPaste(parameters.getPasteCodeParams(params), new OnLoadFinishedListener<String>() {
                @Override
                public void onSuccess(String msg) {
                    view.showLink(msg);
                    view.stopProgress();
                }

                @Override
                public void onFailure(String msg) {
                    view.showMessage(msg);
                    view.stopProgress();
                }
            });
        } else {
            view.showMessage();
        }
    }

    @Override
    public void onClearLink() {
        view.clearLink();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void openLink(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
