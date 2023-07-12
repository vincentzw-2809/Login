package vincentzw.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CuacaMainActivity extends AppCompatActivity
{

    private RecyclerView _recyclerView2;
    private SwipeRefreshLayout _swipeRefreshLayout2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _recyclerView2 = (RecyclerView)findViewById(R.id.recycleView2);
        _swipeRefreshLayout2 = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout2);

        initRecylerView2();
        initSwipeRefreshlayout();
    }

    private void initSwipeRefreshlayout()
    {
        _swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                initRecylerView2();
            }
        });
    }

    private void initRecylerView2() {

        _swipeRefreshLayout2.setRefreshing(true);

        String url = "https://api.openweathermap.org/data/2.5/forecast?id=1630789&appid=3fcbc6b19cef75132890e7e9b8ced300";
        AsyncHttpClient ahc = new AsyncHttpClient();

        ahc.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //  Log.d("*tw*", new String(responseBody));
                Gson gson = new Gson();
                CuacaRootModel rm = gson.fromJson(new String(responseBody), CuacaRootModel.class);

                // Log.d("*tw*, rm.getListModelList().get(0).getDt_txt());

                RecyclerView.LayoutManager lm = new LinearLayoutManager( CuacaMainActivity.this);
                CuacaAdapter ca = new CuacaAdapter(CuacaMainActivity.this, rm);

                _recyclerView2.setLayoutManager(lm);
                _recyclerView2.setAdapter(ca);

                _swipeRefreshLayout2.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                _swipeRefreshLayout2.setRefreshing(false);
            }
        });
    }
}

    private void initRecylerView2() {

        _swipeRefreshLayout2.setRefreshing(true);

        String url = "https://api.openweathermap.org/data/2.5/forecast?id=1630789&appid=3fcbc6b19cef75132890e7e9b8ced300";
        AsyncHttpClient ahc = new AsyncHttpClient();

        ahc.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //  Log.d("*tw*", new String(responseBody));
                Gson gson = new Gson();
                CuacaRootModel rm = gson.fromJson(new String(responseBody), CuacaRootModel.class);

                // Log.d("*tw*, rm.getListModelList().get(0).getDt_txt());

                RecyclerView.LayoutManager lm = new LinearLayoutManager( CuacaMainActivity.this);
                CuacaAdapter ca = new CuacaAdapter(CuacaMainActivity.this, rm);

                _recyclerView2.setLayoutManager(lm);
                _recyclerView2.setAdapter(ca);

                _swipeRefreshLayout2.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                _swipeRefreshLayout2.setRefreshing(false);
            }
        });
    }
}