package com.learnprogrammingacademy.sampler.common

class SampleInfo(clazz: Class<out SampleBase>) {
    val name : String = clazz.simpleName
}