package com.kotlin.graphassignment.presenter

import com.kotlin.graphassignment.tree.Node
import com.kotlin.graphassignment.tree.TreeNodeIterator
import com.kotlin.graphassignment.view.MainView

class MainPresenterImpl : MainPresenter {
    private lateinit var view: MainView
    private lateinit var treeNodeIterator: TreeNodeIterator<Node>
    override fun setView(mainView: MainView) {
        this.view = mainView
    }

    override fun setIterator(treeNodeIterator: TreeNodeIterator<Node>) {
        this.treeNodeIterator = treeNodeIterator
    }

    override fun next() {
        view.nextButtonEnabled(treeNodeIterator.hasNext())
        view.display(treeNodeIterator.next())
    }

    override fun previous() {
        view.previousButtonEnabled(treeNodeIterator.hasPrevious())
        view.display(treeNodeIterator.previous())
    }
}