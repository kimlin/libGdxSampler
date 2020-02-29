package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.*

class InputListeningSample : SampleBase() {

    companion object {
        @JvmStatic
        private val logger = logger<InputListeningSample>()
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var font: BitmapFont

    private val maxMessageCount = 15
    private val messages = GdxArray<String>()

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        logger.debug("create() is called")

        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())

        Gdx.input.inputProcessor = this
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun render() {
        // clear screen
        clearScreen()

        batch.projectionMatrix = camera.combined
        batch.use { draw() }
    }

    private fun draw() {
        for (i in 0 until messages.size) {
            font.draw(batch, messages[i], 20f, 720f - 40f * (i + 1))
        }
    }

    private fun addMessage(message: String) {
        messages.add(message)

        if (messages.size > maxMessageCount) {
            messages.removeIndex(0)
        }
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
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