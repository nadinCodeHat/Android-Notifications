package com.example.androidnotifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String CHANNEL_ID1 = "basic notification channel";
    private final String CHANNEL_ID2 = "heads up notification channel";
    private final String CHANNEL_ID3 = "Expandable notification channel";
    private final int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void basicNotify(View view) {
        createNotificationChannel(CHANNEL_ID1);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID1)
            .setSmallIcon(R.drawable.ic_notification_message)
            .setContentTitle("Basic Notification")
            .setContentText("This is a Basic Notification...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void headsUpNotify(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel02", "name",
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("description");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, "channel02")
                .setSmallIcon(R.drawable.ic_notification_important)
                .setContentTitle("Heads-Up Notification")
                .setContentText("This is a Heads-Up Notification...")
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)   // heads-up
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notification);
    }

    public void expandableNotify(View view) {
        createNotificationChannel(CHANNEL_ID3);
        NotificationCompat.Builder builder =  new NotificationCompat.Builder(this, CHANNEL_ID3)
            .setSmallIcon(R.drawable.ic_post)
            .setContentTitle("Expandable Notification")
            .setContentText("This is an Expandable Notification, Tap to view post")
            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.image_post))
            .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.image_post)).bigLargeIcon(null))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel(String CHANNEL) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}