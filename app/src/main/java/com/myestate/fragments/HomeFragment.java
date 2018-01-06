package com.myestate.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.myestate.R;
import com.myestate.adapter.GridViewAdapter;
import com.myestate.baseclass.BaseFragment;
import com.myestate.container.BaseContainer;
import com.myestate.container.LandContainer;
import com.myestate.container.NewsBlogContainer;
import com.myestate.container.PropertyContainer;
import com.myestate.databinding.FragmentHomeBinding;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements GridViewAdapter.HomeClickListner {
    private FragmentHomeBinding mBinding;
    private GridViewAdapter mGridViewAdapter;
    private static String currentFragment;

    String[] gridColor ={
            "#FF0000",
            "#0000FF",
            "#8B4513",
            "#006400",
            "#8B008B",
            "#FF8C00",
            "#800000",
            "#4169E1",
            "#B8860B",

    };
    String[] mGridValue = {
            "Property",
            "Land",
            "News & Blog",
            "Revenue Records",
            "TP,DP,Village Map",
            "Jantri",
            "Unit Converter",
            "Loan Calulator",
            "Govt. Circular"
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mBinding.getRoot());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mGridViewAdapter = new GridViewAdapter(getActivity(),mGridValue,gridColor,this);
        mBinding.recyclerView.setAdapter(mGridViewAdapter);
    }

    @Override
    public void onHomeClick(int pos) {
        Fragment fragment = null;
        String title = null;
        switch(pos) {
            case 0:
                Toast.makeText(getActivity(), "pos 0", Toast.LENGTH_SHORT).show();
                title = "Property";
                currentFragment = PropertyContainer.class.getSimpleName();
                fragment = new PropertyFragment();
                break;
            case 1:
                Toast.makeText(getActivity(), "pos 1", Toast.LENGTH_SHORT).show();
                title = "Land";
                currentFragment = LandContainer.class.getSimpleName();
                fragment = new LandFragment();
                break;
            case 2:
                Toast.makeText(getActivity(), "pos 2", Toast.LENGTH_SHORT).show();
                title = "News and Blog";
                currentFragment = NewsBlogContainer.class.getSimpleName();
                fragment = new NewsBlogFragment();
                break;
            case 3:
                Toast.makeText(getActivity(), "pos 3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                ((BaseContainer) getParentFragment()).addFragment(new PropertyFragment());
                Toast.makeText(getActivity(), "pos 4", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                ((BaseContainer) getParentFragment()).addFragment(new PropertyFragment());
                Toast.makeText(getActivity(), "pos 5", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                ((BaseContainer) getParentFragment()).addFragment(new PropertyFragment());
                Toast.makeText(getActivity(), "pos 6", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                ((BaseContainer) getParentFragment()).addFragment(new PropertyFragment());
                Toast.makeText(getActivity(), "pos 7", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                ((BaseContainer) getParentFragment()).addFragment(new PropertyFragment());
                Toast.makeText(getActivity(), "pos 8", Toast.LENGTH_SHORT).show();
                break;

        }
        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, fragment, fragment.getClass().getSimpleName());
            ft.commit();

        }
    }
}
