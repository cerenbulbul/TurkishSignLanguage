package turk.isaret.dili.egitimi;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class lesson2 extends AppCompatActivity {

    int dersID = MainActivity.getDersID();
    ArrayList<String> array_gif = new ArrayList<>();
    ArrayList<String> array_name = new ArrayList<>();
    String networkStatus="";
    WebView wb;
    private int currentVideo=0;
    private int currentText=0;
    TextView textView1;
    SeekBar packageRange;
    int total = 0;
    int total2;
    int counter=2;
    int counter2;
    ImageView imageView1;
    Button buttonNext , buttonPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        wb = (WebView) findViewById(R.id.wb);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonPrev = (Button) findViewById(R.id.buttonPrev);
        packageRange = (SeekBar) findViewById(R.id.packageRange);
        textView1 = (TextView) findViewById(R.id.textView1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);

        getLessonInfo();

        packageRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                packageRange.setProgress(10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                packageRange.setProgress(10);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                packageRange.setProgress(10);

            }
        });

        if(networkStatus.equals("Connected")) {

            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentVideo<array_gif.size()-1 &&currentText<array_gif.size()-1) {
                        currentVideo++;
                        currentText++;
                        wb.loadUrl(array_gif.get((currentVideo)));
                        wb.getSettings().setLoadWithOverviewMode(true);
                        wb.getSettings().setUseWideViewPort(true);
                        textView1.setText(array_name.get((currentText)));

                        total = ((counter*100) / array_gif.size()-1);

                        packageRange.setProgress(total);
                        counter++;

                        packageRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                packageRange.setProgress(total);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                                packageRange.setProgress(total);

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                packageRange.setProgress(total);

                            }
                        });


                    }
                }
            });
            buttonPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentVideo > 0 && currentText > 0) {
                        counter2=counter-2;
                        currentVideo--;
                        currentText--;
                        wb.loadUrl(array_gif.get((currentVideo)));
                        wb.getSettings().setLoadWithOverviewMode(true);
                        wb.getSettings().setUseWideViewPort(true);
                        textView1.setText(array_name.get((currentText)));

                        total = ((counter2*100) / array_gif.size()-1);

                        packageRange.setProgress(total);
                        counter--;
                        packageRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                packageRange.setProgress(total);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                                packageRange.setProgress(total);

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                packageRange.setProgress(total);

                            }
                        });


                    }

                }
            });
            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentVideo > -1) {
                        currentVideo = currentVideo--;

                        wb.loadUrl(array_gif.get((currentVideo)));
                        wb.getSettings().setLoadWithOverviewMode(true);
                        wb.getSettings().setUseWideViewPort(true);
                        textView1.setText(array_name.get((currentText)));
                    }
                }
            });
        }

    }

    public void getLessonInfo(){
        try {
            URL url = new URL("http://turkisaretdiliegitimi.dx.am/getGif.php?ders=" + dersID);
            URLConnection uc = url.openConnection();
            uc.setConnectTimeout(10000);
            uc.setReadTimeout(10000);
            uc.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String line = in.readLine();

            if (line.length() < 3){
                networkStatus = "Empty";
//                Toast.makeText(Lesson.this,"", Toast.LENGTH_LONG).show();
                in.close();
            }
            else{
                networkStatus = "Connected";
//                Toast.makeText(Lesson.this,"", Toast.LENGTH_LONG).show();
                String array[] = line.split(",");
                for (int i =0; i<array.length-1; i+=3){
                    array_gif.add(array[i]);
                    array_name.add(array[i+1]);
                }
                wb.loadUrl(array_gif.get((currentVideo)));
                wb.getSettings().setBuiltInZoomControls(true);
                wb.getSettings().setSupportZoom(true);
                wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                wb.getSettings().setAllowFileAccess(true);
                wb.getSettings().setDomStorageEnabled(true);
                wb.getSettings().setJavaScriptEnabled(true);
                wb.getSettings().setLoadWithOverviewMode(true);
                wb.getSettings().setUseWideViewPort(true);
                textView1.setText(array_name.get((currentText)));
                in.close();
            }
        }
        catch (IOException e){
            networkStatus = "No Connection";
            Toast.makeText(lesson2.this,"Internetiniz Baglantiniz Mevcut Degil", Toast.LENGTH_LONG).show();
        }
    }

    }
