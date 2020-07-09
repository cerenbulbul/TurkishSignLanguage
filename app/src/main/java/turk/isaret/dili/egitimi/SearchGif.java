package turk.isaret.dili.egitimi;

import android.content.Intent;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchGif extends AppCompatActivity {

    String gifUrl = Search.getGifUrl();
    String gifName = Search.getGifName();
    ArrayList<String> array_gif = new ArrayList<String>();
    ArrayList<String> array_name = new ArrayList<String>();
    Button button1;

    BottomNavigationItemView home, myAccount, search;
    BottomNavigationView b;

    WebView wb;
    TextView text;
    String networkStatus="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gif);

        wb= (WebView)findViewById(R.id.wb);
        text= (TextView)findViewById(R.id.text);
        button1 =(Button)findViewById(R.id.button1);

        BottomNavigationItemView ev = (BottomNavigationItemView) findViewById(R.id.ev);
        BottomNavigationItemView myAccount = (BottomNavigationItemView) findViewById(R.id.profil);
        BottomNavigationItemView search = (BottomNavigationItemView) findViewById(R.id.search);
        b = findViewById(R.id.bottom_navigaitonview);
        b.setSelectedItemId(R.id.search);

        try {
            networkStatus = "Connected";
            Toast.makeText(SearchGif.this, networkStatus, Toast.LENGTH_LONG).show();
            wb.loadUrl(gifUrl);
            wb.getSettings().setBuiltInZoomControls(true);
            wb.getSettings().setSupportZoom(true);
            wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            wb.getSettings().setAllowFileAccess(true);
            wb.getSettings().setDomStorageEnabled(true);
            wb.getSettings().setJavaScriptEnabled(true);
            wb.getSettings().setLoadWithOverviewMode(true);
            wb.getSettings().setUseWideViewPort(true);
            text.setText(gifName);
        }

        catch (Exception e){
            networkStatus = "No Connection";
            Toast.makeText(SearchGif.this, "Internetiniz Baglantiniz Mevcut Degil", Toast.LENGTH_LONG).show();
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchGif.this,Search.class);
                startActivity(intent);

            }
        });

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchGif.this, Account.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchGif.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
