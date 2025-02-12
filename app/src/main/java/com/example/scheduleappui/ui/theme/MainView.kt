package com.example.scheduleappui.ui.theme

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetLayout
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.primarySurface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.scheduleappui.AuthViewModel
import com.example.scheduleappui.MainViewModel
import com.example.scheduleappui.Navigation
import com.example.scheduleappui.R
import com.example.scheduleappui.Screen
import com.example.scheduleappui.screenInBottom
import com.example.scheduleappui.screensInDrawer
import com.example.scheduleappui.userpreferences.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    // Allow us to find out on which "View" we current are
    val controller: NavHostController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val authViewModel : AuthViewModel = viewModel()
    val context = LocalContext.current

    val dialogOpen = remember{
        mutableStateOf(false)
    }

    val currentScreen = remember{
        viewModel.currentScreen.value
    }

    val title = remember{
        mutableStateOf(currentScreen.title)
    }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded}
    )

    val roundedCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp

    val bottomBar : @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomNavigation(modifier = Modifier.wrapContentSize()) {
                screenInBottom.forEach{
                    item ->
                    val isSelected = currentRoute == item.bRoute
                    val tint = if (isSelected) Color.White else Color.Black
                    BottomNavigationItem(selected = currentRoute == item.bRoute,
                        onClick = {controller.navigate(item.bRoute)
                                  title.value = item.bTitle
                                  }, icon = {

                            Icon(tint = tint,painter = painterResource(id = item.icon),
                                contentDescription = item.bTitle)
                        },
                        label = {
                            Text(text = item.bTitle, color = tint)
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                        )
                }
            }
        }
    }

    ModalBottomSheetLayout(sheetContent = {
        MoreBottomSheet(
            modifier = modifier,
            authViewModel = authViewModel,
            context = context,
            controller
        )
    },
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius)
        ) {
        Scaffold(
            bottomBar = bottomBar,
            topBar = {
                TopAppBar(title = { Text(title.value) },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (modalSheetState.isVisible) modalSheetState.hide()
                                    else modalSheetState.show()
                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                        }
                    },
                    navigationIcon = { IconButton(onClick = {
                        // Open the drawer
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu" )
                    }},
                    colors = topAppBarColors(
                        containerColor = colorResource(id = R.color.app_bar_color),
                        titleContentColor = colorResource(id = R.color.white),
                    ),
                )
            },scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(Modifier.padding(16.dp)){
                    items(screensInDrawer){
                            item ->
                        DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if(item.dRoute == "add_account"){
                                dialogOpen.value= true
                            }else{
                                controller.navigate(item.dRoute)
                                title.value = item.dTitle
                            }
                        }
                    }
                }
            }
        ) {
            Navigation(controller, viewModel, it)
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked : () -> Unit
){
    val background = if (selected) Color.Red else Color.White
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h5,
        )
    }
}

@Composable
fun MoreBottomSheet(modifier: Modifier, authViewModel: AuthViewModel, context: Context, controller: NavHostController) {

    Box(
        Modifier.fillMaxWidth().height(300.dp).background(
            MaterialTheme.colors.primarySurface)
    ) {
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.padding(16.dp).clickable{
                controller.navigate("al")
            }) {
                Icon(modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(R.drawable.baseline_settings_24),
                    contentDescription = "Annual Leave Request")
                Text(text = "Annual Leave Request", fontSize = 20.sp, color = Color.White)
            }
            Row(modifier = Modifier.padding(16.dp).clickable{
                logoutUser(context, authViewModel)
            }) {
                Icon(modifier = Modifier.padding(end = 8.dp).clickable{
                    logoutUser(context, authViewModel)
                },
                    painter = painterResource(R.drawable.baseline_share_24),
                    contentDescription = "Logout and Close")
                Text(text = "Logout and Close", fontSize = 20.sp, color = Color.White)
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(R.drawable.baseline_help_center_24),
                    contentDescription = "Help")
                Text(text = "Help", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

fun logoutUser(context: Context, authViewModel: AuthViewModel) {
    authViewModel.logout()
    UserPreferences.clearEmail(context)
    val activity = context as? android.app.Activity
    activity?.finishAffinity()
}
