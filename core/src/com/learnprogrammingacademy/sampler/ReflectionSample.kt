package com.learnprogrammingacademy.sampler

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.reflect.ClassReflection
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.learnprogrammingacademy.sampler.utils.clearScreen
import com.learnprogrammingacademy.sampler.utils.logger
import com.learnprogrammingacademy.sampler.utils.toInternalFile

class ReflectionSample : ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val logger = logger<ReflectionSample>()
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var batch: SpriteBatch
    private lateinit var font: BitmapFont

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        logger.debug("create() is called")

        camera = OrthographicCamera()
        viewport = FitViewport(1080f, 720f, camera)
        batch = SpriteBatch()
        font = BitmapFont("fonts/oswald-32.fnt".toInternalFile())
        debugReflection<ReflectionSample>()
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
        // mouse/touch x/y coordinates
        val mouseX = Gdx.input.x
        val mouseY = Gdx.input.y

        val leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT)
        val rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT)

        font.draw(batch, "Mouse x=$mouseX, y=$mouseY", 20f, 720f - 20f)

        val leftPressedString = if (leftPressed) "Left button pressed" else "Left button not pressed"

        font.draw(batch, leftPressedString, 20f, 720f - 50f)

        val rightPressedString = if (rightPressed) {
            "Right button pressed"
        } else {
            "Right button not pressed"
        }
        font.draw(batch, rightPressedString, 20f, 720f - 80f)

        // keys
        val wPressed = Gdx.input.isKeyJustPressed(Input.Keys.W)
        val sPressed = Gdx.input.isKeyPressed(Input.Keys.S)

        font.draw(batch,
                if (wPressed) "W is pressed" else "W is not pressed",
                20f,
                720f - 110f)

        font.draw(batch,
                if (sPressed) "S is pressed" else "S is not pressed",
                20f,
                720f - 140f)


    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }

    private inline fun <reified T : Any> debugReflection() {
        val fields = ClassReflection.getDeclaredFields(T::class.java)
        val methods = ClassReflection.getDeclaredMethods(T::class.java)

        logger.debug("reflecting class= ${T::class.java.simpleName}")
        logger.debug("field count= ${fields.size}")

        for (field in fields) {
            logger.debug("name= ${field.name} type= ${field.type}")
        }

        logger.debug("method count= ${methods.size}")

        methods.forEach {
            logger.debug("name= ${it.name} parameterCount= ${it.parameterTypes.size}")
        }
    }
}