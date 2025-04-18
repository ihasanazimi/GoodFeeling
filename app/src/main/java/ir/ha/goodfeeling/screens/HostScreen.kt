package ir.ha.goodfeeling.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ha.goodfeeling.MainActivity
import ir.ha.goodfeeling.R
import ir.ha.goodfeeling.db.DataStoreManager
import ir.ha.goodfeeling.navigation.BottomNavigationBar
import ir.ha.goodfeeling.navigation.Screens
import ir.ha.goodfeeling.ui.theme.CustomTypography
import ir.ha.goodfeeling.ui.theme.DarkBackground
import ir.ha.goodfeeling.ui.theme.GoodFeelingTheme
import ir.ha.goodfeeling.ui.theme.LightBackground
import ir.ha.goodfeeling.ui.theme.getBackgroundColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun HostScreen(activity : MainActivity , navController: NavHostController) {

    val viewModel = hiltViewModel<HostScreenVM>()
    GoodFeelingTheme {

        val hostNavController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        var userName by remember { mutableStateOf("حسن عظیمی") }


        SideEffect {
            coroutineScope.launch {
                viewModel.userName.collect {
                    userName = it
                }
            }
        }

        Scaffold(
            modifier = Modifier.background(color = getBackgroundColor()),
            bottomBar = {
                BottomNavigationBar(navController = hostNavController)
            },
            topBar = {
                TopBar(userName)
            }
        ) { innerPadding ->
            Surface {
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        navController = hostNavController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screens.Home.route) {
                            HomeScreen(navController = navController,activity = activity)
                        }

                        composable(route = Screens.CurrencyPrices.route) {
                            CurrencyPricesScreen(navController = navController)
                        }

                        composable(route = Screens.Setting.route) {
                            SettingScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(userName: String) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                MyLottieAnimation(
                    Modifier
                        .align(Alignment.Bottom)
                        .height(124.dp)
                        .width(230.dp)
                        .weight(1f)
                        .graphicsLayer(rotationZ = 180f)
                        .graphicsLayer(rotationX = 180f)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "سـلام",
                        style = CustomTypography.labelSmall,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = userName,
                        style = CustomTypography.titleLarge,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    }
}


@Composable
fun MyLottieAnimation(modifier: Modifier) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.birds)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}

@HiltViewModel
class HostScreenVM @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel(){

    val TAG = "HostScreenVM"
    
    init {
        getUserName()
    }

    val userName = MutableStateFlow("")

    fun getUserName(){
        viewModelScope.launch {
            dataStoreManager.userNameFlow.collect {
                Log.i(TAG, "getUserName: $it")
                userName.emit(it?:"")
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun HostScreenPreview() {
    GoodFeelingTheme {
        HostScreen(activity = MainActivity() , navController = rememberNavController())
    }
}