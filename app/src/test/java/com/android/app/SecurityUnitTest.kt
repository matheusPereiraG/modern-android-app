package com.android.app

import com.android.app.util.HashUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class SecurityUnitTest : HashUtils {
    @Test
    fun isSha256Ok() {
        val equalHash1 = "12345".sha256()
        val equalHash2 = "12345".sha256()

        assertEquals(equalHash1, equalHash2)

        val diffHash1 = "123".sha256()
        val diffHash2 = "1234".sha256()

        assertNotEquals(diffHash1, diffHash2)
    }
}