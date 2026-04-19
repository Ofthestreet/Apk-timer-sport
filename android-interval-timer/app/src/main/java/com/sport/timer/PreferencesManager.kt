package com.sport.timer

import android.content.Context

object PreferencesManager {
    private const val PREFS_NAME = "timer_prefs"
    private const val KEY_WORK_TIME = "default_work_time"
    private const val KEY_REST_TIME = "default_rest_time"
    private const val KEY_SERIES = "default_series"

    fun getDefaultWorkTime(context: Context): Int =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getInt(KEY_WORK_TIME, 60)

    fun getDefaultRestTime(context: Context): Int =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getInt(KEY_REST_TIME, 30)

    fun getDefaultSeries(context: Context): Int =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getInt(KEY_SERIES, 5)

    fun saveDefaults(context: Context, workTime: Int, restTime: Int, series: Int) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
            .putInt(KEY_WORK_TIME, workTime)
            .putInt(KEY_REST_TIME, restTime)
            .putInt(KEY_SERIES, series)
            .apply()
    }
}
