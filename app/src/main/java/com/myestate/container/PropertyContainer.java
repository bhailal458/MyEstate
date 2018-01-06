package com.myestate.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myestate.R;
import com.myestate.fragments.PropertyFragment;



public class PropertyContainer extends BaseContainer {
    private PropertyFragment propertyFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (propertyFragment == null)
            propertyFragment = new PropertyFragment();
        replaceFragment(propertyFragment);
    }

}
