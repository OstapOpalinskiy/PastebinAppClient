package com.opalinskiy.ostap.pastebin.screens.paste_code_screen.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.opalinskiy.ostap.pastebin.Application;
import com.opalinskiy.ostap.pastebin.R;
import com.opalinskiy.ostap.pastebin.global.Constants;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.DaggerPasteCodeComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.PasteCodeComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PasteCodeModule;
import com.opalinskiy.ostap.pastebin.screens.base.BaseFragment;
import com.opalinskiy.ostap.pastebin.screens.paste_code_screen.IPasteCodeScreen;

import javax.inject.Inject;

public class PasteCodeFragment extends BaseFragment implements IPasteCodeScreen.IView {
    private TextView tvCode;
    private String url;
    @Inject
    IPasteCodeScreen.IPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.paste_code_fragment, container, false);
        tvCode = (TextView) view.findViewById(R.id.tv_code_PCF);
        url = getArguments().getString(Constants.URL_KEY);
        int myOrTrending = getArguments().getInt(Constants.MY_OR_TRANDING_KEY);
        if (myOrTrending == Constants.MY_PASTES) {
            setHasOptionsMenu(true);
        }
       PasteCodeComponent component = DaggerPasteCodeComponent.builder()
                .appComponent(Application.getAppComponent())
                .pasteCodeModule(new PasteCodeModule(this))
                .build();
        component.inject(this);
        presenter.getCode(url);
        return view;
    }

    public static PasteCodeFragment newInstance(String url, int myOrTrending) {
        Bundle args = new Bundle();
        args.putString(Constants.URL_KEY, url);
        args.putInt(Constants.MY_OR_TRANDING_KEY, myOrTrending);
        PasteCodeFragment fragment = new PasteCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        setTitle(getResources().getString(R.string.paste_code));
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_my_pastes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences prefs = getActivity().getSharedPreferences(Constants.PREFS_NAME, 0);
        presenter.deletePaste(url, prefs);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCode(String code) {
        setTitle(getResources().getString(R.string.paste_code));
        tvCode.setText(code);
    }

    @Override
    public void onDeletePaste(String s) {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroyView();
    }

    @Override
    public void onStop() {
        stopProgress();
        super.onStop();
    }

}
