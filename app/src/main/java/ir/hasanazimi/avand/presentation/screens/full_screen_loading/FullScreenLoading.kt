package ir.hasanazimi.avand.presentation.screens.full_screen_loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hasanazimi.avand.R
import ir.hasanazimi.avand.presentation.theme.AvandTheme
import ir.hasanazimi.avand.presentation.theme.CustomTypography


@Composable
fun FullScreenLoading(show: Boolean = false) {

    if (show) {

        val infiniteTransition = rememberInfiniteTransition(label = "rotation")
        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1200, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "rotationAnim"
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clickable{}
                .focusable()
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
        ) {
            Card(
                modifier = Modifier.height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Row(
                    Modifier
                        .clickable {}
                        .focusable(true),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Text(
                            text = "کمی صبر کنید..",
                            textAlign = TextAlign.End,
                            style = CustomTypography.labelLarge.copy(color = Color.Black),
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(vertical = 8.dp)
                        )
                    }


                    Box(
                        modifier = Modifier.padding(
                            end = 12.dp,
                            bottom = 4.dp,
                            top = 4.dp,
                            start = 8.dp
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.loading),
                            contentDescription = "loading image",
                            modifier = Modifier
                                .size(24.dp)
                                .graphicsLayer {
                                    rotationZ = rotation
                                },
                            colorFilter = ColorFilter.tint(Color.Black)
                        )
                    }

                }
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FullScreenLoading() {
    FullScreenLoading(show = true)
}