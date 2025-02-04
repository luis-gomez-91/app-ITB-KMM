package org.example.aok

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import org.example.aok.core.URLOpenerAndroid
import org.example.aok.features.common.home.HomeViewModel
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import dev.icerock.moko.biometry.compose.BindBiometryAuthenticatorEffect
import dev.icerock.moko.biometry.compose.BiometryAuthenticatorFactory
import dev.icerock.moko.biometry.compose.rememberBiometryAuthenticatorFactory
import dev.icerock.moko.mvvm.getViewModel
import io.github.vinceglb.filekit.core.FileKit
import org.example.aok.data.database.getAppDatabase
import org.example.aok.features.common.login.LoginViewModel

class MainActivity : AppCompatActivity() {
//class MainActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FileKit.init(this)
        supportActionBar?.hide()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.Transparent.toArgb()
        window.navigationBarColor = Color.Transparent.toArgb()
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        val dao = getAppDatabase(context = applicationContext).userDao()

        setContent {
            Surface(color = Color.Transparent) {
                val darkTheme = isSystemInDarkTheme()
                insetsController.isAppearanceLightStatusBars = !darkTheme
                insetsController.isAppearanceLightNavigationBars = !darkTheme

                val urlOpener = URLOpenerAndroid(this)
                val homeViewModel = HomeViewModel(urlOpener)
                val biometryFactory: BiometryAuthenticatorFactory = rememberBiometryAuthenticatorFactory()


                loginViewModel = getViewModel {
                    LoginViewModel(
                        biometryAuthenticator = biometryFactory.createBiometryAuthenticator()
                    )
                }
                BindBiometryAuthenticatorEffect(loginViewModel.biometryAuthenticator)

                App(
                    homeViewModel = homeViewModel,
                    loginViewModel = loginViewModel,
                    userDao = dao
                )
            }
        }

        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
    }
}
