package com.andrea.com.bakingtime.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;

import com.andrea.com.bakingtime.Model.IngredientsTable;
import com.andrea.com.bakingtime.R;

/**
 * Implementation of App Widget functionality.
 */
public class BakingTimeWidget extends AppWidgetProvider {

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {

        String widgetText = getWidgetText(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_time_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    //Get list of ingredients from ContentProvider
    private static String getWidgetText(Context context){
        String ingredients = "";

        Cursor cursor = context.getContentResolver().query(IngredientsTable.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()){
            ingredients = ingredients +
                    cursor.getString(cursor.getColumnIndex(IngredientsTable.FIELD_COL_INGREDIENTS))
                    + ", ";
        }
        return ingredients;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
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

