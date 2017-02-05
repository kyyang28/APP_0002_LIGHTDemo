package com.young.app_0002_lightdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int LED_NOTIFICATION_ID = 0;
    private static final int DELAY_TIME = 20000;
    private Button mLightButton = null;
    boolean isFlashing = false;

    private Handler mLightHandler = new Handler();

    private void FlashingLight() {
        NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
        Notification notif = new Notification();
        notif.flags = Notification.FLAG_SHOW_LIGHTS;
        notif.ledARGB = 0xFF0000FF;     // blue
        notif.ledOnMS = 100;
        notif.ledOffMS = 100;
        nm.notify(LED_NOTIFICATION_ID, notif);
    }

    private void ClearFlashingLight() {
        NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
        nm.cancel( LED_NOTIFICATION_ID );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLightButton = (Button) findViewById(R.id.button);
        mLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFlashing = !isFlashing;

                if (isFlashing) {
                    mLightButton.setText("Stop flashing the light");
                } else {
                    mLightButton.setText("Flashing light at 20s");
                }

                /* Send notification */
                mLightHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isFlashing) {
                            FlashingLight();
                        } else {
                            ClearFlashingLight();
                        }
                    }
                }, DELAY_TIME);

            }
        });
    }
}
