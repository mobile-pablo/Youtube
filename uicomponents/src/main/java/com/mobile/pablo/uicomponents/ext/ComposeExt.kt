package com.mobile.pablo.uicomponents.ext

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.mobile.pablo.uicomponents.theme.spacing
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.utils.navGraph
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.compose.material.MaterialTheme as Theme

/**
 * Found solution on
 *  https://stackoverflow.com/questions/68592618/how-to-add-border-on-bottom-only-in-jetpack-compose
 */
@Suppress("UnnecessaryComposedModifier")
fun Modifier.topRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier =
    composed(
        factory = {
            this.then(
                Modifier.drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawLine(
                            brush,
                            Offset(
                                width.value,
                                0f
                            ),
                            Offset(
                                size.width - width.value,
                                0f
                            )
                        )
                    }
                }
            )
        },
        inspectorInfo =
            debugInspectorInfo {
                name = "border"
                properties["width"] = width
                if (brush is SolidColor) {
                    properties["color"] = brush.value
                    value = brush.value
                } else {
                    properties["brush"] = brush
                }
                properties["shape"] = RectangleShape
            }
    )

@Suppress("UnnecessaryComposedModifier")
fun Modifier.bottomRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier =
    composed(
        factory = {
            this.then(
                Modifier.drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawLine(
                            brush,
                            Offset.Zero.copy(y = size.height),
                            Offset(
                                size.width,
                                size.height
                            )
                        )
                    }
                }
            )
        },
        inspectorInfo =
            debugInspectorInfo {
                name = "border"
                properties["width"] = width
                if (brush is SolidColor) {
                    properties["color"] = brush.value
                    value = brush.value
                } else {
                    properties["brush"] = brush
                }
                properties["shape"] = RectangleShape
            }
    )

@Suppress("UnnecessaryComposedModifier")
fun Modifier.leftRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier =
    composed(
        factory = {
            this.then(
                Modifier.drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawLine(
                            brush,
                            Offset.Zero.copy(y = 0f),
                            Offset.Zero.copy(y = size.height)
                        )
                    }
                }
            )
        },
        inspectorInfo =
            debugInspectorInfo {
                name = "border"
                properties["width"] = width
                if (brush is SolidColor) {
                    properties["color"] = brush.value
                    value = brush.value
                } else {
                    properties["brush"] = brush
                }
                properties["shape"] = RectangleShape
            }
    )

@Suppress("UnnecessaryComposedModifier")
fun Modifier.rightRectBorder(
    width: Dp = Dp.Hairline,
    brush: Brush = SolidColor(Color.Black)
): Modifier =
    composed(
        factory = {
            this.then(
                Modifier.drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawLine(
                            brush,
                            Offset(
                                size.width,
                                0f
                            ),
                            Offset(
                                size.width,
                                size.height
                            )
                        )
                    }
                }
            )
        },
        inspectorInfo =
            debugInspectorInfo {
                name = "border"
                properties["width"] = width
                if (brush is SolidColor) {
                    properties["color"] = brush.value
                    value = brush.value
                } else {
                    properties["brush"] = brush
                }
                properties["shape"] = RectangleShape
            }
    )

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Modifier.testTag(
    @androidx.annotation.StringRes resId: Int
) = testTag(stringResource(id = resId))

// https://stackoverflow.com/a/70185157/13770601
@Composable
inline fun <reified T> Flow<T>.observeWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    key2: Any? = null,
    noinline action: suspend (T) -> Unit
) {
    LaunchedEffect(
        key1 = Unit,
        key2 = key2
    ) {
        lifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(
                lifecycleOwner.lifecycle,
                minActiveState
            ).collect(action)
        }
    }
}

fun navigateTo(
    destinationsNavigator: DestinationsNavigator,
    navController: NavController,
    direction: Direction
) {
    val navGraphSpec: NavGraphSpec = navController.currentBackStackEntry!!.navGraph()
    destinationsNavigator.navigate(direction within navGraphSpec)
}

/**
 * https://medium.com/@daniel.atitienei/shimmer-effect-in-jetpack-compose-f247b212c062
 */
fun Modifier.shimmerEffect(): Modifier =
    composed {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        val transition = rememberInfiniteTransition(
            label = "Animates the background"
        )
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000)
            ),
            label = "Animates the background"
        )
        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFFB8B5B5),
                    Color(0xFF8F8B8B),
                    Color(0xFFB8B5B5)
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            ),
            shape = RoundedCornerShape(Theme.spacing.spacing_6)
        )
            .onGloballyPositioned {
                size = it.size
            }
    }
