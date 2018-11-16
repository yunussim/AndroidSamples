package yunussimulya.gmail.com.mysample.common;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

public class NotificationHandler {

    public static Notification createNotification(Context context, PendingIntent pendingIntent, @NonNull String title,
                                                  @NonNull String message, int smallIcon, Bitmap largeIcon,
                                                  @NonNull NotificationManager notificationManager, String channelId,
                                                  String channelName) {
        NotificationCompat.Builder mNotifyBuilder;
        mNotifyBuilder = new NotificationCompat.Builder(context, "WISE")
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(smallIcon)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (largeIcon != null) mNotifyBuilder.setLargeIcon(largeIcon);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            mNotifyBuilder.setChannelId(channelId);
        }
        return mNotifyBuilder.build();
    }
}
