package com.bruno.kmm_demoapp.android.utils

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import com.bruno.kmm_demoapp.android.R
import com.bruno.kmm_demoapp.android.components.CulqiNavHostController
import com.bruno.kmm_demoapp.android.components.createCulqiNavHostController
import com.bruno.kmm_demoapp.android.components.setGraph
import com.bruno.kmm_demoapp.android.databinding.NavHostBinding
import com.bruno.kmm_demoapp.android.ui.base.ScreenFragment

class LazyNavHostController(
    val controllerProducer: (arguments: Bundle?) -> CulqiNavHostController,
    val fragment: Fragment
): Lazy<CulqiNavHostController> {
    private var cached: CulqiNavHostController? = null
    private var arguments: Bundle? = null
    override val value: CulqiNavHostController
        get() {
            var controller = cached
            if(controller == null){
                controller = controllerProducer(arguments)
                cached = controller
                return controller
            }
            return controller
        }

    override fun isInitialized(): Boolean = cached != null
    fun reset(){
        cached = null
        fragment.apply {
            childFragmentManager.findFragmentById(R.id.nav_host)?.let { fragment ->
                val arguments = fragment.arguments
                childFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
            }
        }
    }
}

fun ScreenFragment<*>.navHostController(graphId: Int): LazyNavHostController {
    val lazy = LazyNavHostController(
        controllerProducer = {
            val binding = NavHostBinding.inflate(layoutInflater)
            val navHost = childFragmentManager.findFragmentById(R.id.nav_host) as DynamicNavHostFragment
            navHost.arguments = it
            createCulqiNavHostController(
                navHost,
                binding.root
            ).apply {
                setGraph(graphId)
            }
        },
        this
    )
    setLazyHostController(lazy)
    return lazy
}