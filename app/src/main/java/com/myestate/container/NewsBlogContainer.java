package com.myestate.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myestate.R;
import com.myestate.fragments.NewsBlogFragment;



public class NewsBlogContainer extends BaseContainer {
    private NewsBlogFragment newsBlogFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (newsBlogFragment == null)
            newsBlogFragment = new NewsBlogFragment();
        replaceFragment(newsBlogFragment);
    }

}
