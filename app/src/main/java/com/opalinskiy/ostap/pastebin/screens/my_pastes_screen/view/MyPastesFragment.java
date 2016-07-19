package com.opalinskiy.ostap.pastebin.screens.my_pastes_screen.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opalinskiy.ostap.pastebin.Application;
import com.opalinskiy.ostap.pastebin.R;
import com.opalinskiy.ostap.pastebin.global.Constants;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.DaggerMyPastesComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.components.MyPastesComponent;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.AppModule;
import com.opalinskiy.ostap.pastebin.interactor.dagger.modules.MyPastesModule;
import com.opalinskiy.ostap.pastebin.interactor.models.Paste;
import com.opalinskiy.ostap.pastebin.screens.base.BaseFragment;
import com.opalinskiy.ostap.pastebin.screens.main_screen.view.NavigationDrawerActivity;
import com.opalinskiy.ostap.pastebin.screens.my_pastes_screen.IMyPastesScreen;
import com.opalinskiy.ostap.pastebin.screens.paste_code_screen.view.PasteCodeFragment;

import java.util.List;

import javax.inject.Inject;

public class MyPastesFragment extends BaseFragment
        implements IMyPastesScreen.IView, IMyPastesScreen.IItemClickHandler {
    @Inject
    IMyPastesScreen.IPresenter presenter;
    private RecyclerView recyclerView;
    private int myOrTrending;
    // TODO: 7/19/16 It can be injected via component
    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_pastes_fragment, container, false);
        init(view);
        Log.d(Constants.TAG1, "my or tranging" + myOrTrending);

        MyPastesComponent component = DaggerMyPastesComponent.builder()
                .appComponent(Application.getAppComponent())
                .myPastesModule(new MyPastesModule(this, myOrTrending))
                .build();
        component.inject(this);

        // TODO: 7/19/16 Why do we want set myOrTrending, if we injected via constructor of presenter
        presenter.choseTitle(myOrTrending);
        presenter.showMyPastes(prefs);
        Log.d(Constants.TAG1, "TRENDING FRAGMENT onCreateView()");
        return view;
    }

    public static MyPastesFragment newInstance(int myOrTRending) {

        Bundle args = new Bundle();
        // TODO: 7/19/16 Why do you check this? You can give it to argument
        if (myOrTRending == Constants.TRENDING_PASTES) {
            args.putInt(Constants.MY_OR_TRANDING_KEY, Constants.TRENDING_PASTES);
        } else {
            args.putInt(Constants.MY_OR_TRANDING_KEY, Constants.MY_PASTES);
        }

        MyPastesFragment fragment = new MyPastesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_MPF);
        myOrTrending = getArguments().getInt(Constants.MY_OR_TRANDING_KEY);
        prefs = getActivity().getSharedPreferences(Constants.PREFS_NAME, 0);
    }

    @Override
    public void setDataToRecyclerView(List<Paste> myPastes) {
        // TODO: 7/19/16 You can inject via component
        MyPastesAdapter adapter = new MyPastesAdapter(myPastes, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onItemClick(String url) {
        PasteCodeFragment fragment = PasteCodeFragment.newInstance(url, myOrTrending);
        ((NavigationDrawerActivity) getActivity()).commitFragment(fragment
                , Constants.MY_PASTES_CODE_FRAGMENT_TAG, true, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        stopProgress();
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroyView();
    }
}
