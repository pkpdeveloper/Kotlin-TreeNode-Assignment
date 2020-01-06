package com.kotlin.graphassignment.tree

import org.json.JSONArray
import org.json.JSONObject

typealias Node = TreeNode<String, Int>

class TreeNode<T, Int>(private val value: String, private val level: Int) {
    private var parent: TreeNode<T, Int>? = null

    private var children = mutableSetOf<TreeNode<T, Int>>()

    fun addChild(node: TreeNode<T, Int>) {
        children.add(node)
        node.parent = this
    }

    fun addChildren(nodeList: List<TreeNode<T, Int>>, element: TreeNode<T, Int>) {
        nodeList.forEach {
            children.add(it)
        }
        element.parent = this
    }

    override fun toString(): String {
        var s = "value = $value and level = $level"
        if (children.isNotEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }

    fun getChildren(): MutableSet<TreeNode<T, Int>> {
        return children
    }

    fun getValue(): String {
        return value
    }

    fun getLevel(): Int {
        return level
    }

    fun find(value: String, level: Int): TreeNode<T, Int>? {
        if (this.level == level && this.value == value) {
            return this
        }

        //recursive find node
        this.getChildren().forEach {
            findRecursiveNode(it, value, level)?.let {
                return it
            }
        }
        return null
    }

    private fun findRecursiveNode(
        node: TreeNode<T, Int>,
        value: String,
        level: Int
    ): TreeNode<T, Int>? {
        if (node.level == level && node.value == value) {
            return node
        }
        return null
    }

    fun getSortedTreeNodeList(): List<TreeNode<T, Int>> {
        val sortedSet = mutableSetOf<TreeNode<T, Int>>()
        sortedSet.add(this)
        processChildNodes(sortedSet, this.getChildren())
        return sortedSet.sortedBy { it.getLevel().toString() }
    }

    private fun processChildNodes(
        sortedSet: MutableSet<TreeNode<T, Int>>,
        children: MutableSet<TreeNode<T, Int>>
    ) {
        children.forEach {
            sortedSet.add(it)
            processChildNodes(sortedSet, it.getChildren())
        }
    }
}

fun Node.processJsonArray(jsonArray: JSONArray, level: kotlin.Int) {
    for (index in 0 until jsonArray.length()) {
        when (val json = jsonArray[index]) {
            is JSONObject -> processJsonObject(json, level + 1)
            is JSONArray -> processJsonArray(json, level + 1)

        }
    }
}

private fun Node.processJsonObject(
    jsonObject: JSONObject,
    level: Int
) {
    addChild(TreeNode(jsonObject.getString("uiValue"), level))
}
