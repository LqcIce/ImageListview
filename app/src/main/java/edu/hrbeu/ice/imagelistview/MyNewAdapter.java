package edu.hrbeu.ice.imagelistview;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by ice on 2016/5/21.
 */
public class MyNewAdapter extends ArrayAdapter {
    public MyNewAdapter(Context context, int resource) {
        super(context, resource);
    }

    public MyNewAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public MyNewAdapter(Context context, int resource, Object[] objects) {
        super(context, resource, objects);
    }

    public MyNewAdapter(Context context, int resource, int textViewResourceId, Object[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public MyNewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    public MyNewAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
