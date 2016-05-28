package edu.hrbeu.ice.imagelistview;

import android.content.Context;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2016/5/21.
 */
public class MyAdapter extends BaseAdapter implements AbsListView.OnScrollListener {
    private static final String TAG = "MyAdapter";
    ArrayList<ItemBean> arrayList;
    Context context;
    MyImageLoader myImageLoader;

    private int mStart, mEnd;

    private String[] urlArray;

    private ListViewCompat mListview;

    private boolean firstFlag = true;

    public MyAdapter(Context context, ArrayList<ItemBean> arrayList, ListViewCompat listview) {
        super();
        this.context = context;
        this.arrayList = arrayList;
        myImageLoader = new MyImageLoader(context);
        mListview = listview;

        listview.setOnScrollListener(this);
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

        viewHolder.imageView.setTag(arrayList.get(position).drawable);
        // myImageLoader.getImageByThread(viewHolder.imageView, arrayList.get(position).drawable);
        //发现，不论怎么样，由于会重用view，所以滑动到未获取图片的item时，由于用到的可能是其他view重用来的，所以必然会显示原view上的图片，造成图片暂时错乱的感觉
        //所以滑动到没有获得图片的item时，用默认图片覆盖下重用来的view，即可解决这个现象。
        //等滑动再滑动到已经获取到图片的item时，虽然依旧重用了别人的view，但是由于可以从cache中快速获得到图片来进行替换，所以看不到图片暂时错乱的现象了
        //所以此处做如下处理
        if (!myImageLoader.getImageByCache(viewHolder.imageView, arrayList.get(position).drawable)) {
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        }
        viewHolder.titleText.setText(arrayList.get(position).title);
        viewHolder.detailText.setText(arrayList.get(position).detail);


        return view;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //  Log.e(TAG, "onScrollStateChanged() called with: " + " scrollState = [" + scrollState + "]");

        /**
         * 只有从网络获取图片时，才滚动时不加载图片，停止后加载
         * 而对于已经获取了图片的，由于从cache获取很迅速，所以可以不用上述规则，否则会出现当图片都下载后，再次滑回时会看到错误的图片的现象，停下来才从缓存获取正确的图片
         */
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {//停止状态

            myImageLoader.loadAllImage(view, mStart, mEnd, arrayList);
            Log.e(TAG, "onScrollStateChanged: loadAllImage");
        } else//拽动或滑动状态
        {

        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //   Log.e(TAG, "onScroll() called with: " +"firstVisibleItem = [" + firstVisibleItem + "], visibleItemCount = [" + visibleItemCount + "], totalItemCount = [" + totalItemCount + "]");
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        //初次进入后加载
        if (firstFlag && visibleItemCount > 0) {
            myImageLoader.loadAllImage(view, mStart, mEnd, arrayList);
            firstFlag = false;
        }
    }


    class ViewHolder {
        ImageView imageView;
        TextView titleText;
        TextView detailText;


    }
}
