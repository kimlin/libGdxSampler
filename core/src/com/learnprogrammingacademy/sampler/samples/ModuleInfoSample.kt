package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.logger

class ModuleInfoSample : SampleBase() {

    companion object {
        @JvmStatic
        private val logger = logger<ModuleInfoSample>()
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        logger.debug("app= ${Gdx.app}")
        logger.debug("audio= ${Gdx.audio}")
        logger.debug("input= ${Gdx.input}")
        logger.debug("files= ${Gdx.files}")
        logger.debug("net= ${Gdx.net}")
        logger.debug("graphics= ${Gdx.graphics}")
    }

}