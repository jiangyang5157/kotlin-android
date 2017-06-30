package com.gmail.jiangyang5157.kotlin.core.data.structure.graph

import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by Yang Jiang on June 30, 2017
 */
class GraphTest {

    /*
    https://github.com/jiangyang5157/go-graph/tree/master/testdata/graph_04.png
    "graph_04": {
        "A": {
            "B": 7,
            "C": 9,
            "F": 20
        },
        "B": {
            "A": 7,
            "C": 10,
            "D": 15
        },
        "C": {
            "A": 9,
            "B": 10,
            "D": 11,
            "E": 30,
            "F": 2
        },
        "D": {
            "B": 15,
            "C": 11,
            "E": 2
        },
        "E": {
            "F": 9,
            "C": 30,
            "D": 2
        },
        "F": {
            "A": 20,
            "C": 2,
            "E": 9
        }
    },
     */
    var graph : Graph<String> = Graph()

    @Before
    fun setUp() {
        graph.reset()

        graph.addNode(Node("A"))
        graph.addNode(Node("B"))
        graph.addNode(Node("C"))
        graph.addNode(Node("D"))
        graph.addNode(Node("E"))
        graph.addNode(Node("F"))

        graph.addEdge("A", "B", Edge(7.0))
        graph.addEdge("A", "C", Edge(9.0))
        graph.addEdge("A", "F", Edge(20.0))

        graph.addEdge("B", "A", Edge(7.0))
        graph.addEdge("B", "C", Edge(10.0))
        graph.addEdge("B", "D", Edge(15.0))

        graph.addEdge("C", "A", Edge(9.0))
        graph.addEdge("C", "B", Edge(10.0))
        graph.addEdge("C", "D", Edge(11.0))
        graph.addEdge("C", "E", Edge(30.0))
        graph.addEdge("C", "F", Edge(2.0))

        graph.addEdge("D", "B", Edge(15.0))
        graph.addEdge("D", "C", Edge(11.0))
        graph.addEdge("D", "E", Edge(2.0))

        graph.addEdge("E", "F", Edge(9.0))
        graph.addEdge("E", "C", Edge(30.0))
        graph.addEdge("E", "D", Edge(2.0))

        graph.addEdge("F", "A", Edge(20.0))
        graph.addEdge("F", "C", Edge(2.0))
        graph.addEdge("F", "E", Edge(9.0))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getNode() {
        println(graph)
    }

    @Test
    fun getSources() {
    }

    @Test
    fun getTargets() {
    }

    @Test
    fun getEdge() {
    }

    @Test
    fun addNode() {
    }

    @Test
    fun deleteNode() {
    }

    @Test
    fun addEdge() {
    }

    @Test
    fun deleteEdge() {
    }

}