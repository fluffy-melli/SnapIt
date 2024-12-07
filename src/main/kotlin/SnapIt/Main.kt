package SnapIt

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import SnapIt.display.createResize

fun init() {
    try {
        GlobalScreen.registerNativeHook()
        GlobalScreen.addNativeKeyListener(object : NativeKeyListener {
            override fun nativeKeyPressed(event: NativeKeyEvent) {
                if (event.keyCode == NativeKeyEvent.VC_F12) {
                    createResize()
                }
            }
        })
    } catch (e: NativeHookException) {
        e.printStackTrace()
    }
}

fun main() {
    println("You can take a screenshot by pressing 'F12'")
    init()
}
