package com.kotlin.graphassignment.presenter

import com.kotlin.graphassignment.tree.Node
import com.kotlin.graphassignment.tree.TreeNodeIterator
import com.kotlin.graphassignment.view.MainView

interface MainPresenter {
    fun setView(mainView: MainView)
    fun setIterator(treeNodeIterator: TreeNodeIterator<Node>)
    fun next()
    fun previous()
}