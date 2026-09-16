package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.AccessPreferences
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("ONE IPTV", appName)
  }

  @Test
  fun `verify access code unlocks content and persists`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val prefs = AccessPreferences(context)
    prefs.lock()
    assertFalse(prefs.isUnlocked())

    // Invalid code should fail
    val wrongResult = prefs.verifyAndUnlock("1234")
    assertFalse(wrongResult)
    assertFalse(prefs.isUnlocked())

    // Valid secret code 6565 should unlock
    val correctResult = prefs.verifyAndUnlock("6565")
    assertTrue(correctResult)
    assertTrue(prefs.isUnlocked())

    // Lock again
    prefs.lock()
    assertFalse(prefs.isUnlocked())
  }
}

