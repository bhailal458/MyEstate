//package com.myestate.adapter;
//
//import android.content.Context;
//import android.databinding.DataBindingUtil;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.TextView;
//
//import com.myestate.MainActivity;
//import com.myestate.databinding.GridRowBinding;
//
///**
// * Created by Sony on 24-12-2017.
// */
//
//public class GridViewAdapter extends BaseAdapter{
//    private Context mContext;
//
//    public String[] mGridValue = {
//            "Home", "News & Blog",
//            "Land", "Property",
//            "Revenue Records",
//            "TP,DP,Village Map",
//            "Jantri",
//            "Unit Converter", "Loan Calulator",
//            "Covernment Circular"
//    };
//
//    public GridViewAdapter(Context context) {
//        this.mContext = context;
//    }
//
//    @Override
//    public int getCount() {
//        return mGridValue.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mGridValue[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView textView = new TextView(mContext);
//        textView.setText(mGridValue[position]);
//        textView.setLayoutParams(new GridView.LayoutParams(70,70));
//        return textView;
//    }
//}

///////////////////////////////////

// in MAINACTIVITY

//GridView gridView = (GridView) findViewById(R.id.grid_view);
//
//        // Instance of ImageAdapter Class
//        gridView.setAdapter(new GridViewAdapter(this));
