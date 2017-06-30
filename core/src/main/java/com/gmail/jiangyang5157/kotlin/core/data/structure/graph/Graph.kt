package com.gmail.jiangyang5157.kotlin.core.data.structure.graph

/**
 * Created by Yang Jiang on June 28, 2017
 */
open class Graph<T> {

    /**
     * nodes HashMap<Node.id, Node> stores all nodes.
     */
    val nodes: HashMap<T, Node<T>> = HashMap()

    /**
     * sourcesMap HashMap<Node.id, HashMap<Node.id, Edge>>  maps a node to parents with edge.
     */
    val sourcesMap: HashMap<T, HashMap<T, Edge>> = HashMap()

    /**
     * targetsMap HashMap<Node.id, HashMap<Node.id, Edge>>  maps a node to children with edge.
     */
    val targetsMap: HashMap<T, HashMap<T, Edge>> = HashMap()

    fun getNode(id: T): Node<T>? {
        return nodes[id]
    }

    fun getSources(id: T): HashMap<T, Edge>? {
        return sourcesMap[id]
    }

    fun getTargets(id: T): HashMap<T, Edge>? {
        return targetsMap[id]
    }

    fun getEdge(src: T, tgt: T): Edge? {
        val children = targetsMap[src] ?: return null
        return children[tgt]
    }

    fun addNode(node: Node<T>) {
        nodes[node.id] = node
    }

    fun deleteNode(id: T) {
        nodes.remove(id)
        sourcesMap.remove(id)
        targetsMap.remove(id)

        // Remove edges which source is the node
        sourcesMap.forEach { _, parents ->
            parents.remove(id)
        }

        // remove edges which target is the node
        targetsMap.forEach { _, children ->
            children.remove(id)
        }
    }

    fun addEdge(src: T, tgt: T, edge: Edge) {
        sourcesMap[tgt]?.set(src, edge)
        targetsMap[src]?.set(tgt, edge)
    }

    fun addEdge(src: T, tgt: T) {
        addEdge(src, tgt, Edge())
    }

    fun deleteEdge(src: T, tgt: T) {
        sourcesMap[tgt]?.remove(src)
        targetsMap[src]?.remove(tgt)
    }

}
