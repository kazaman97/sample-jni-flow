//
// Created by kazaman97 on 12/13/20.
//

#include <jni.h>
#include <string>
#include "timer.h"

using namespace std;

#include "android/log.h"

#define LOGE(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jstring JNICALL
Java_world_kazaman_timer_1library_TimerLibrary_helloWorld(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello World";

    return env->NewStringUTF(hello.c_str());
}
