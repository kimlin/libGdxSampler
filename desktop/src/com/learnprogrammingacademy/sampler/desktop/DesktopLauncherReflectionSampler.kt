package com.learnprogrammingacademy.sampler.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.learnprogrammingacademy.sampler.ReflectionSample

fun main(args: Array<String>) {
    val config = LwjglApplicationConfiguration()
    config.width = 1080
    config.height = 720

    LwjglApplication(ReflectionSample(), config)
}