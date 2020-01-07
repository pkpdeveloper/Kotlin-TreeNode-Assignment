package com.kotlin.graphassignment.tree

interface TreeNodeIterator<T> {
    fun hasNext(): Boolean
    fun hasPrevious(): Boolean
    fun next(): T
    fun previous(): T
}