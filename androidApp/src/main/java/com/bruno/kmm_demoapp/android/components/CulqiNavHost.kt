package com.bruno.kmm_demoapp.android.components

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment

@Composable
fun CulqiNavHost(
    modifier: Modifier = Modifier,
    controller: CulqiNavHostController
){
    if(controller is CulqiNavHostControllerImpl){
        CulqiNavHost(
            modifier = modifier,
            controller = controller
        )
    }
}

@Composable
private fun CulqiNavHost(
    modifier: Modifier,
    controller: CulqiNavHostControllerImpl
){
    AndroidView(
        modifier = modifier,
        factory = {
            controller.container
        }
    )
}

fun createCulqiNavHostController(navHost: DynamicNavHostFragment, container: View): CulqiNavHostController {
    return CulqiNavHostControllerImpl(
        navHost,
        container
    )
}

interface CulqiNavHostController

private class CulqiNavHostControllerImpl(
    val navHost: NavHost,
    val container: View
): CulqiNavHostController

fun CulqiNavHostController.addOnDestinationChangedListener(
    listener: NavController.OnDestinationChangedListener
){
    if(this is CulqiNavHostControllerImpl){
        this.navHost.navController.addOnDestinationChangedListener(listener)
    }
}

fun CulqiNavHostController.setGraph(
    id: Int
){
    if(this is CulqiNavHostControllerImpl){
        navHost.navController.setGraph(id)
    }
}

val CulqiNavHostController.value get(): NavController {
    return if(this is CulqiNavHostControllerImpl){
        navHost.navController
    } else {
        null
    }!!
}