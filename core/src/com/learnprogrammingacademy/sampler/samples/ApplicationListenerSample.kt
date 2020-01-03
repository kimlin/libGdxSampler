package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.logger

class ApplicationListenerSample : SampleBase() {

    companion object {
        @JvmStatic
        private val logger = logger<ApplicationListenerSample>()
    }

    private var renderInterrupted = true

    // used to initialize game and load resources
    override fun create() {
//        Gdx.app.log("ApplicationListenerSample", "create()")
        Gdx.app.logLevel = Application.LOG_DEBUG
        logger.debug("create()")
    }

    // used to handle setting a new screen size
    override fun resize(width: Int, height: Int) {
        logger.debug("resize()")
    }

    // used to render game elements, default 60 times per second
    override fun render() {
        if (renderInterrupted) {
            logger.debug("render()")
            renderInterrupted = false
        }
    }

    // used to save game state when it looses focus, which does not involve the actual
    // game play being paused unless the developer wants to pause it
    override fun pause() {
        logger.debug("pause()")
        renderInterrupted = true
    }

    // used to handle the game coming back from being paused, and restores game state
    override fun resume() {
        logger.debug("resume()")
        renderInterrupted = true
    }

    // used to free resources and clean up. Free memory and resources that we do not need/use
    override fun dispose() {
        logger.debug("dispose()")
    }
}