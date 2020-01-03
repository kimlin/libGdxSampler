package com.learnprogrammingacademy.sampler.desktop

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.learnprogrammingacademy.sampler.samples.ModuleInfoSample

fun main(args: Array<String>) {

    println("app= ${Gdx.app}")
    println("input= ${Gdx.input}")

    LwjglApplication(ModuleInfoSample(), LwjglApplicationConfiguration())

    println("app= ${Gdx.app}")
    println("input= ${Gdx.input}")
}