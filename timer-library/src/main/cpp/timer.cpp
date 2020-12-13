//
// Created by kazaman97 on 12/13/20.
//

#include <jni.h>
#include <string>
#include <chrono>
#include "timer.h"

using namespace std;

#include "android/log.h"

#define LOGE(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

static bool g_running_timer = false;
static JNIEnv *g_env;
static jobject g_thiz;

void currentTIme(int time) {
    auto clazz = g_env->FindClass("world/kazaman/timer_library/TimerLibrary");
    auto methodID = g_env->GetMethodID(clazz, "currentTime", "(I)V");
    if (methodID != nullptr) {
        g_env->CallVoidMethod(g_thiz, methodID, time);
    }
    g_env->DeleteLocalRef(clazz);
}

void timer(int time) {
    g_running_timer = true;
    auto started_at = std::chrono::system_clock::now();
    auto started_at_c = std::chrono::system_clock::to_time_t(started_at);

    while (g_running_timer) {
        auto now = std::chrono::system_clock::now();
        auto now_c = std::chrono::system_clock::to_time_t(now);
        auto current_time = time - (now_c - started_at_c);
        currentTIme(current_time);
    }
}

extern "C"
JNIEXPORT jstring JNICALL
Java_world_kazaman_timer_1library_TimerLibrary_helloWorld(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello World";

    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_world_kazaman_timer_1library_TimerLibrary_startTimer(JNIEnv *env, jobject thiz, jint time) {
    g_env = env;
    g_thiz = thiz;
    timer(time);
}

extern "C"
JNIEXPORT void JNICALL
Java_world_kazaman_timer_1library_TimerLibrary_stopTimer(JNIEnv *env, jobject thiz) {
    g_running_timer = false;
}
