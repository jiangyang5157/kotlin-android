package com.gmail.jiangyang5157.kotlin.core.data.structure.graph

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by yjiang on 30/06/17.
 */
class EdgeTest {

    @Test
    fun test_w() {
        val w = 1.1
        val edge : Edge = Edge(w)
        assertEquals(w, edge.w)
    }

}