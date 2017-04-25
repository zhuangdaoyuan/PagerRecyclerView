package com.zdy.garden.recyclerviewpagerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int itemWidth;
    private int itemHeight;
    private int colums;

    //容器固定高度（根据需求设置--计算或者自适应高度）
    private int pagerViewMaxHeight = 500;

    List<MenuItem> data = new ArrayList<>();

    {
        for (int i = 1; i <= 17; i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setMenuName("菜单" + i);
            if (i < 10) {
                menuItem.setMenuIcon("http://static.yuanxininfo.com/assets/app/icon/menu000" + i + ".png");
            } else {
                menuItem.setMenuIcon("http://static.yuanxininfo.com/assets/app/icon/menu00" + i + ".png");
            }

            data.add(menuItem);
        }
    }

    //    PageGridView pageGridView, pageGridView3;
    PageGridView pageGridView2;
    MyPageIndicator pageIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageIndicator = (MyPageIndicator) findViewById(R.id.pageindicator);
        colums = calculateColums();
        itemWidth = getResources().getDisplayMetrics().widthPixels / colums;
        itemHeight = pagerViewMaxHeight / 2;
//        screenWidth = getResources().getDisplayMetrics().widthPixels;
//        pageGridView = (PageGridView) findViewById(R.id.pagingGridView);
        pageGridView2 = (PageGridView) findViewById(R.id.pagingGridView2);
//        pageGridView3 = (PageGridView) findViewById(R.id.pagingGridView3);

//        MyAdapter adapter1 = new MyAdapter(data);
        MyAdapter adapter2 = new MyAdapter(data);
        pageGridView2.setmColums(colums);
//        MyAdapter adapter3 = new MyAdapter(data);
//        pageGridView.setAdapter(adapter1);

        pageGridView2.setAdapter(adapter2);
//        pageGridView3.setAdapter(adapter3);
//        pageGridView.setOnItemClickListener(adapter1);
//        pageGridView3.setOnItemClickListener(adapter3);

        //设置分页指示器
        pageGridView2.setPageIndicator(pageIndicator);
    }

    //计算列数(行数固定为2)
    public int calculateColums() {
        if (data.size() > 4) {
            return 3;
        } else {
            return 2;
        }
    }


    public class MyAdapter extends PageGridView.PagingAdapter<MyVH> implements PageGridView.OnItemClickListener {
        List<MenuItem> mData = new ArrayList<>();

        public MyAdapter(List<MenuItem> data) {
            this.mData.addAll(data);
        }

        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_item, parent, false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = itemHeight;
            params.width = itemWidth;
            view.setLayoutParams(params);
            return new MyVH(view);
        }

        @Override
        public void onBindViewHolder(MyVH holder, int position) {
            MenuItem menuItem = mData.get(position);
            if (menuItem == null) {
                holder.icon.setVisibility(View.GONE);
                holder.tv_title.setText("");
            } else {
                holder.icon.setVisibility(View.VISIBLE);
                Glide.with(MainActivity.this).load(menuItem.getMenuIcon()).error(R.drawable.ic_icon).into(holder.icon);
                holder.tv_title.setText(menuItem.getMenuName());
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public List getData() {
            return mData;
        }

        @Override
        public Object getEmpty() {
            return null;
        }

        @Override
        public void onItemClick(PageGridView pageGridView, int position) {
            String gridview = "";
//            if (pageGridView == pageGridView) {
//                gridview = "第一个GridView";
//            }
            if (pageGridView == pageGridView2) {
                gridview = "第二个GridView";
            }
//            if (pageGridView == pageGridView3) {
//                gridview = "第三个GridView";
//            }

            Toast.makeText(MainActivity.this, gridview + " 第" + (position + 1) + "个item 被点击" + " 值：" + mData.get(position).getMenuName(), Toast.LENGTH_SHORT).show();
        }
    }

    public static class MyVH extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public ImageView icon;

        public MyVH(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
