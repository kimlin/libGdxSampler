package com.learnprogrammingacademy.sampler.common

class SampleInfo(val clazz: Class<out SampleBase>) {
    val name : String = clazz.simpleName
}