package com.kotlin.graphassignment.view

import com.kotlin.graphassignment.tree.Node

interface MainView {
    fun display(node: Node)
    fun previousButtonEnabled(isEnabled: Boolean)
    fun nextButtonEnabled(isEnabled: Boolean)
}