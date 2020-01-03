package com.learnprogrammingacademy.sampler.common

import com.learnprogrammingacademy.sampler.samples.*

object SampleInfos {

    val allSamples = arrayListOf(
            SampleInfo(ApplicationListenerSample::class.java),
            SampleInfo(GdxGeneratedSample::class.java),
            SampleInfo(InputListeningSample::class.java),
            SampleInfo(InputPollingSample::class.java),
            SampleInfo(ModuleInfoSample::class.java),
            SampleInfo(MultiplexerSample::class.java),
            SampleInfo(ReflectionSample::class.java)
    )

    // val map : Map<String, Class<out SampleInfo>>
    fun getSampleNames() = allSamples
            .associateBy { it.name }
            .keys
            .toList()
            .sorted()
            .toTypedArray()

    fun find(name: String) = allSamples.find { it.name == name }

}