package com.kotlin.graphassignment.tree

interface TreeNodeIterable<T> {
    fun getIterator(): TreeNodeIterator<T>
}