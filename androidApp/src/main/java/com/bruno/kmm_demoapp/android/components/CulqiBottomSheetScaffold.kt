package com.bruno.kmm_demoapp.android.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.LifecycleOwner
import com.bruno.kmm_demoapp.android.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.launch

//@ExperimentalMaterialApi
//@Composable
//fun CulqiBottomSheetScaffold(
//    content: @Composable ColumnScope.() -> Unit
//) {
//    val state = rememberCulqiBottomSheetState()
//    val bottomSheetScaffoldState = (state as CulqiBottomSheetStateImpl).state
//    val sheetContent by state.content
//    CompositionLocalProvider(LocalCulqiBottomSheetState provides state) {
//        ModalBottomSheetLayout(
//            sheetState = bottomSheetScaffoldState,
//            sheetBackgroundColor = Color.Transparent,
//            scrimColor = Color(0x99000000),
//            sheetContent = {
//                Column(modifier = Modifier.defaultMinSize(minHeight = 1.dp)){
//                    sheetContent()
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        ) {
//            Column {
//                content()
//            }
//        }
//    }
//}
//
//
//@ExperimentalMaterialApi
//@Composable
//fun rememberCulqiBottomSheetState(
//    initialValue: ModalBottomSheetValue = ModalBottomSheetValue.Hidden,
//    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
//    confirmStateChange: (ModalBottomSheetValue) -> Boolean = { true }
//): CulqiBottomSheetState {
//    val coroutineScope = rememberCoroutineScope()
//    val state = rememberModalBottomSheetState(
//        initialValue = initialValue,
//        animationSpec = animationSpec,
//        confirmStateChange = confirmStateChange
//    )
//    return remember(coroutineScope, state) {
//        CulqiBottomSheetStateImpl(
//            state = state,
//            coroutine = coroutineScope
//        )
//    }
//}
//
interface CulqiBottomSheetState {
    fun show()
    fun hide()
    fun setSheetContent(
        lifecycleOwner: LifecycleOwner,
        content: @Composable () -> Unit
    )
    @Composable
    fun visibilityAsState(): State<Boolean>
}

private class CulqiBottomSheetStateImpl @OptIn(ExperimentalMaterialApi::class) constructor(
    val state: ModalBottomSheetState,
    val coroutine: CoroutineScope
) : CulqiBottomSheetState {

    var content = mutableStateOf<@Composable () -> Unit>({}, neverEqualPolicy())

    @OptIn(ExperimentalMaterialApi::class)
    override fun show() {
        coroutine.launch {
            if (!state.isVisible) {
                state.show()
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    override fun hide() {
        coroutine.launch {
            if (state.isVisible) {
                state.hide()
            }
        }
    }

    override fun setSheetContent(
        lifecycleOwner: LifecycleOwner,
        content: @Composable () -> Unit
    ){
        this@CulqiBottomSheetStateImpl.content.value = content
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun visibilityAsState(): State<Boolean> {
        val currentValue = state.currentValue
        return remember(currentValue) {
            mutableStateOf(currentValue == ModalBottomSheetValue.Expanded)
        }
    }

}

val LocalCulqiBottomSheetState = compositionLocalOf<CulqiBottomSheetState> { error("") }

@Composable
fun localCulqiBottomState(): CulqiBottomSheetState {
    return LocalCulqiBottomSheetState.current
}

//@Composable
//fun CulqiBottomSheetScaffold(
//    modifier: Modifier = Modifier,
//    title: String,
//    onBack: () -> Unit,
//    withContentPadding: Boolean = false,
//    content: @Composable ColumnScope.() -> Unit
//) {
//    Column(
//        modifier = Modifier.background(
//            color = CulqiTheme.colors.background, shape = RoundedCornerShape(
//                topStart = 15.dp, topEnd = 15.dp
//            )
//        ), horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Spacer(modifier = Modifier.height(8.dp))
//        Box(
//            modifier = Modifier
//                .height(4.dp)
//                .width(54.dp)
//                .background(
//                    color = CulqiTheme.palette.sky.p400,
//                    shape = RoundedCornerShape(2.dp)
//                )
//        )
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 0.dp, end = 0.dp, top = 22.dp)
//        ) {
//            ConstraintLayout(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//
//                val (textRef, buttonRef) = createRefs()
//
//                CulqiText(
//                    text = title, fontSize = 17.sp,
//                    modifier = Modifier.constrainAs(textRef) {
//                        top.linkTo(parent.top)
//                        linkTo(start = buttonRef.end, end = parent.end, bias = 0.4f)
//                        width = Dimension.preferredWrapContent
//                    }, textAlign = TextAlign.Center, color = CulqiTheme.colors.onBackground
//                )
//                IconButton(modifier = Modifier
//                    .width(36.dp)
//                    .constrainAs(buttonRef) {
//                        start.linkTo(parent.start, margin = 8.dp)
//                        linkTo(top = textRef.top, bottom = textRef.bottom)
//                    }, onClick = { onBack() }) {
//                    Image(
//                        painter = painterResource(R.drawable.ic_chevron_left),
//                        contentDescription = null,
//                        modifier = Modifier.width(7.dp),
//                        colorFilter = ColorFilter.tint(
//                            CulqiTheme.colors.onBackground
//                        )
//                    )
//                }
//            }
//        }
//        Column(modifier = modifier.run {
//            if(withContentPadding){
//                padding(start = 16.dp, end = 16.dp, top = 32.dp)
//            } else {
//                this
//            }
//        }) {
//            content()
//        }
//    }
//}