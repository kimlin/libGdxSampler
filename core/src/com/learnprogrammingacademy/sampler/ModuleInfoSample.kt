package com.learnprogrammingacademy.sampler

import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.learnprogrammingacademy.sampler.utils.logger

class ModuleInfoSample : ApplicationAdapter() {

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