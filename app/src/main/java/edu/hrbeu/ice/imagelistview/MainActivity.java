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

        for (int i = 0; i < 56; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.drawable = "";
            itemBean.title = "";
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
