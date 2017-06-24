package cracker.example.com.news.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cracker.example.com.news.Bean.Data;
import cracker.example.com.news.Interface.OnMyItemClickListener;
import cracker.example.com.news.R;


/**
 * Created by cracker on 2017/5/18.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder>{
    private List<Data.ResultBean.DataBean> mNewsList = new ArrayList<>();
    private OnMyItemClickListener mItemClickListener;

    public NewsAdapter(List<Data.ResultBean.DataBean> newsList) {
        mNewsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        NewsViewHolder holder = new NewsViewHolder(view,mItemClickListener);
        return holder;
    }

    public void setOnItemClickListener(OnMyItemClickListener listener){
        this.mItemClickListener = listener;
    }
    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
       Data.ResultBean.DataBean dataBean = mNewsList.get(position);
        holder.item_title.setText(dataBean.getTitle());
        Glide.with(holder.item_img.getContext())
                .load(dataBean.getThumbnail_pic_s())
                .into(holder.item_img);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void changeData(List<Data.ResultBean.DataBean> newsList) {
        this.mNewsList = newsList;
        notifyDataSetChanged();
    }
}
