package world.kazaman.countdown

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class CountDown {
    companion object {
        private val TAG = CountDown::class.java.simpleName

        init {
            System.loadLibrary("countdown")
        }
    }

    private val _currentTime = MutableStateFlow(0)
    val currentTime = _currentTime

    private fun onUpdateTime(time: Int) {
        _currentTime.value = time
    }

    external fun start(time: Int)
    external fun stop()
}
