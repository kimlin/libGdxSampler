package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.clearScreen
import com.learnprogrammingacademy.sampler.utils.isKeyPressed
import com.learnprogrammingacademy.sampler.utils.logger
import com.learnprogrammingacademy.sampler.utils.toInternalFile

class OrthographicCameraSample : SampleBase() {

    companion object {
        @JvmStatic
        private val logger = logger<OrthographicCameraSample>()

        private const val WORLD_WIDTH = 10.8f // world units
        private const val WORLD_HEIGHT = 7.2f // world units
        private const val CAMERA_SPEED = 2.0f // world units
        private const val CAMERA_ZOOM_SPEED = 2.0f // world units
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        logger.debug("create() is called")

        camera = OrthographicCamera()
        viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
        batch = SpriteBatch()
        texture = Texture("raw/level-bg.png".toInternalFile())
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        // clear screen
        clearScreen()

        queryInput()

        // setting projectionMatrix. Batch is used to draw the images
        // through projectionMatrix we are telling batch about the camera position, zoom and rotation (combined)
        // according to our camera settings the batch will know how to draw everything on the screen
        batch.projectionMatrix = camera.combined
        batch.begin()

        draw()

        batch.end()
    }

    private fun queryInput() {
        val deltaTime = Gdx.graphics.deltaTime

        when {
            Input.Keys.LEFT.isKeyPressed() -> camera.position.x -= CAMERA_SPEED * deltaTime
            Input.Keys.RIGHT.isKeyPressed() -> camera.position.x += CAMERA_SPEED * deltaTime
            Input.Keys.UP.isKeyPressed() -> camera.position.y += CAMERA_SPEED * deltaTime
            Input.Keys.DOWN.isKeyPressed() -> camera.position.y -= CAMERA_SPEED * deltaTime
            Input.Keys.PAGE_UP.isKeyPressed() -> camera.zoom -= CAMERA_ZOOM_SPEED * deltaTime
            Input.Keys.PAGE_DOWN.isKeyPressed() -> camera.zoom += CAMERA_ZOOM_SPEED * deltaTime
            Input.Keys.ENTER.isKeyPressed() -> {
                logger.debug("position= ${camera.position}")
                logger.debug("zoom= ${camera.zoom}")
            }
        }

        camera.update()
    }

    private fun draw() {
        batch.draw(texture, 0f, 0f, WORLD_WIDTH, WORLD_HEIGHT)
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }
}