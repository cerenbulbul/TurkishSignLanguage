package turk.isaret.dili.egitimi;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Search extends AppCompatActivity {

    WebView wb;
    ListView list;
    ArrayList<String> array_gif = new ArrayList<String>();
    ArrayList<String> array_name = new ArrayList<String>();
    String networkStatus="";
    private static String gifUrl;
    private static String gifName;
    BottomNavigationView b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = (ListView) findViewById(R.id.list);
        SearchView sv = findViewById(R.id.sv);
        sv.setActivated(true);
        sv.setQueryHint("Type your keyword here");
        sv.onActionViewExpanded();
        sv.setIconified(false);

        BottomNavigationItemView ev = (BottomNavigationItemView) findViewById(R.id.ev);
        BottomNavigationItemView myAccount = (BottomNavigationItemView) findViewById(R.id.profil);
        BottomNavigationItemView search = (BottomNavigationItemView) findViewById(R.id.search);
        b = findViewById(R.id.bottom_navigaitonview);
        b.setSelectedItemId(R.id.search);
        try{
            URL url = new URL("http://turkisaretdiliegitimi.dx.am/getSearch.php");
            URLConnection uc = url.openConnection();
            uc.setConnectTimeout(10000);
            uc.setReadTimeout(10000);
            uc.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = in.readLine();
            if(line.length() < 1){
                networkStatus = "Empty";
//                Toast.makeText(Search.this, networkStatus, Toast.LENGTH_LONG).show();
                in.close();
            }
            else{
                networkStatus = "Connected";
//                Toast.makeText(Search.this, networkStatus, Toast.LENGTH_LONG).show();
                array_gif.clear();
                array_name.clear();
                String[] array = line.split(",");
                for (int i = 0; i < array.length - 1; i += 2){
                    array_gif.add(array[i]);
                    array_name.add(array[i + 1]);
                }
                in.close();
            }
        }
        catch(IOException e) {
            networkStatus = "No Connection";
            Toast.makeText(Search.this, "Internetiniz Baglantiniz Mevcut Degil", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, array_name);
        list.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //gifID = i;
                gifName = ((TextView) view).getText().toString();
                gifUrl = array_gif.get(array_name.indexOf(gifName));
                Intent intent = new Intent(Search.this, SearchGif.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, Account.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


    public static String getGifUrl(){
        return gifUrl;
    }

    public static String getGifName(){
        return gifName;
    }


}
