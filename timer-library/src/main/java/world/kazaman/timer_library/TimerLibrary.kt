package world.kazaman.timer_library

import android.util.Log

class TimerLibrary {
    companion object {
        private val TAG = TimerLibrary::class.java.simpleName

        init {
            System.loadLibrary("timer")
        }
    }

    fun currentTime(time: Int) {
        Log.d(TAG, "currentTime: $time")
    }

    external fun helloWorld(): String
    external fun startTimer(time: Int)
    external fun stopTimer()
}
