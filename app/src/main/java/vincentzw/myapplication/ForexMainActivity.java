package vincentzw.myapplication;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class ForexMainActivity extends AppCompatActivity {
    private ProgressBar loadingProgressBar;

    private TextView audTextView, bndTextView, btcTextView, eurTextView, gbpTextView, hkdTextView, inrTextView, jpyTextView, myrTextView, usdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forex_main);


        audTextView = findViewById(R.id.audTextView);
        bndTextView = findViewById(R.id.awgTextView);
        btcTextView = findViewById(R.id.btcTextView);
        eurTextView = findViewById(R.id.bgnTextView);
        gbpTextView = findViewById(R.id.hkdTextView);
        hkdTextView = findViewById(R.id.hkdTextView);
        inrTextView = findViewById(R.id.aoaTextView);
        jpyTextView = findViewById(R.id.usdTextView);
        myrTextView = findViewById(R.id.audTextView);
        usdTextView = findViewById(R.id.usdTextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);


        initForex();
    }

    private void initForex() {
        loadingProgressBar.setVisibility(TextView.VISIBLE);

        String url = "https://openexchangerates.org/api/latest.json?app_id=e54d79084f56452898e94b0e64e12870";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                ForexRootModel rootModel = gson.fromJson(new String(responseBody), ForexRootModel.class);
                ForexRatesModel ratesModel = rootModel.getRatesModel();

                double aud = ratesModel.getIDR() / ratesModel.getAUD();
                double bnd = ratesModel.getIDR() / ratesModel.getAWG();
                double btc = ratesModel.getIDR() / ratesModel.getBTC();
                double eur = ratesModel.getIDR() / ratesModel.getAZN();
                double gbp = ratesModel.getIDR() / ratesModel.getBHD();
                double hkd = ratesModel.getIDR() / ratesModel.getHKD();
                double inr = ratesModel.getIDR() / ratesModel.getAWG();
                double jpy = ratesModel.getIDR() / ratesModel.getHKD();
                double myr = ratesModel.getIDR() / ratesModel.getAZN();
                double idr = ratesModel.getIDR();

                audTextView.setText(formatNumber(aud, "###,##0.##"));
                bndTextView.setText(formatNumber(bnd, "###,##0.##"));
                btcTextView.setText(formatNumber(btc, "###,##0.##"));
                eurTextView.setText(formatNumber(eur, "###,##0.##"));
                gbpTextView.setText(formatNumber(gbp, "###,##0.##"));
                hkdTextView.setText(formatNumber(hkd, "###,##0.##"));
                inrTextView.setText(formatNumber(inr, "###,##0.##"));
                jpyTextView.setText(formatNumber(jpy, "###,##0.##"));
                myrTextView.setText(formatNumber(myr, "###,##0.##"));
                usdTextView.setText(formatNumber(idr, "###,##0.##"));

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
        });

    }



    public String formatNumber(double number, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(number);
    }
}