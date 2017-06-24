package cracker.example.com.news.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import cracker.example.com.news.R;


/**
 * Created by cracker on 2017/5/19.
 */

public class NewsWeb extends AppCompatActivity {

    String item_url;
    private WebView mWebView;

   private EditText edtComment;
    private Button btnComment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        //加载页面
        initWebView();

        initComment();

    }
    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.news_view);
        Intent intent = getIntent();
        item_url = intent.getStringExtra("item_url");
        mWebView.loadUrl(item_url );
    }

    private void initComment() {
        edtComment = (EditText) findViewById(R.id.edt_comment);
        btnComment = (Button) findViewById(R.id.btn_comment);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsWeb.this,LoginActivity.class));
            }
        });

    }
}
