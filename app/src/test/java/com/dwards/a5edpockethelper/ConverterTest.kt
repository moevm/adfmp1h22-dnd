package com.dwards.a5edpockethelper

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConverterTest {
    private val converter = StringListConverter()

    @Test
    fun fromStringConvert() {
        assertEquals(emptyList<String>(), converter.fromString(""))
        assertEquals(listOf("dog", "cat", "rabbit"), converter.fromString("dog,cat,rabbit"))
    }

    @Test
    fun toStringConvert() {
        assertEquals(converter.toString(listOf("dog", "cat", "rabbit")), "dog,cat,rabbit")
        assertEquals(converter.toString(emptyList()), "")
    }
}
