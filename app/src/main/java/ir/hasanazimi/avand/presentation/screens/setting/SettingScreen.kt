package ir.hasanazimi.avand.presentation.screens.setting

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hasanazimi.avand.MainActivity
import ir.hasanazimi.avand.common.extensions.withNotNull
import ir.hasanazimi.avand.common.file.AssetHelper
import ir.hasanazimi.avand.data.entities.local.other.CityEntity
import ir.hasanazimi.avand.data.entities.local.weather.WeatherEntity
import ir.hasanazimi.avand.db.DataStoreManager
import ir.hasanazimi.avand.presentation.bottom_sheets.CitiesModalBottomSheet
import ir.hasanazimi.avand.presentation.bottom_sheets.UserProfileBottomSheet
import ir.hasanazimi.avand.presentation.itemViews.SettingItemView
import ir.hasanazimi.avand.presentation.itemViews.settingItems
import ir.hasanazimi.avand.presentation.navigation.Screens
import ir.hasanazimi.avand.presentation.theme.AvandTheme
import ir.hasanazimi.avand.presentation.theme.CustomTypography
import ir.hasanazimi.avand.presentation.theme.RedColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject


@Composable
fun SettingScreen(activity: MainActivity, navController: NavController) {

    val viewModel = hiltViewModel<SettingScreenVM>()

    var citiesModalOpenState by remember { mutableStateOf(false) }
    var userProfileModalOpenState by remember { mutableStateOf(false) }

    var userNameState = viewModel.userName.collectAsStateWithLifecycle()
    var defaultCityState = viewModel.defaultCity.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.getDefaultCity()
        viewModel.getUserName()
    }

    AvandTheme {

        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 14.dp, end = 14.dp, bottom = 8.dp, top = 8.dp)
            ) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.primary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {

                        var notificationCheckedToggle by remember { mutableStateOf(true) }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                Modifier.weight(0.6f),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "اعلانها فعال باشن؟",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = CustomTypography.labelSmall.copy(
                                        textAlign = TextAlign.Start,
                                    ),
                                    lineHeight = TextUnit(20f, TextUnitType.Sp),
                                    maxLines = 1
                                )
                                Text(
                                    text = if (notificationCheckedToggle) "بله" else "خیر",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    style = CustomTypography.labelSmall.copy(
                                        textAlign = TextAlign.Start,
                                    ),
                                    color = if (notificationCheckedToggle) MaterialTheme.colorScheme.primary else RedColor,
                                    lineHeight = TextUnit(20f, TextUnitType.Sp),
                                    maxLines = 1
                                )
                            }


                            Checkbox(
                                checked = notificationCheckedToggle,
                                onCheckedChange = { notificationCheckedToggle = it }
                            )
                        }

                    }
                }



                Card(
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1f)
                        .padding(start = 4.dp),
                    onClick = {
                        citiesModalOpenState = true
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.6f)
                            ) {

                                Text(
                                    text = "لوکیشن انتخابی",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = CustomTypography.labelSmall.copy(
                                        textAlign = TextAlign.Start,
                                    ),
                                    lineHeight = TextUnit(20f, TextUnitType.Sp),
                                    maxLines = 1
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = defaultCityState.value?.cityName ?: "",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        color = MaterialTheme.colorScheme.primary,
                                        maxLines = 1
                                    )
                                }


                            }


                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "location",
                                modifier = Modifier.weight(0.2f)
                            )

                        }

                    }
                }

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                LazyColumn {
                    items(settingItems) {
                        SettingItemView(it) { type ->
                            when (type) {

                                Screens.Setting -> {
                                    viewModel.getUserName().also {
                                        userProfileModalOpenState = true
                                    }
                                }

                                Screens.AboutUs -> {
                                    navController.navigate(Screens.AboutUs.routeId)
                                }

                                else -> {
                                    Log.i("TAG", "SettingScreen: ${type?.routeId}")
                                }

                            }
                        }
                    }
                }
            }

        }
    }


    CitiesModalBottomSheet(
        isOpen = citiesModalOpenState,
        selectedCity = defaultCityState.value,
        citiesSnapshotList = viewModel.prepareCities(),
        onDismiss = { city ->
            viewModel.saveAndNotifyDefaultCity(city)
            citiesModalOpenState = false
        }
    ) { city ->
        citiesModalOpenState = false
        city.withNotNull { it -> viewModel.saveAndNotifyDefaultCity(it) }
    }


    UserProfileBottomSheet(lastUserName = userNameState.value, isOpen = userProfileModalOpenState) {
        viewModel.saveAndNotifyUserName(userNameState.value).also {
            userProfileModalOpenState = false
        }
    }


}


@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    AvandTheme {
        SettingScreen(
            activity = MainActivity(),
            navController = rememberNavController()
        )
    }
}