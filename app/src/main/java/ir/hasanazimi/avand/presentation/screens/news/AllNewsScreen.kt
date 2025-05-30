package ir.hasanazimi.avand.presentation.screens.news

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ir.hasanazimi.avand.MainActivity
import ir.hasanazimi.avand.presentation.dialogs.WebViewDialog
import ir.hasanazimi.avand.presentation.screens.full_screen_loading.FullScreenLoading
import ir.hasanazimi.avand.presentation.screens.web_view.WebViewScreen
import ir.hasanazimi.avand.presentation.theme.AvandTheme


@Composable
fun AllNewsScreen(navController : NavHostController , activity : MainActivity) {

    var urlFromNewsScreen by remember { mutableStateOf("") }
    var showPaginationLoading by remember { mutableStateOf<Boolean>(false) }

    AvandTheme {

        Surface(modifier = Modifier.padding(top = 54.dp , start = 8.dp , end = 8.dp , bottom = 16.dp).fillMaxSize()) {

            NewsScreen(
                navController = navController,
                activity = activity ,
                showMoreBtn = false ,
                onMoreNews = {} ,
                showPaginationLoading = { show ->
                    showPaginationLoading = show
                },
                onOpenWebView = {
                    urlFromNewsScreen = it
                }
            )

            if (urlFromNewsScreen.isNotEmpty()){
                WebViewDialog(
                    onDismissRequest = { urlFromNewsScreen = "" },
                    content = {
                        WebViewScreen(newsUrl = urlFromNewsScreen) { urlFromNewsScreen = "" }
                    }
                )
            }
        }

        FullScreenLoading(showPaginationLoading)

    }

}


@Preview
@Composable
private fun AllNewsScreenPreview() {

    val navController = rememberNavController()
    val activity = MainActivity()

    NewsScreen(
        navController = navController,
        activity = activity ,
        showMoreBtn = false ,
        onMoreNews = {} ,
        showPaginationLoading = {},
        onOpenWebView = {}
    )
}