package com.hoholms.book.hohbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hoholms.book.hohbooks.navigation.bottom.BottomNavigationBar
import com.hoholms.book.hohbooks.ui.theme.HohBooksTheme
import com.hoholms.book.hohbooks.viewmodel.ThemeViewModel
import com.hoholms.book.hohbooks.viewmodel.ThemeViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HohBooks()
        }
    }
}

@Composable
fun HohBooks() {
    val context = LocalContext.current
    val themeViewModel: ThemeViewModel = viewModel(factory = ThemeViewModelFactory(context))

    HohBooksTheme(themeSetting = themeViewModel.themeSetting.value) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            BottomNavigationBar(themeViewModel)
        }
    }
}
