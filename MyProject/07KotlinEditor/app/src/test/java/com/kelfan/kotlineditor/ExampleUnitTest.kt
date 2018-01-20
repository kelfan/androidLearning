package com.kelfan.kotlineditor

import com.kelfan.kotlineditor.util.Constant
import com.kelfan.kotlineditor.util.FileWorker
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun getPathCorrect(){
        val result = FileWorker.getStoragePath(Constant.STORAGE_DATA, "test.txt")
        assertEquals("test.txt",result)
    }
}
