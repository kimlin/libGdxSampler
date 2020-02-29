package com.learnprogrammingacademy.sampler.samples

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.learnprogrammingacademy.sampler.common.SampleBase
import com.learnprogrammingacademy.sampler.utils.clearScreen
import com.learnprogrammingacademy.sampler.utils.use

class GdxGeneratedSample : SampleBase() {

    private lateinit var batch: SpriteBatch
    private lateinit var img: Texture

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render() {
        // clear screen
        // DRY - Don't repeat yourself
        // WET - Waste everyone's time
//        Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//
//        batch.begin()
//        batch.draw(img, 0.0f, 0.0f)
//        batch.end()
        clearScreen()
        batch.use { batch.draw(img, 0f, 0f) }
    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
    }

}