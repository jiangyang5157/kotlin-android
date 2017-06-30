package com.gmail.jiangyang5157.kotlin.core.data.structure.graph

/**
 * Created by Yang Jiang on June 28, 2017
 */
open class Graph<T> {

    /**
     * nodes HashMap<Node.id, Node> stores all nodes.
     */
    val nodes : HashMap<T, Node<T>> = HashMap<T, Node<T>>()

    /**
     * sources HashMap<Node.id, HashMap<Node.id, Edge>>  maps a node to parents with edge.
     */
    val sources : HashMap<T, HashMap<T, Edge>> = HashMap<T, HashMap<T, Edge>>()

    /**
     * targets HashMap<Node.id, HashMap<Node.id, Edge>>  maps a node to children with edge.
     */
    val targets : HashMap<T, HashMap<T, Edge>> = HashMap<T, HashMap<T, Edge>>()



}
