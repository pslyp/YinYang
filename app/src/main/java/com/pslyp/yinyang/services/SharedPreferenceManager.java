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

    public void putBoolean(String key, Boolean b) {
        this.editor.putBoolean(key, b);
    }

    public void putString(String key, String value) {
        this.editor.putString(key, value);
    }

    public void clear() {
        this.editor.clear();
    }

    public void commit() {
        this.editor.commit();
    }

}
