package com.mobile.pablo.youtube.graph

import com.google.common.truth.Truth.assertThat
import com.mobile.pablo.youtube.nav.graph.NavGraphs
import com.ramcosta.composedestinations.spec.DestinationSpec
import org.junit.Test

class NavGraphsTest {

    private val navGraph: NavGraphs = NavGraphs

    companion object {

        const val MAIN = "main"
        const val MAIN_HOME_SCREEN_ROUTE = "main/home_screen"
        const val ROOT = "root"
    }

    @Test
    fun mainNavGraphHasCorrectStartRoute() {
        assertThat(navGraph.main.route).matches(MAIN)
        assertThat(navGraph.main.startRoute.route).isEqualTo(MAIN_HOME_SCREEN_ROUTE)
    }

    @Test
    fun rootNavGraphHasCorrectStartRoute() {
        assertThat(navGraph.root.route).matches(ROOT)
        assertThat(navGraph.root.startRoute).isEqualTo(navGraph.main)
    }

    @Test
    fun rootNavGraphHasCorrectNestedNavGraphs() {
        val expectedNestedNavGraphs = listOf(navGraph.main)

        assertThat(navGraph.root.nestedNavGraphs).isEqualTo(expectedNestedNavGraphs)
    }

    @Test
    fun rootNavGraphHasNoDestinations() {
        assertThat(navGraph.root.destinationsByRoute).isEqualTo(emptyMap<String, DestinationSpec<*>>())
    }
}