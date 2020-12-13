package world.kazaman.sample_jni_flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import world.kazaman.sample_jni_flow.databinding.ActivityMainBinding
import world.kazaman.timer_library.TimerLibrary

internal class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val timerLibrary = TimerLibrary()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.currentTime.text = timerLibrary.helloWorld()

        // TODO Coroutineを導入してUIスレッドを止めないようにする
        timerLibrary.startTimer(60)
    }
}
