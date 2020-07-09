package turk.isaret.dili.egitimi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    Thread objectThread;
    ImageView view;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startSplash();

    }

    private void startSplash() {

        try {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
            animation.reset();

            view = (ImageView)findViewById(R.id.view);
            layout = (LinearLayout)findViewById(R.id.layout);
            layout.clearAnimation();
            layout.startAnimation(animation);


            objectThread = new Thread() {

                @Override
                public void run() {
                    int parseInt = 0;
                    while (parseInt < 500) {
                        try {
                            sleep(650);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        parseInt += 100;
                    }

                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
            objectThread.start();
        } catch (Exception e) {

            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }


}



