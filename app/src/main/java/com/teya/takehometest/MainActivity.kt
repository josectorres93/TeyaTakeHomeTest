package com.teya.takehometest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.teya.presentation.ui.AppNavigation
import com.teya.takehometest.ui.theme.TeyaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeyaTestTheme {
                AppNavigation()
            }
        }
    }
}
