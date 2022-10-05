package mao.android_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

/**
 * Project name(项目名称)：android_Notification
 * Package(包名): mao.android_notification
 * Class(类名): NotificationHelper
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/10/5
 * Time(创建时间)： 19:46
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class NotificationHelper
{
    /**
     * 发布通知
     *
     * @param context      上下文
     * @param notifyId     通知标识id
     * @param iconResId    显示的icon的id
     * @param textResId    显示的文字的id
     * @param soundResId   声音 - 没有使用（可以自己加）
     * @param titleResId   打开通知抽屉后的标题的id
     * @param contentResId 打开通知抽屉后的内容的id
     * @param cls          点击后打开的类
     * @param flag         通知标签
     * @return 返回Notification对象
     */
    static public Notification notify(Context context, int notifyId, int iconResId,
                                      int textResId, int soundResId, int titleResId, int contentResId,
                                      Class<?> cls, int flag)
    {
        final Resources res = context.getResources();

        return notify(context, notifyId, iconResId, res.getString(textResId), soundResId,
                res.getString(titleResId), res.getString(contentResId), cls,
                flag);
    }

    /**
     * 发布通知
     *
     * @param context        上下文
     * @param notifyId       通知标识id
     * @param iconResId      显示的icon的id
     * @param notifyShowText 显示的文字
     * @param soundResId     声音 - 没有使用（可以自己加）
     * @param titleText      打开通知抽屉后的标题
     * @param contentText    打开通知抽屉后的内容
     * @param cls            点击后打开的类
     * @param flag           通知标签
     * @return 返回Notification对象
     */
    static public Notification notify(Context context, int notifyId, int iconResId,
                                      String notifyShowText, int soundResId, String titleText,
                                      String contentText, Class<?> cls, int flag)
    {

        Notification n = genNotification(context, notifyId, iconResId, notifyShowText,
                soundResId, titleText, contentText, cls, flag);

        // 显示通知
        notify(context, notifyId, n);

        return n;
    }

    /**
     * 发布通知
     *
     * @param context      上下文
     * @param notifyId     通知标识id
     * @param notification 通知对象
     */
    static public void notify(Context context, int notifyId, Notification notification)
    {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // 显示通知
        nm.notify(notifyId, notification);
    }

    /**
     * 生成Notification对象
     *
     * @param context            上下文
     * @param notifyId     通知标识id
     * @param iconResId    显示的icon的id
     * @param textResId    显示的文字的id
     * @param soundResId   声音 - 没有使用（可以自己加）
     * @param titleResId   打开通知抽屉后的标题的id
     * @param contentResId 打开通知抽屉后的内容的id
     * @param cls          点击后打开的类
     * @param flag         通知标签
     * @return 返回Notification对象
     */
    static public Notification genNotification(Context context, int notifyId, int iconResId,
                                               int textResId, int soundResId, int titleResId, int contentResId,
                                               Class<?> cls, int flag)
    {
        final Resources res = context.getResources();

        return genNotification(context, notifyId, iconResId, res.getString(textResId), soundResId,
                res.getString(titleResId), res.getString(contentResId), cls,
                flag);
    }

    /**
     * 生成Notification对象
     *
     * @param context        上下文
     * @param notifyId       通知标识id
     * @param iconResId      显示的icon的id
     * @param notifyShowText 显示的文字
     * @param soundResId     声音 - 没有使用（可以自己加）
     * @param titleText      打开通知抽屉后的标题
     * @param contentText    打开通知抽屉后的内容
     * @param cls            点击后打开的类
     * @param flag           通知标签
     * @return 返回Notification对象
     */
    static public Notification genNotification(Context context, int notifyId, int iconResId,
                                               String notifyShowText, int soundResId, String titleText,
                                               String contentText, Class<?> cls, int flag)
    {

        Intent intent = null;
        if (cls != null)
        {
            intent = new Intent(context, cls);
        }

        final Notification notification = new Notification();

        // 控制点击通知后显示内容的类
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,  // requestCode  现在是没有使用的，所以任意值都可以
                intent,
                0 // PendingIntent的flag，在update这个通知的时候可以加特别的flag
        );
        // 设置通知图标
        notification.icon = iconResId;
        // 通知文字
        notification.tickerText = notifyShowText;
        // 通知发出的标志设置
        notification.flags = flag;
        // 设置通知参数
        //notification.setLatestEventInfo(context, titleText, contentText, pendingIntent);

        return notification;
    }

    /**
     * 取消消息
     *
     * @param context  上下文
     * @param notifyId 通知id
     */
    public static void cancel(Context context, int notifyId)
    {
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).cancel(notifyId);
    }

    // flags
    final static public int FLAG_ONGOING_EVENT_AUTO_CANCEL = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONGOING_EVENT;
    final static public int FLAG_ONGOING_EVENT = Notification.FLAG_ONGOING_EVENT;
    final static public int FLAG_NO_CLEAR = Notification.FLAG_NO_CLEAR;
    final static public int FLAG_AUTO_CANCEL = Notification.FLAG_AUTO_CANCEL;
}
