package cracker.example.com.news.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cracker.example.com.news.Activity.NewsWeb;
import cracker.example.com.news.Adapter.NewsAdapter;
import cracker.example.com.news.Bean.Data;
import cracker.example.com.news.Interface.OnMyItemClickListener;
import cracker.example.com.news.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cracker on 2017/5/17.
 */

public class TechnologyFragment extends Fragment {
    private static final int MSG_NEWS = 1001;
    private List<Data.ResultBean.DataBean> mNewsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private Handler handler;
    private final String URL = "http://v.juhe.cn/toutiao/index?type=keji&key=cb48295adc20d11d85a5b7bd049cfe9e";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hots, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initNewsFromJuHe();
        mNewsAdapter = new NewsAdapter(mNewsList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mNewsAdapter);

        mNewsAdapter.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                Toast.makeText(getContext(),"你点击了我",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), NewsWeb.class);
                intent.putExtra("item_url",mNewsList.get(postion).getUrl());
                startActivity(intent);
            }
        });

        return view;
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == MSG_NEWS) {
                    mNewsAdapter.changeData(mNewsList);
                    return true;
                }
                return false;
            }
        });
    }

    private void initNewsFromJuHe() {

        //创建okhttpClient对象
        OkHttpClient client = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder().url(URL).build();
        //new Call
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("AAA", "GET DATA FAILED");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                Data newsData = gson.fromJson(response.body().string(), Data.class);
                mNewsList = newsData.getResult().getData();
                handler.sendEmptyMessage(MSG_NEWS);
            }
        });
    }
}
