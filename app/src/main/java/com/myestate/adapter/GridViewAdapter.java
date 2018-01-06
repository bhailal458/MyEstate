package com.myestate.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.myestate.MainActivity;
import com.myestate.R;
import com.myestate.databinding.GridRowBinding;
import com.myestate.fragments.HomeFragment;

/**
 * Created by Sony on 24-12-2017.
 */

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>{

    private GridRowBinding mBinding;
    private String[] mGrid = new String[0];
    private String[] mColor = new String[0];
    private Context context;
    private HomeClickListner homeClickListner;

    public GridViewAdapter(Context context, String[] mGridValue, String[] mGridColor, HomeClickListner homeClickListner) {
        this.context = context;
        this.mGrid = mGridValue;
        this.mColor = mGridColor;
        this.homeClickListner = homeClickListner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.grid_row,parent,false);
        return new ViewHolder(mBinding,parent);
    }

    @Override
    public void onBindViewHolder(GridViewAdapter.ViewHolder holder, int position) {
            holder.mBinding.textGrid.setText(mGrid[position]);
//            holder.mBinding.textGrid.setBackgroundColor(Color.parseColor(mColor[position].toString()));
            holder.mBinding.llGridRow.setBackgroundColor(Color.parseColor(mColor[position].toString()));
            holder.mBinding.llGridRow.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mGrid.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private GridRowBinding mBinding;
        public ViewHolder(GridRowBinding mBinding, View itemView) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
            this.mBinding.llGridRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (homeClickListner != null){
                int pos = (int) view.getTag();
                homeClickListner.onHomeClick(pos);
            }
        }
    }

    public interface HomeClickListner{
        void onHomeClick(int pos);
//        void onLandClick(int pos);
//        void onNewsBlogClick(int pos);
//        void onRevenueClick(int pos);
//        void onTPDPClick(int pos);
//        void onJantriClick(int pos);
//        void onUnitCpnverterClick(int pos);
//        void onLoanCalcClick(int pos);
//        void onGovtCirculer(int pos);
    }
}