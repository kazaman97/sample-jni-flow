//
// Created by kazaman97 on 12/13/20.
//

#include <jni.h>
#include <string>
#include <chrono>
#include "countdown.h"

using namespace std;

#include "android/log.h"

#define LOGE(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

static bool g_running_timer = false;
static JNIEnv *g_env;
static jobject g_thiz;

void update_current_time(int current_time) {
    auto clazz = g_env->FindClass("world/kazaman/countdown/CountDown");
    auto methodID = g_env->GetMethodID(clazz, "onUpdateCurrentTime", "(I)V");
    if (methodID != nullptr) {
        g_env->CallVoidMethod(g_thiz, methodID, current_time);
    }
    g_env->DeleteLocalRef(clazz);
}

void countdown(int time) {
    g_running_timer = true;
    auto started_at = std::chrono::system_clock::now();
    auto started_at_c = std::chrono::system_clock::to_time_t(started_at);

    while (g_running_timer) {
        auto now = std::chrono::system_clock::now();
        auto now_c = std::chrono::system_clock::to_time_t(now);
        auto diff_time = now_c - started_at_c;
        auto current_time = time - diff_time;
        if (current_time < 0) {
            g_running_timer = false;
        } else {
            update_current_time(current_time);
        }
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_world_kazaman_countdown_CountDown_start(JNIEnv *env, jobject thiz, jint time) {
    g_env = env;
    g_thiz = thiz;
    countdown(time);
}

extern "C"
JNIEXPORT void JNICALL
Java_world_kazaman_countdown_CountDown_stop(JNIEnv *env, jobject thiz) {
    g_running_timer = false;
}
