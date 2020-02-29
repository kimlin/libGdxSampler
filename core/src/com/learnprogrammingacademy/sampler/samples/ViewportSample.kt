package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ArrayMap
import com.badlogic.gdx.utils.viewport.*
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.clearScreen
import com.learnprogrammingacademy.sampler.utils.logger
import com.learnprogrammingacademy.sampler.utils.toInternalFile
import com.learnprogrammingacademy.sampler.utils.use

class ViewportSample : SampleBase() {

    companion object {
        @JvmStatic
        private val logger = logger<ViewportSample>()

        private const val WORLD_WIDTH = 1080f // world units -> 1 world unit = 1 pixel
        private const val WORLD_HEIGHT = 720f // world units -> 1 world unit = 1 pixel
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var currentViewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture
    private lateinit var font: BitmapFont

    private val viewports = ArrayMap<String, Viewport>()
    private var currentViewportIndex = -1
    private var currentViewportName = ""


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        logger.debug("create() is called")

        camera = OrthographicCamera()
        batch = SpriteBatch()
        texture = Texture("raw/level-bg.png".toInternalFile())
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

        createViewports()
        selectNextViewport()

        Gdx.input.inputProcessor = this
    }

    private fun createViewports() {
        viewports.put(StretchViewport::class.java.simpleName, StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)) // fill it up, weird aspect ratio
        viewports.put(FitViewport::class.java.simpleName, FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)) // Auto, black bars
        viewports.put(FillViewport::class.java.simpleName, FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)) // fill it up, bad cropping
        viewports.put(ScreenViewport::class.java.simpleName, ScreenViewport(camera)) // One type of screen
        viewports.put(ExtendViewport::class.java.simpleName, ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)) // Auto, black bars
    }

    private fun selectNextViewport() {
        currentViewportIndex = (currentViewportIndex + 1) % viewports.size

        currentViewport = viewports.getValueAt(currentViewportIndex)
        resize(Gdx.graphics.width, Gdx.graphics.height)
        currentViewportName = viewports.getKeyAt(currentViewportIndex)

        logger.debug("selected Viewport= $currentViewportName")
    }


    override fun resize(width: Int, height: Int) {
        currentViewport.update(width, height, true)
    }

    override fun render() {
        // clear screen
        clearScreen()


        batch.projectionMatrix = camera.combined
        batch.use { draw() }
    }

    private fun draw() {
        batch.draw(texture, 0f, 0f, WORLD_WIDTH, WORLD_HEIGHT)
        font.draw(batch, currentViewportName, 50f, 100f)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        selectNextViewport()
        return false
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }
}