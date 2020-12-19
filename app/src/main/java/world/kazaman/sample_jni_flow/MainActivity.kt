package world.kazaman.sample_jni_flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import world.kazaman.countdown.CountDown
import world.kazaman.sample_jni_flow.databinding.ActivityMainBinding

@ExperimentalCoroutinesApi
internal class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val countDown = CountDown()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.startButton.setOnClickListener { view ->
            lifecycleScope.launchWhenStarted {
                view.isEnabled = false
                start()
                view.isEnabled = true
            }
        }

        binding.stopButton.setOnClickListener {
            binding.startButton.isEnabled = true
            countDown.stop()
        }

        lifecycleScope.launch {
            countDown.currentTime.collect {
                binding.currentTime.text = it.toString()
            }
        }
    }

    private suspend fun start() {
        withContext(Dispatchers.IO) {
            countDown.start(binding.setTimer.text.toString().toIntOrNull() ?: 0)
        }
    }
}
