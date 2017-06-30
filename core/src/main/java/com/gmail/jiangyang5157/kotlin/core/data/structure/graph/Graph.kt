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
     * sources HashMap<Node.id, HashMap<Node.id, Edge>>  maps a node to parents with edge.
     */
    val sources: HashMap<T, HashMap<T, Edge>> = HashMap()

    /**
     * targets HashMap<Node.id, HashMap<Node.id, Edge>>  maps a node to children with edge.
     */
    val targets: HashMap<T, HashMap<T, Edge>> = HashMap()

    fun getNode(id: T): Node<T>? {
        return nodes[id]
    }

    fun getSource(id: T): HashMap<T, Edge>? {
        return sources[id]
    }

    fun getTargets(id: T): HashMap<T, Edge>? {
        return targets[id]
    }

    fun getEdge(src: T, tgt: T): Edge? {
        val children = targets[src] ?: return null
        return children[tgt]
    }

    fun addNode(node: Node<T>) {
        nodes.put(node.id, node)
    }

    fun updateNode(node: Node<T>) {
        nodes.replace(node.id, node)
    }

    fun deleteNode(id: T) {
        nodes.remove(id)
        sources.remove(id)
        targets.remove(id)

        // Remove edges which source is the node
        sources.forEach { _, parents ->
            parents.remove(id)
        }

        // remove edges which target is the node
        targets.forEach { _, children ->
            children.remove(id)
        }
    }

}
