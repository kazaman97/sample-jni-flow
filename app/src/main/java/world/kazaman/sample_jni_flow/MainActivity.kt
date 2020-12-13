package world.kazaman.sample_jni_flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import world.kazaman.sample_jni_flow.databinding.ActivityMainBinding
import world.kazaman.timer_library.TimerLibrary

internal class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val timerLibrary = TimerLibrary()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.currentTime.text = timerLibrary.helloWorld()

        binding.startButton.setOnClickListener { view ->
            CoroutineScope(Dispatchers.Main).launch {
                view.isEnabled = false
                start()
                view.isEnabled = true
            }
        }

        binding.stopButton.setOnClickListener {
            binding.startButton.isEnabled = true
            timerLibrary.stopTimer()
        }
    }

    private suspend fun start() {
        withContext(Dispatchers.IO) {
            timerLibrary.startTimer(binding.setTimer.text.toString().toIntOrNull() ?: 0)
        }
    }
}
