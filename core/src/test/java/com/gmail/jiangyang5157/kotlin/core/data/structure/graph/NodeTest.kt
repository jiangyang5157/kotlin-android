package com.gmail.jiangyang5157.kotlin.core.data.structure.graph

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by yjiang on 30/06/17.
 */
class NodeTest {

    @Test
    fun test_id_Int() {
        val id = 1
        val node : Node<Int> = Node(id)
        assertEquals(id, node.id)
    }

    @Test
    fun test_id_Double() {
        val id = 1.1
        val node : Node<Double> = Node(id)
        assertEquals(id, node.id)
    }

    @Test
    fun test_id_String() {
        val id = "A"
        val node : Node<String> = Node(id)
        assertEquals(id, node.id)
    }

}