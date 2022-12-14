package mao.android_notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
//        {
//            NotificationChannel notificationChannel = new NotificationChannel("1", "测试通知", NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//        PendingIntent pendingIntent = PendingIntent.
//                getActivity(this, 0, new Intent(MainActivity.this, MainActivity2.class), 0);
//
//        Notification notification = new NotificationCompat.Builder(this, "1")
//                .setContentTitle("标题")
//                .setContentText("内容")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setColor(Color.rgb(255, 0, 0))
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//                .build();
//
//        notificationManager.notify(1, notification);

        createNotificationChannel("2", "测试通知");

        findViewById(R.id.Button1).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendBaseNotification("2", new Random().nextInt(100000), "标题", "内容");
            }
        });
        findViewById(R.id.Button2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendBaseNotification("2", new Random().nextInt(100000), "标题", "内容", MainActivity2.class);
            }
        });
        findViewById(R.id.Button3).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendBaseNotification("2", 1, "标题", "内容", MainActivity2.class);
            }
        });
        findViewById(R.id.Button4).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);
                sendNotification("2", new Random().nextInt(100000), "标题", "内容",
                        MainActivity2.class, R.drawable.ic_launcher_foreground, bitmap);
            }
        });
    }


    /**
     * 创建通知渠道，通知的重要程度默认为NotificationManager.IMPORTANCE_HIGH
     *
     * @param id   id
     * @param name 名字
     */
    private void createNotificationChannel(String id, String name)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /**
     * 创建通知渠道
     *
     * @param id    id
     * @param name  名字
     * @param level 通知水平,比如：NotificationManager.IMPORTANCE_HIGH
     */
    private void createNotificationChannel(String id, String name, int level)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(id, name, level);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }


    /**
     * 发送基本通知
     *
     * @param channelId 通道标识
     * @param id        id
     * @param title     标题
     * @param content   内容
     */
    private void sendBaseNotification(String channelId, int id, String title, String content)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(id, notification);
    }

    /**
     * 发送基本通知
     *
     * @param channelId 通道标识
     * @param id        id
     * @param title     标题
     * @param content   内容
     * @param cls       点击后要跳转到的Activity类
     */
    private void sendBaseNotification(String channelId, int id, String title, String content, Class<?> cls)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.
                getActivity(this, 0,
                        new Intent(this, cls), 0);
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(id, notification);
    }


    /**
     * 发送通知
     *
     * @param channelId 通道标识
     * @param id        id
     * @param title     标题
     * @param content   内容
     * @param cls       点击后要跳转到的Activity类
     * @param smallIcon 小图标
     * @param largeIcon 大图标
     */
    private void sendNotification(String channelId, int id, String title, String content,
                                  Class<?> cls, int smallIcon, Bitmap largeIcon)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.
                getActivity(this, 0,
                        new Intent(this, cls), 0);
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(smallIcon)
                .setLargeIcon(largeIcon)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(id, notification);
    }
}