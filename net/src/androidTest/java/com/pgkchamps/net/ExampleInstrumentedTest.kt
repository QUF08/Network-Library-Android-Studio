package com.pgkchamps.net

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pgkchamps.net.common.ContextHolder
import com.pgkchamps.net.dto.RegDto
import com.pgkchamps.net.dto.UserLogin
import com.pgkchamps.net.remote.UserRemoteApi
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import kotlin.time.Clock

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.pgkchamps.net.test", appContext.packageName)
    }

    @Before
    fun start() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        ContextHolder.init(appContext.applicationContext)
    }

    fun getRandomEmail(): String {
        return "example${System.currentTimeMillis()}}@google.com"
    }


    @Test
    fun test1() = runBlocking {
        val mail = getRandomEmail()

        val userRem = UserRemoteApi()

        val isReg = userRem.reg(
            RegDto(
                email = mail,
                password = "P@ssw0rd",
                passwordConfirm = "P@ssw0rd",
                name = "test",
            )
        )

        println(isReg)
        assert(isReg)

        val loginReturn = userRem.auth(
            UserLogin(
                identity = mail,
                password = "P@ssw0rd"
            )
        )

        println(loginReturn)

        val profile = userRem.getProfile(loginReturn.record.id)

        assertTrue(profile.email == mail)
        assertTrue(profile.name == "test")

    }

}