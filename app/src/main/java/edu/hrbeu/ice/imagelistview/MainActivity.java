package edu.hrbeu.ice.imagelistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.listview)
    ListViewCompat listview;
    private ArrayList<ItemBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        arrayList = new ArrayList<>();

        for (int i = 1; i <=48; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.drawable = "https://raw.githubusercontent.com/LqcIce/PicStore/master/pic_"+(i<10?"0"+i:i)+".gif";
            itemBean.title = "item第"+i+"项";
            arrayList.add(itemBean);
        }


        final MyAdapter myAdapter = new MyAdapter(MainActivity.this, arrayList);
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);

                startActivity(intent);
            }
        });

    }
}
