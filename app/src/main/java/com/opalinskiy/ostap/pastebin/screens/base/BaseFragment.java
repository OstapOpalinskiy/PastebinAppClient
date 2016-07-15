package com.opalinskiy.ostap.pastebin.screens.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.opalinskiy.ostap.pastebin.global.Constants;


public class BaseFragment extends Fragment implements IBaseFragment {
    private ProgressDialog ringProgress;


    @Override
    public void startProgress(String title, String msg) {
        ringProgress = ProgressDialog.show(getActivity(), title, msg);
        ringProgress.setCancelable(true);
    }

    @Override
    public void stopProgress() {
        if(ringProgress != null){
            ringProgress.cancel();
        }
    }


    @Override
    public void showMessage(String msg) {
        Log.d(Constants.TAG1, "SHOW MSG BASE" + getActivity());
        Toast.makeText(getActivity(), "11111111111111111", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setTitle(String title) {
        if(isAdded()){
            getActivity().setTitle(title);
        }
    }
}
