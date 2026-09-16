package com.example.data

import android.content.Context
import android.content.SharedPreferences

class AccessPreferences(context: Context) {
  private val prefs: SharedPreferences =
    context.getSharedPreferences("one_iptv_access_prefs", Context.MODE_PRIVATE)

  companion object {
    const val KEY_IS_UNLOCKED = "is_channels_unlocked"
    const val SECRET_CODE = "6565"
  }

  fun isUnlocked(): Boolean {
    return prefs.getBoolean(KEY_IS_UNLOCKED, false)
  }

  fun verifyAndUnlock(code: String): Boolean {
    if (code.trim() == SECRET_CODE) {
      prefs.edit().putBoolean(KEY_IS_UNLOCKED, true).apply()
      return true
    }
    return false
  }

  fun lock() {
    prefs.edit().putBoolean(KEY_IS_UNLOCKED, false).apply()
  }
}
