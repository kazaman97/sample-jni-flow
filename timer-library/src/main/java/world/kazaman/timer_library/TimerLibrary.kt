package world.kazaman.timer_library

class TimerLibrary {
    companion object {
        init {
            System.loadLibrary("timer")
        }
    }

    external fun helloWorld(): String
}
