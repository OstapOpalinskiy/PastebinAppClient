package com.opalinskiy.ostap.pastebin.screens.profile_screen.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.opalinskiy.ostap.pastebin.Application;
import com.opalinskiy.ostap.pastebin.R;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.DaggerProfileComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.ProfileComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MainPresenterModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.ProfileModule;
import com.opalinskiy.ostap.pastebin.interactor.models.User;
import com.opalinskiy.ostap.pastebin.screens.base.BaseFragment;
import com.opalinskiy.ostap.pastebin.screens.main_screen.IMainScreen;
import com.opalinskiy.ostap.pastebin.screens.profile_screen.IProfileScreen;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends BaseFragment implements IProfileScreen.IProfileView {
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvLocation;
    private TextView tvLogOut;
    private CircleImageView avatar;
    @Inject
    IProfileScreen.IPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

       ProfileComponent component = DaggerProfileComponent.builder()
                .appComponent(Application.getAppComponent())
                .profileModule(new ProfileModule(this))
                .mainPresenterModule(new MainPresenterModule((IMainScreen.IView) getActivity()))
                .build();
        component.inject(this);

        initViews(view);
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogout();
            }
        });
        return view;
    }


    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        presenter.loadData();
        setTitle(getResources().getString(R.string.profile));
        super.onResume();
    }


    @Override
    public void showUser(User user) {
        Picasso.
                with(getActivity()).
                load(user.getUserAvatarUrl()).
                into(avatar);
        tvName.setText(user.getUserName());
        tvEmail.setText(user.getUserEmail());
        tvLocation.setText(user.getUserLocation());

        tvEmail.setVisibility(View.VISIBLE);
        tvLocation.setVisibility(View.VISIBLE);

        tvLogOut.setText(R.string.logout);
    }

    @Override
    public void showGuest() {
        avatar.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.guest_green));
        tvName.setText(R.string.guest);

        tvEmail.setVisibility(View.INVISIBLE);
        tvLocation.setVisibility(View.INVISIBLE);

        tvLogOut.setText(R.string.login);
    }

    private void initViews(View view) {
        avatar = (CircleImageView) view.findViewById(R.id.iv_avatar_PF);
        tvName = (TextView) view.findViewById(R.id.tv_name_PF);
        tvEmail = (TextView) view.findViewById(R.id.tv_email_PF);
        tvLocation = (TextView) view.findViewById(R.id.tv_location_PF);
        tvLogOut = (TextView) view.findViewById(R.id.tv_logout_PF);
    }

    @Override
    public void onStop() {
        stopProgress();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }
}
