package com.manan.doorunlocker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.manan.doorunlocker.MainActivity;

import android.hardware.camera2.params.MandatoryStreamCombination;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class DoorButtonWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews remoteV = new RemoteViews(context.getPackageName(), R.layout.door_button_widget);
        Intent intentSync = new Intent(context, DoorButtonWidget.class);
        intentSync.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE); //You need to specify the action for the intent. Right now that intent is doing nothing for there is no action to be broadcasted.
        PendingIntent pendingSync = PendingIntent.getBroadcast(context,0, intentSync, PendingIntent.FLAG_UPDATE_CURRENT); //You need to specify a proper flag for the intent. Or else the intent will become deleted.
        remoteV.setOnClickPendingIntent(R.id.OpenButton,pendingSync);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteV);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Toast.makeText(context, "Widget has been updated! ", Toast.LENGTH_SHORT).show();

            //BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAYCG5WKDQAVEQLYMD", "EfPD9jZ9JHWoOwQsvVIxYV6zvu8hxKUEenRlivI8");
            //AmazonS3 s3 = new AmazonS3Client(awsCreds);
            //s3.deleteObject("dooropener", "example2.txt");
            //CopyObjectRequest copyObjRequest = new CopyObjectRequest("dooropener", "example.txt", "dooropener", "example2.txt");
            //s3.copyObject(copyObjRequest);
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }



    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

