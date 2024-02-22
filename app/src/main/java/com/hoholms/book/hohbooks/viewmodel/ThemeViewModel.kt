package com.hoholms.book.hohbooks.viewmodel

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoholms.book.hohbooks.R
import kotlinx.coroutines.launch

enum class ThemeSetting { Light, Dark, Auto }

class ThemeViewModel(private val context: Context) : ViewModel() {
    companion object {
        private const val PREFS_NAME = "theme_preferences"
        private const val THEME_KEY = "theme_setting"
    }

    private val prefs by lazy { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    var themeSetting = mutableStateOf(ThemeSetting.Auto)
        private set

    init {
        loadThemeSetting()
    }

    private fun loadThemeSetting() {
        val themeName = prefs.getString(THEME_KEY, ThemeSetting.Auto.name) ?: ThemeSetting.Auto.name
        themeSetting.value = ThemeSetting.valueOf(themeName)
    }

    fun setTheme(theme: ThemeSetting) {
        themeSetting.value = theme
        viewModelScope.launch {
            with(prefs.edit()) {
                putString(THEME_KEY, theme.name)
                apply()
            }
        }
    }

    @Composable
    fun getI18nName(theme: ThemeSetting): String {
        return when (theme) {
            ThemeSetting.Light -> stringResource(R.string.theme_light)
            ThemeSetting.Dark -> stringResource(R.string.theme_dark)
            ThemeSetting.Auto -> stringResource(R.string.theme_auto)
        }
    }

}
