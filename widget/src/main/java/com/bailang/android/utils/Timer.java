package com.bailang.android.utils;

import android.os.Handler;

public class Timer {

    private static Timer instance = null;

    private TimerListener m_listener = null;

    private int m_updateTime = 0;
    private int m_count = 0;

    public static Timer getInstance() {
        if(instance == null) instance = new Timer();
        return instance;
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timerHandler.postDelayed(this, 1000);

            updateTick();
        }
    };

    public void start(TimerListener listener) {
        m_count = 0;
        m_updateTime = 0;
        m_listener = listener;
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public void start(int updateTime, TimerListener listener) {
        m_count = 0;
        m_updateTime = updateTime;
        m_listener = listener;
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public void stop() {
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void updateTick() {
        if(m_updateTime == 0) {
            m_listener.updateTick();
        }
        else {
            m_count++;
            if(m_updateTime == m_count) {
                m_count = 0;
                m_listener.updateTick();
            }
        }
    }

    public interface TimerListener {
        void updateTick();
    }
}
