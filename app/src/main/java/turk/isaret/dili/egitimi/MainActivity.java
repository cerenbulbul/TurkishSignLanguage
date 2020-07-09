package turk.isaret.dili.egitimi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text1;
    BottomNavigationView b;
    ImageView star1 , star2, star3, star4, star5, star6, star7, star8;
    CardView cardView1;
    CardView cardView2;
    CardView cardView3;
    CardView cardView4;
    CardView cardView5;
    CardView cardView6;
    CardView cardView7;
    CardView cardView8;
    CardView cardCumle1;
    CardView cardCumle2;
    CardView esHareketli1;
    private static int dersID;
    public static final String MY_PREFS_NAME = "userProfile";
    SharedPreferences prefs;
    int scor1, scor2, scor3, scor4, scor5, scor6, scor7, scor8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        text1 = (TextView) findViewById(R.id.text1);
        cardView1 = (CardView) findViewById(R.id.cardView1);
        cardView2 = (CardView) findViewById(R.id.cardView2);
        cardView3 = (CardView) findViewById(R.id.cardView3);
        cardView4 = (CardView) findViewById(R.id.cardView4);
        cardView5 = (CardView) findViewById(R.id.cardView5);
        cardView6 = (CardView) findViewById(R.id.cardView6);
        cardView7 = (CardView) findViewById(R.id.cardView7);
        cardView8 = (CardView) findViewById(R.id.cardView8);
        cardCumle1 = (CardView) findViewById(R.id.cardCumle1);
        cardCumle2 = (CardView) findViewById(R.id.cardCumle2);
        esHareketli1 = (CardView)findViewById(R.id.esHareketli1);

        star1 = (ImageView)findViewById(R.id.star1);
        star2 = (ImageView)findViewById(R.id.star2);
        star3 = (ImageView)findViewById(R.id.star3);
        star4 = (ImageView)findViewById(R.id.star4);
        star5 = (ImageView)findViewById(R.id.star5);
        star6 = (ImageView)findViewById(R.id.star6);
        star7 = (ImageView)findViewById(R.id.star7);
        star8 = (ImageView)findViewById(R.id.star8);

        BottomNavigationItemView ev = (BottomNavigationItemView) findViewById(R.id.ev);
        BottomNavigationItemView myAccount = (BottomNavigationItemView) findViewById(R.id.profil);
        BottomNavigationItemView search = (BottomNavigationItemView) findViewById(R.id.search);
        b = findViewById(R.id.bottom_navigaitonview);
        b.setSelectedItemId(R.id.ev);

        String name = prefs.getString("name", "");
        text1.setText("MERHABA " + name);


        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);
        cardView6.setOnClickListener(this);
        cardView7.setOnClickListener(this);
        cardView8.setOnClickListener(this);

        scor1= prefs.getInt("score1", 0);
        scor2= prefs.getInt("score2", 0);
        scor3= prefs.getInt("score3", 0);
        scor4= prefs.getInt("score4", 0);
        scor5= prefs.getInt("score5", 0);
        scor6= prefs.getInt("score6", 0);
        scor7= prefs.getInt("score7", 0);
        scor8= prefs.getInt("score8", 0);

        starCheck(star1,scor1);
        starCheck(star2,scor2);
        starCheck(star3,scor3);
        starCheck(star4,scor4);
        starCheck(star5,scor5);
        starCheck(star6,scor6);
        starCheck(star7,scor7);
        starCheck(star8,scor8);

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Account.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        cardCumle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dersID = 9;

                Intent intent = new Intent(MainActivity.this, lesson2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cardCumle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dersID =10;

                Intent intent = new Intent(MainActivity.this, lesson2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        esHareketli1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dersID=11;

                Intent intent = new Intent(MainActivity.this, lesson2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cardView1:
                dersID = 1;
                break;
            case R.id.cardView2:
                dersID = 2;
                break;
            case R.id.cardView3:
                dersID = 3;
                break;
            case R.id.cardView4:
                dersID = 4;
                break;
            case R.id.cardView5:
                dersID = 5;
                break;
            case R.id.cardView6:
                dersID = 6;
                break;
            case R.id.cardView7:
                dersID = 7;
                break;
            case R.id.cardView8:
                dersID = 8;
                break;
//            case R.id.cardCumle1:
//                dersID = 9;
//                break;
//            case R.id.cardCumle2:
//                dersID =10;
//                break;
//            case R.id.esHareketli1:
//                dersID=11;
//                break;
        }

        Intent intent = new Intent(MainActivity.this, Lesson.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void starCheck(View view, int scor){
        if (scor<=50) {
            view.setBackground(this.getResources().getDrawable(R.drawable.notstar));
        }
        else if(scor>50) {
            view.setBackground(this.getResources().getDrawable(R.drawable.star));
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Uygulama Kapatılıyor ...")
                .setMessage("Uygulamayı Kapatmak İstediğine Emin Misin?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Hayır", null)
                .show();
    }

   public static int getDersID(){
       return dersID;
   }

}