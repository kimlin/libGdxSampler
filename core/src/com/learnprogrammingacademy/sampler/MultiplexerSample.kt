package com.learnprogrammingacademy.sampler

import com.badlogic.gdx.*
import com.learnprogrammingacademy.sampler.utils.logger

class MultiplexerSample : ApplicationAdapter() {

    companion object {
        @JvmStatic
        private val logger = logger<MultiplexerSample>()
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        // anonymous inner class
        val firstProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                logger.debug("first keyDown keycode= $keycode")
                return true
            }

            override fun keyUp(keycode: Int): Boolean {
                logger.debug("first keyUp keycode= $keycode")
                return false
            }
        }

        val secondProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                logger.debug("second keyDown keycode= $keycode")
                return true
            }

            override fun keyUp(keycode: Int): Boolean {
                logger.debug("second keyUp keycode= $keycode")
                return false
            }
        }

        Gdx.input.inputProcessor = InputMultiplexer(firstProcessor, secondProcessor)
    }

}