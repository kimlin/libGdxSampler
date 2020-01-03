package com.learnprogrammingacademy.sampler.desktop

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas
import com.badlogic.gdx.utils.reflect.ClassReflection
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class GdxSamplerLauncher : JFrame() {

    private val windowHeight = 720
    private val windowWidth = 1280
    private val windowSize = Dimension(windowWidth, windowHeight)
    private val cellWidth = 200
    private val canvasWidth = windowWidth - cellWidth

    private var lwjglAWTCanvas: LwjglAWTCanvas? = null
    private lateinit var sampleList: JList<String>

    init {
        title = this::class.java.simpleName
        minimumSize = windowSize
        size = windowSize
        isResizable = false
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

        createControlPanel()

        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                println("windowClosing")
                lwjglAWTCanvas?.stop()
            }
        })

        // tell window/jframe to resize and layout component
        pack()
        isVisible = true
    }

    private fun createControlPanel() {
        val controlPanel = JPanel(GridBagLayout())
        val c = GridBagConstraints()

        // scrollpane
        c.apply {
            gridx = 0 // column
            gridy = 0 // row
            fill = GridBagConstraints.VERTICAL // fill vertically
            weighty = 1.0
        }

        sampleList = JList(arrayOf("com.learnprogrammingacademy.sampler.samples.InputPollingSample"))
        sampleList.fixedCellWidth = cellWidth
        sampleList.selectionMode = ListSelectionModel.SINGLE_SELECTION

        // add double click to launch sample
        sampleList.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(mouseEvent: MouseEvent?) {
                if (mouseEvent?.clickCount == 2) {
                    launchSelectedSample()
                }
            }
        })

        val scrollPane = JScrollPane(sampleList)
        controlPanel.add(scrollPane, c)

        // button
        c.apply {
            gridx = 0 // column
            gridy = 1 // row
            fill = GridBagConstraints.HORIZONTAL
            weighty = 0.0
        }

        val launchButton = JButton("Launch Sample")
        launchButton.addActionListener { launchSelectedSample() }
        controlPanel.add(launchButton, c)


        // add to JFrame
        contentPane.add(controlPanel, BorderLayout.WEST)

    }

    private fun launchSelectedSample() {
        val sampleName: String? = sampleList.selectedValue

        if (sampleName.isNullOrBlank()) {
            println("sampleName is null or blank, can't launch")
            return
        }

        launchSample(sampleName)
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
        lwjglAWTCanvas?.canvas?.size = Dimension(canvasWidth, windowHeight)
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        contentPane.add(lwjglAWTCanvas?.canvas, BorderLayout.CENTER)
        pack()
    }
}

// == main ==
fun main(args: Array<String>) {
    SwingUtilities.invokeLater { GdxSamplerLauncher() }
}