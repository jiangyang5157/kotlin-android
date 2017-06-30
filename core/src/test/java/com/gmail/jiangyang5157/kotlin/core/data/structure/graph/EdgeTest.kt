package com.gmail.jiangyang5157.kotlin.core.data.structure.graph

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by yjiang on 30/06/17.
 */
class EdgeTest {

    @Test
    fun test_w_Int() {
        val w = 1
        val edge : Edge<Int> = Edge(w)
        assertEquals(w, edge.w)
    }

    @Test
    fun test_w_Double() {
        val w = 1.1
        val edge : Edge<Double> = Edge(w)
        assertEquals(w, edge.w)
    }

    @Test
    fun test_w_String() {
        val w = "W"
        val edge : Edge<String> = Edge(w)
        assertEquals(w, edge.w)
    }

}