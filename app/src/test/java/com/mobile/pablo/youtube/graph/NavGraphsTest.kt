package com.mobile.pablo.youtube.graph

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.ramcosta.composedestinations.spec.DestinationSpec
import org.junit.Test

class NavGraphsTest {
    private val navGraph: NavGraphs = NavGraphs
    private val mainNavGraph = navGraph.main
    private val rootNavGraph = navGraph.root

    companion object {
        const val MAIN = "main"
        const val MAIN_HOME_SCREEN_ROUTE = "main/home_screen"
        const val ROOT = "root"
    }

    @Test
    fun mainNavGraphHasCorrectStartRoute() {
        assertThat(mainNavGraph.route).matches(MAIN)
        assertThat(mainNavGraph.startRoute.route).isEqualTo(MAIN_HOME_SCREEN_ROUTE)
    }

    @Test
    fun rootNavGraphHasCorrectStartRoute() {
        assertThat(rootNavGraph.route).matches(ROOT)
        assertThat(rootNavGraph.startRoute).isEqualTo(mainNavGraph)
    }

    @Test
    fun rootNavGraphHasCorrectNestedNavGraphs() {
        val expectedNestedNavGraphs = listOf(mainNavGraph)

        assertThat(rootNavGraph.nestedNavGraphs).isEqualTo(expectedNestedNavGraphs)
    }

    @Test
    fun rootNavGraphHasNoDestinations() {
        assertThat(rootNavGraph.destinationsByRoute).isEqualTo(emptyMap<String, DestinationSpec<*>>())
    }
}
