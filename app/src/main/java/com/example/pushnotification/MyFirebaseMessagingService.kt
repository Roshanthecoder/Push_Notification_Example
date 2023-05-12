package com.example.pushnotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelId="notification_Channel"
const val channelName="com.example.pushnotification"

class MyFirebaseMessagingService: FirebaseMessagingService() {



    //generate the notification
    //attach the notification created with the custom layout
    //show the notification


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
       if(remoteMessage.notification !=null){
           generatenotification(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)
       }
    }


     @SuppressLint("SuspiciousIndentation")
     fun getRemoteView(title: String, message: String): RemoteViews {
       val remoteViews=RemoteViews("com.example.pushnotification",R.layout.push_layout)
         remoteViews.setTextViewText(R.id.notification_message1,title)
         remoteViews.setTextViewText(R.id.message,message)
         remoteViews.setImageViewResource(R.id.notification_icon,R.drawable.frame)
         return remoteViews
     }


fun generatenotification(title: String,message:String) {
    val intent = Intent(this, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

    val pendingIntent = PendingIntent.getActivity(
        this,
        0,
        intent,
        PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
    )

    //channel id and channel name

    val builder: NotificationCompat.Builder =
        NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.frame)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

    builder.setContent(getRemoteView(title,message))
    val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
        val notificationChannel=NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
    }
    notificationManager.notify(0,builder.build())

}

}