package turk.isaret.dili.egitimi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Account extends AppCompatActivity {

    Button button1;
    EditText text1;
    BottomNavigationView navBar;
    BottomNavigationItemView home, myAccount, search;
    public static final String MY_PREFS_NAME = "userProfile";
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    int scor1,scor2,scor3,scor4,scor5,scor6,scor7,scor8;
    SeekBar seekbar1,seekbar2,seekbar3,seekbar4,seekbar5,seekbar6,seekbar7,seekbar8;
    ImageView profile;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;

    private boolean blockSeekBar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        button1 = (Button) findViewById(R.id.button1);
        text1 = (EditText) findViewById(R.id.text1);
        home = (BottomNavigationItemView) findViewById(R.id.ev);
        myAccount = (BottomNavigationItemView) findViewById(R.id.profil);
        search = (BottomNavigationItemView) findViewById(R.id.search);
        navBar = findViewById(R.id.bottom_navigaitonview);
        navBar.setSelectedItemId(R.id.profil);

        seekbar1 = (SeekBar)findViewById(R.id.seekbar1);
        seekbar2 = (SeekBar)findViewById(R.id.seekbar2);
        seekbar3 = (SeekBar)findViewById(R.id.seekbar3);
        seekbar4 = (SeekBar)findViewById(R.id.seekbar4);
        seekbar5 = (SeekBar)findViewById(R.id.seekbar5);
        seekbar6 = (SeekBar)findViewById(R.id.seekbar6);
        seekbar7 = (SeekBar)findViewById(R.id.seekbar7);
        seekbar8 = (SeekBar)findViewById(R.id.seekbar8);

        textView1=(TextView)findViewById(R.id.textView1);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        textView4=(TextView)findViewById(R.id.textView4);
        textView5=(TextView)findViewById(R.id.textView5);
        textView6=(TextView)findViewById(R.id.textView6);
        textView7=(TextView)findViewById(R.id.textView7);
        textView8=(TextView)findViewById(R.id.textView8);

        profile =(ImageView)findViewById(R.id.profile);


        scor1 = prefs.getInt("score1", 0);
        seekbar1.setProgress(scor1);
        textView1.setText(String.valueOf(scor1));

        scor2 = prefs.getInt("score2", 0);
        seekbar2.setProgress(scor2);
        textView2.setText(String.valueOf(scor2));

        scor3 = prefs.getInt("score3", 0);
        seekbar3.setProgress(scor3);
        textView3.setText(String.valueOf(scor3));

        scor4 = prefs.getInt("score4", 0);
        seekbar4.setProgress(scor4);
        textView4.setText(String.valueOf(scor4));

        scor5 = prefs.getInt("score5", 0);
        seekbar5.setProgress(scor5);
        textView5.setText(String.valueOf(scor5));

        scor6 = prefs.getInt("score6", 0);
        seekbar6.setProgress(scor6);
        textView6.setText(String.valueOf(scor6));

        scor7 = prefs.getInt("score7", 0);
        seekbar7.setProgress(scor7);
        textView7.setText(String.valueOf(scor7));

        scor8 = prefs.getInt("score8", 0);
        seekbar8.setProgress(scor8);
        textView8.setText(String.valueOf(scor8));

       seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               seekbar1.setProgress(scor1);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {
               seekbar1.setProgress(scor1);
           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar2.setProgress(scor2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbar2.setProgress(scor2);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar3.setProgress(scor3);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbar3.setProgress(scor3);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });seekbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar4.setProgress(scor4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbar4.setProgress(scor4);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });seekbar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar5.setProgress(scor5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbar5.setProgress(scor5);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });seekbar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar6.setProgress(scor6);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbar6.setProgress(scor6);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });seekbar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar7.setProgress(scor7);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbar7.setProgress(scor7);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });seekbar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekbar8.setProgress(scor8);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekbar8.setProgress(scor8);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        String name = prefs.getString("name", " ");
        text1.setText(name);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("name", text1.getText().toString());
                editor.apply();
                Toast.makeText(Account.this, "Veriler kaydedildi", Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(Account.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account.this, Search.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
