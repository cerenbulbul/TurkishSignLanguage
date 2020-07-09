package turk.isaret.dili.egitimi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class test extends AppCompatActivity {

    int turn=0;
    int counter=0;
    int total=0;
    int dersID = MainActivity.getDersID();
    ArrayList<String> array_gif = new ArrayList<>();
    ArrayList<String> array_name = new ArrayList<>();
    TextView point;
    String networkStatus="";
    public static final String MY_PREFS_NAME = "userProfile";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final WebView wb = (WebView) findViewById(R.id.wb);
        final Button button1=(Button)findViewById(R.id.button1);
        final Button button2=(Button)findViewById(R.id.button2);
        final Button button3=(Button)findViewById(R.id.button3);
        final Button button4=(Button)findViewById(R.id.button4);
        final Button button5=(Button)findViewById(R.id.button5);
        final Button button6=(Button)findViewById(R.id.button6);
        point = findViewById(R.id.point);

        getInfo();
        updateOptions(button1,button2,button3,wb);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
                checkRound(button1,button2,button3,wb);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
                checkRound(button1,button2,button3,wb);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(3);
                checkRound(button1,button2,button3,wb);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(test.this, Lesson.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(test.this, "Quiz daha bitmedi!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getInfo(){
        try {
            URL url = new URL("http://turkisaretdiliegitimi.dx.am/getGif.php?ders=" + dersID);
            URLConnection uc = url.openConnection();
            uc.setConnectTimeout(10000);
            uc.setReadTimeout(10000);
            uc.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = in.readLine();

            if (line.length() < 3) {
                networkStatus = "Empty";
//                Toast.makeText(test.this, "", Toast.LENGTH_LONG).show();
                in.close();
            }
            else {
                networkStatus = "Connected";
                String[] lineSplit = line.split(",");
                for (int i = 0; i < lineSplit.length - 1; i += 3) {
                    array_gif.add(lineSplit[i]);
                    array_name.add(lineSplit[i + 1]);
                }
//                Toast.makeText(test.this, Toast.LENGTH_LONG).show();
                in.close();
            }
        }
        catch (IOException e) {
            networkStatus = "No Connection";
            Toast.makeText(test.this, "Internetiniz Baglantiniz Mevcut Degil", Toast.LENGTH_LONG).show();
        }
    }

    public void updateOptions(Button button1,Button button2,Button button3,WebView wb){
        if(networkStatus.equals("Connected")) {
            List<String> temporaryNames = new ArrayList<>(array_name);
            ArrayList<String> temporaryOptions = new ArrayList<>();
            final Random rand = new Random();
            for (int i = 0; i < 3; i++) {
                int random = rand.nextInt(temporaryNames.size());
                if (i > 0) {
                    temporaryOptions.add(temporaryNames.get(random));
                    temporaryNames.remove(temporaryNames.get(random));
                } else {
                    temporaryOptions.add(temporaryNames.get(turn));
                    temporaryNames.remove(temporaryNames.get(turn));
                }
            }
            Collections.shuffle(temporaryOptions);
            button1.setText(temporaryOptions.get(0));
            button2.setText(temporaryOptions.get(1));
            button3.setText(temporaryOptions.get(2));
            wb.loadUrl(array_gif.get((turn)));
            wb.getSettings().setBuiltInZoomControls(true);
            wb.getSettings().setSupportZoom(true);
            wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            wb.getSettings().setAllowFileAccess(true);
            wb.getSettings().setDomStorageEnabled(true);
            wb.getSettings().setJavaScriptEnabled(true);
            wb.getSettings().setLoadWithOverviewMode(true);
            wb.getSettings().setUseWideViewPort(true);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void checkAnswer(int answer){
        Button button = null;
        switch (answer){
            case 1: button = findViewById(R.id.button1);
                break;
            case 2: button = findViewById(R.id.button2);
                break;
            case 3: button = findViewById(R.id.button3);
                break;
        }
        if(button!=null) {
            if (button.getText().toString().equalsIgnoreCase(array_name.get(turn))) {
                Toast.makeText(test.this, "Dogru!!", Toast.LENGTH_SHORT).show();
                counter++;
                total = (counter * 100) / array_gif.size();
                point.setText("Toplam Puan: " + (total));
            } else {
                Toast.makeText(test.this, "Yanlis!!", Toast.LENGTH_SHORT).show();
            }
            turn++;
        }
    }

    public void checkRound(Button button1,Button button2,Button button3, WebView wb){
        if(turn<array_gif.size()){
            updateOptions(button1,button2,button3,wb);
        }
        else{
            editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("score"+dersID, total);
            editor.apply();
            Toast.makeText(test.this, "Sinav bitti!!", Toast.LENGTH_SHORT).show();
            if (total>50) {
                successDialog();
            }
            else{
                failDialog();
            }
        }
    }

    public void successDialog(){
        FancyGifDialog build = new FancyGifDialog.Builder(test.this)
                .setTitle("SINAV BITTI YENI KONUYA GECEBILIRSIN")
                .setPositiveBtnText("Ok")
                .setPositiveBtnBackground("#FF4081")
                .setGifResource(R.drawable.gif1)
                .isCancellable(false)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Intent intent = new Intent(test.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).build();
    }

    public void failDialog(){
        FancyGifDialog build = new FancyGifDialog.Builder(test.this)
                .setTitle("SINAV BITTI KONUYU TEKRAR ETMEN LAZIM")
                .setPositiveBtnText("Ok")
                .setPositiveBtnBackground("#FF4081")
                .setGifResource(R.drawable.gif1)
                .isCancellable(false)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Intent intent = new Intent(test.this, Lesson.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).build();
    }

}

