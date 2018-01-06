package com.myestate.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myestate.R;
import com.myestate.baseclass.BaseFragment;
import com.myestate.databinding.FragmentWebBinding;
import com.myestate.utility.Utility;

import butterknife.ButterKnife;



public class WebFragment extends BaseFragment {

    private FragmentWebBinding mBinding;
    private boolean isLoad;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false);
        ButterKnife.bind(this, mBinding.getRoot());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String strtext = getArguments().getString("keyurl");
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.getSettings().setLoadWithOverviewMode(true);
        mBinding.webview.getSettings().setUseWideViewPort(true);
        mBinding.webview.getSettings().setBuiltInZoomControls(true);
        mBinding.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Utility.log("Error call");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(!isLoad) {
                    isLoad = true;
                    showDialog();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                dismissDialog();
            }
        });
        mBinding.webview.loadUrl(strtext);
    }
}
