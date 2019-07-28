package com.mobile.mavelapp.presenter.encryption

import org.junit.Before
import org.junit.Test

class Md5Test {

    lateinit var subject : Md5

    @Before
    fun setup(){
        subject = Md5()
    }

    @Test
    fun `generate md5 from string should return always the same value`() {
        val generatedDigest = subject.generateMd5FromString("45959fcee5f69862d2d0753ea7e58d05");
        assert(generatedDigest == "9bffa0a48a380c02c513a9a1185d8ff2")

    }
}