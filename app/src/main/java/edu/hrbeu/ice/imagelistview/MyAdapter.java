package edu.hrbeu.ice.imagelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ice on 2016/5/21.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<ItemBean> arrayList;
    Context context;
    MyImageLoader myImageLoader;

    public MyAdapter(Context context, ArrayList<ItemBean> arrayList) {
        super();
        this.context = context;
        this.arrayList = arrayList;
        myImageLoader = new MyImageLoader(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.my_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.image);
            viewHolder.detailText = (TextView) view.findViewById(R.id.detail);
            viewHolder.titleText = (TextView) view.findViewById(R.id.title);

            view.setTag(viewHolder);

        } else {
            view = convertView;
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        //    viewHolder.imageView.setImageDrawable(arrayList.get(position).drawable);
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.imageView.setTag(arrayList.get(position).drawable);
        myImageLoader.getImageByThread(viewHolder.imageView, arrayList.get(position).drawable);
        viewHolder.titleText.setText(arrayList.get(position).title);
        viewHolder.detailText.setText(arrayList.get(position).detail);


        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView titleText;
        TextView detailText;


    }
}
