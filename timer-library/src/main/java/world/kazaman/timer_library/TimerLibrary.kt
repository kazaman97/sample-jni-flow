package world.kazaman.timer_library

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class TimerLibrary {
    companion object {
        private val TAG = TimerLibrary::class.java.simpleName

        init {
            System.loadLibrary("timer")
        }
    }

    private val _currentTime = MutableStateFlow(0)
    val currentTime = _currentTime

    protected fun onUpdateTime(time: Int) {
        _currentTime.value = time
    }

    external fun startTimer(time: Int)
    external fun stopTimer()
}
