package com.bruno.kmm_demoapp.android.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.annotation.IdRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bruno.kmm_demoapp.android.R
import com.bruno.kmm_demoapp.android.components.CulqiBottomSheetState
import com.bruno.kmm_demoapp.android.components.localCulqiBottomState
import com.bruno.kmm_demoapp.android.databinding.NavHostBinding
import com.bruno.kmm_demoapp.android.utils.LazyNavHostController
import com.bruno.kmm_demoapp.android.utils.network.ConnectivityObserver
import com.bruno.kmm_demoapp.android.utils.network.NetworkConnectivityObserver
import com.culqi.commons.ui.theme.CulqiTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.parametersOf
import kotlin.reflect.KClass

abstract class ScreenFragment<V : ViewModel>(
    private val clazz: KClass<V>,
    private val viewModelStoree: Int? = null,
    private val blockBackAction: Boolean = false,
    private val fullScreen: Boolean = false,
    private val animation: Int? = null,
    isCancelable: Boolean = true,
    private val isBottomSheetScaffold: Boolean = true,
    private val destinationId: List<Int>? = null
) : DialogFragment() {

    private lateinit var connectivityObserver: ConnectivityObserver
    private var statusNetwork = ConnectivityObserver.Status.Unavailable

    private var lazyNavHostController: LazyNavHostController? = null
    var navHostBinding: NavHostBinding? = null

    protected val viewModel: V by lazy {
        if (viewModelStoree != null) {
            findNavController().getViewModelStoreOwner(viewModelStoree)
                .getViewModel(clazz = clazz, parameters = ::getParameters)
        } else {
            getViewModel(clazz = clazz, parameters = ::getParameters)
        }
    }

    init {
        this.isCancelable = isCancelable
    }

    open fun getParameters(): ParametersHolder {
        return parametersOf()
    }

    private lateinit var sheetState: CulqiBottomSheetState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(requireContext())

        if (fullScreen) {
            setStyle(STYLE_NORMAL, R.style.DialogFragmentTheme)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lazyNavHostController?.value
        onInit()
//        initDelegateApiError()
//        initScreenTimeScope()
        viewModel
        return ComposeView(requireContext()).apply {
            setContent {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )

                if (isBottomSheetScaffold) {
                    sheetState = localCulqiBottomState()
                    sheetState.setSheetContent(
                        lifecycleOwner = viewLifecycleOwner,
                        content = {
                            BottomSheetModal()
                        }
                    )
                    val isShow by sheetState.visibilityAsState()
                    BackHandler(
                        enabled = isShow
                    ) {
                        hideBottomSheetModal()
                    }
                }
                CulqiTheme {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CulqiTheme.colors.background)
                    ) {
                        Screen()
                    }
                }
                if (blockBackAction) {
                    BackHandler {}
                }

//                if (status == ConnectivityObserver.Status.Lost) {
//                    NotInternetDialog().show()
//                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (fullScreen) {
            dialog?.let {
                val width = ViewGroup.LayoutParams.MATCH_PARENT
                val height = ViewGroup.LayoutParams.MATCH_PARENT
                it.window?.setLayout(width, height)
                animation?.let { animation ->
                    it.window?.setWindowAnimations(animation)
                }
            }
        }
    }

    open fun onInit() {}

    @Composable
    abstract fun Screen()

    @Composable
    open fun BottomSheetModal() {
    }

    fun showBottomSheetModal() {
        sheetState.show()
    }

    fun hideBottomSheetModal() {
        sheetState.hide()
    }

    protected fun navigate(directions: NavDirections) {
        lifecycleScope.launchWhenResumed {
            findNavController().navigate(directions)
        }
    }

    protected fun popBackStack() {
        findNavController().popBackStack()
    }

    protected fun popBackStack(@IdRes destinationId: Int, inclusive: Boolean = false) {
        findNavController().popBackStack(destinationId, inclusive)
    }

    protected fun DialogFragment.show() {
        val result = this@ScreenFragment.childFragmentManager.findFragmentByTag(this.javaClass.name)
        if (result == null && !isAdded) {
            this@show.show(this@ScreenFragment.childFragmentManager, this.javaClass.name)
        }
    }

    protected fun DialogFragment.hide() {
        if (isAdded) {
            this.dismiss()
        }
    }


//    private fun initDelegateApiError() {
//        viewModel.let { viewModel ->
//            if (viewModel is ApiErrorDelegate) {
//                lifecycleScope.launchWhenResumed {
//                    viewModel.getApiError().collect() {
//                        onApiError(it)
//                    }
//                }
//            }
//        }
//    }

//    open fun onApiError(apiError: ApiError) {
//        when (apiError) {
//            ApiError.Unauthorized -> {
//                UnauthorizedDialog(
//                    onCancel = {
//                        val intent = Intent()
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                        intent.setClassName(
//                            requireActivity(),
//                            "com.culqi.culqiapp.ui.main.MainActivity"
//                        )
//                        startActivity(intent)
//                        requireActivity().finish()
//                    }
//                ).show()
//            }
//
//            ApiError.None -> {}
//            ApiError.NotInternet -> {
//                if(!requireContext().isInternetAvailable()) NotInternetDialog().show()
//            }
//        }
//    }

    fun setLazyHostController(lazy: LazyNavHostController) {
        lazyNavHostController = lazy
    }

    override fun onDestroyView() {
        lazyNavHostController?.reset()
        super.onDestroyView()
    }


//    private fun initScreenTimeScope() {
//        destinationId?.let {
//            viewModel.let { viewModel ->
//                if (viewModel is ScreenTimeScope) {
//                    initScreenTimeScope(viewModel, it)
//                }
//            }
//        }
//    }

//    protected fun Fragment.changeSubScreen(subScreenName: String) {
//        viewModel.let { viewModel ->
//            if (viewModel is ScreenTimeScope) {
//                viewModel.changeSubScreen(subScreenName = subScreenName)
//            }
//        }
//    }
}