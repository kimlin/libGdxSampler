package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.GdxArray
import com.learnprogrammingacademy.sampler.utils.clearScreen
import com.learnprogrammingacademy.sampler.utils.logger
import com.learnprogrammingacademy.sampler.utils.toInternalFile

class SpriteBatchSample : SampleBase() {

    companion object {
        @JvmStatic
        private val logger = logger<SpriteBatchSample>()
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var texture: Texture

    private val maxMessageCount = 15
    private val messages = GdxArray<String>()

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        logger.debug("create() is called")

        camera = OrthographicCamera()
        viewport = FitViewport(10.8f, 7.2f, camera)
        batch = SpriteBatch()
        texture = Texture("raw/character.png".toInternalFile())

        Gdx.input.inputProcessor = this
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        // clear screen
        clearScreen()

        batch.projectionMatrix = camera.combined
        batch.begin()

        draw()

        batch.end()
    }

    private fun draw() {

        batch.color = Color.WHITE
        val width = 1f // world units
        val height = 1f // world units

        batch.draw(texture,
                1f,
                1f,
                width / 2f,
                height / 2f,
                width,
                height,
                1f,
                1f,
                0f,
                0,
                0,
                texture.width,
                texture.height,
                false,
                false
        )

        // scaled
        batch.draw(texture, 4f, 2f,
                width / 2, height / 2,
                width, height,
                2f, 2f,
                0f,
                0, 0,
                texture.width, texture.height,
                true, false)

        // batch color
        val oldColor = batch.color
        batch.color = Color.GREEN

        batch.draw(texture, 8f, 1f,
                width / 2, height / 2,
                width, height,
                1f, 1f,
                0f,
                0, 0,
                texture.width, texture.height,
                false, true)

        println(oldColor)


    }

    private fun addMessage(message: String) {
        messages.add(message)

        if (messages.size > maxMessageCount) {
            messages.removeIndex(0)
        }
    }

    override fun dispose() {
        batch.dispose()
        texture.dispose()
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val message = "touchUp screenX= $screenX, screenY= $screenY"
        logger.debug(message)
        addMessage(message)
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        val message = "mouseMoved screenX= $screenX, screenY= $screenY"
        logger.debug(message)
        addMessage(message)
        return true
    }

    override fun keyTyped(character: Char): Boolean {
        val message = "keyTyped key= $character"
        logger.debug(message)
        addMessage(message)
        return true
    }

    override fun scrolled(amount: Int): Boolean {
        val message = "scrolled amount= $amount"
        logger.debug(message)
        addMessage(message)
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        val message = "keyUp keycode= $keycode"
        logger.debug(message)
        addMessage(message)
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        val message = "touchDragged screenX= $screenX, screenY= $screenY, pointer= $pointer"
        logger.debug(message)
        addMessage(message)
        return true
    }

    override fun keyDown(keycode: Int): Boolean {
        val message = "keyDown keycode= $keycode"
        logger.debug(message)
        addMessage(message)
        return true
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val message = "touchDragged screenX= $screenX, screenY= $screenY, pointer= $pointer, button= $button"
        logger.debug(message)
        addMessage(message)
        return true
    }
}