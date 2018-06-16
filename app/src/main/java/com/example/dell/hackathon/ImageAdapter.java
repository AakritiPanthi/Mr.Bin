package com.example.dell.hackathon;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Dell on 22/04/2018.
 */

public class ImageAdapter extends PagerAdapter{
    Context mContext;
    private int[] sliderImagesId = new int[]{
            R.drawable.img5,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img1,
            R.drawable.img6
    };

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    public ImageAdapter(MainActivity mainActivity) {

    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }



    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v== ((LinearLayout) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        mContext = container.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(sliderImagesId[i]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        container.removeView((LinearLayout) obj);
    }
}
