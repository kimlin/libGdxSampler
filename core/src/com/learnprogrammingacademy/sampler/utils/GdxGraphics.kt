package com.learnprogrammingacademy.sampler.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20

// JvmOverloads creates the option of using clearScreen method with default parameter in JAVA
@JvmOverloads
fun clearScreen(color: Color = Color.BLACK) = clearScreen(color.r, color.g, color.b, color.a)

fun clearScreen(red: Float, green: Float, blue: Float, alpha: Float) {
    // DRY - Don't repeat yourself
    // WET - Waste everyone's time
    Gdx.gl.glClearColor(red, green, blue, alpha)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
}