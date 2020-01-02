package com.learnprogrammingacademy.sampler.desktop

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.badlogic.gdx.utils.reflect.ClassReflection
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

class GdxSamplerLauncher : JFrame() {

    private val windowSize = Dimension(1280, 720)

    private var lwjglAWTCanvas : LwjglAWTCanvas? = null

    init {
        title = this::class.java.simpleName
        minimumSize = windowSize
        size = windowSize
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        launchSample("com.learnprogrammingacademy.sampler.InputPollingSample")

        // tell window/jframe to resize and layout component
        pack()
        isVisible = true
    }

    private fun launchSample(name: String) {
        println("launching name= $name")

        // cleanup before running new sample
        lwjglAWTCanvas?.stop()
        if (lwjglAWTCanvas != null) {
            contentPane.remove(lwjglAWTCanvas?.canvas)
        }

        // get class object by name
        val sampleClass = ClassReflection.forName(name)

        // create new instance of sample class
        val sample = ClassReflection.newInstance(sampleClass) as ApplicationListener

        lwjglAWTCanvas = LwjglAWTCanvas(sample)
        lwjglAWTCanvas?.canvas?.size = windowSize
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        contentPane.add(lwjglAWTCanvas?.canvas, BorderLayout.CENTER)

    }
}

// == main ==
fun main(args: Array<String>) {
    SwingUtilities.invokeLater { GdxSamplerLauncher() }
}