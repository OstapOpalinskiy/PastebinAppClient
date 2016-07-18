package com.opalinskiy.ostap.pastebin.screens.login_screen.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.opalinskiy.ostap.pastebin.R;
import com.opalinskiy.ostap.pastebin.interactor.ConnectProvider;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.DaggerLoginComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.LoginComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.DataModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.LoginPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ParamsModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.PrefsModule;
import com.opalinskiy.ostap.pastebin.screens.base.BaseFragment;
import com.opalinskiy.ostap.pastebin.screens.login_screen.ILoginScreen;
import com.opalinskiy.ostap.pastebin.screens.login_screen.presenter.LoginPresenter;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;
import com.opalinskiy.ostap.pastebin.utils.ConverterUtils;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment implements ILoginScreen.ILoginView {
    private EditText etLogin;
    private EditText etPassword;
    private TextView tvLogin;

    @Inject
    LoginPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        initViews(view);

        LoginComponent component = DaggerLoginComponent.builder()
                .loginPresenterModule(new LoginPresenterModule())
                .dataModule(new DataModule(ConnectProvider.getInstance().getRetrofit(), new ConverterUtils()))
                .prefsModule(new PrefsModule(getActivity()))
                .paramsModule(new ParamsModule())
                .mainPresenterModule(new MainPresenterModule((IMainScreen.IView) getActivity()))
                .build();
        component.inject(this);


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = String.valueOf(etLogin.getText());
                String password = String.valueOf(etPassword.getText());
                presenter.onLogin(login, password);
            }
        });
        return view;
    }

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initViews(View view) {
        etLogin = (EditText) view.findViewById(R.id.et_login_FL);
        etPassword = (EditText) view.findViewById(R.id.et_password_FL);
        tvLogin = (TextView) view.findViewById(R.id.tv_login_FL);
    }

    @Override
    public void onResume() {
        setTitle(getResources().getString(R.string.login));
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopProgress();
    }
}
