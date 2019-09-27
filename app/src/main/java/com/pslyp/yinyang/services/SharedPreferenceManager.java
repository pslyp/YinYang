package com.pslyp.yinyang.services;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static Context mContext;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

//    private String name;
//    private int mode;

    private SharedPreferenceManager(SharedPreferenceManager.Builder builder) {
        this.sp = mContext.getSharedPreferences(builder.name, builder.mode);
    }

    public static class Builder {
        private String name;
        private int mode;

        public Builder(Context context) {
            SharedPreferenceManager.mContext = context;
        }

        public SharedPreferenceManager.Builder name(String name) {
            this.name = name;
            return this;
        }

        public SharedPreferenceManager.Builder mode(int mode) {
            this.mode = mode;
            return this;
        }

        public SharedPreferenceManager build() {
            return new SharedPreferenceManager(this);
        }
    }

    public void edit() {
        this.editor = sp.edit();
    }

    public void putFloat(String key, Float f) {
        this.editor.putFloat(key, f);
    }

    public void putInt(String key, int i) {
        this.editor.putInt(key, i);
    }

    public void putLong(String key, Long l) {
        this.editor.putLong(key, l);
    }

    public void putString(String key, String value) {
        this.editor.putString(key, value);
    }

    //    public void putStringSet(String key) {
//        this.editor.putStringSet();
//    }

    public void putBoolean(String key, Boolean b) {
        this.editor.putBoolean(key, b);
    }

    public Float getFloat(String key, Float f) {
        return this.sp.getFloat(key, f);
    }

    public Integer getInt(String key, Integer i) {
        return this.sp.getInt(key, i);
    }

    public Long getLong(String key, Long l) {
        return this.getLong(key, l);
    }

    public String getString(String key, String value) {
        return this.sp.getString(key, value);
    }

    public Boolean getBoolean(String key, Boolean b) {
        return this.sp.getBoolean(key, b);
    }

    public void clear() {
        this.editor.clear();
    }

    public void commit() {
        this.editor.commit();
    }

}
