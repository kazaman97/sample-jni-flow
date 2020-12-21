package world.kazaman.countdown

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class CountDown {
    companion object {
        private val TAG = CountDown::class.java.simpleName

        init {
            System.loadLibrary("countdown")
        }
    }

    private val _currentTime = MutableStateFlow(0)
    val currentTime: StateFlow<Int> = _currentTime

    private fun onUpdateCurrentTime(currentTime: Int) {
        _currentTime.value = currentTime
    }

    external fun start(time: Int)
    external fun stop()
}
