package com.swapniljain.jinshashan;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;
import com.swapniljain.jinshashan.activity.JNListActivity;

/**
 * Implementation of App Widget functionality.
 */
public class JNAppWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context,
                                AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Read data from shared preference.
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(JNListActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String title = sharedPreferences.getString(JNListActivity.WIDGET_TITLE,
                "Open the app to login");
        String subTitle = sharedPreferences.getString(JNListActivity.WIDGET_SUBTITLE,
                "Open the app to login");
        String imageURL = sharedPreferences.getString(JNListActivity.WIDGET_HERO_IMAGE_URL,
                "");

        // Construct the RemoteViews object
        RemoteViews views =
                new RemoteViews(context.getPackageName(), R.layout.jnapp_widget_provider);
        views.setTextViewText(R.id.app_widget_title, title);
        views.setTextViewText(R.id.app_widget_subtitle, subTitle);
        //views.setImageViewResource(R.id.app_widget_hero_image, R.drawable.card_placeholder_02);

        Picasso.get()
                .load(imageURL)
                .into(views, R.id.app_widget_hero_image, new int[]{appWidgetId});

        // Create an Intent to launch MainActivity when clicked.
        Intent intent = new Intent(context, JNListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Widget allow click handlers to only launch pending intent.
        views.setOnClickPendingIntent(R.id.app_widget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
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

