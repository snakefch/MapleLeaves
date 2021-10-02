package com.example.mapleleaves.utils

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mapleleaves.R

object Notifier {

    private const val channelId="MapleLeaves"

    fun init(activity:Activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationManager=
                activity.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            val existingChannel=notificationManager.getNotificationChannel(channelId)
            //手动判断通知渠道是否已创建，但第一行代码第三版359页上说系统也会检查通知渠道是否已经存在了，
            //不会重复创建，也并不会影响运行效率，哪种方式更好？
            if (existingChannel==null){
                val channel=NotificationChannel(channelId,"消息推送",NotificationManager.IMPORTANCE_DEFAULT)
                channel.description="Notifications from MapleLeaves"
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun postNotification(id:Long,context: Context,intent:PendingIntent?){
        val notification=NotificationCompat.Builder(context, channelId)
            .setContentTitle("通知标题")
            .setContentText("成功创建新课程")
            .setSmallIcon(R.drawable.home_message_on_ic)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.drawable.blue_fish))
            //.setContentIntent(intent)
            .setAutoCancel(true)
            .build()
        val notificationManager=NotificationManagerCompat.from(context)
        notificationManager.cancelAll()
        notificationManager.notify(id.toInt(),notification)
    }
}