package com.kotlin.graphassignment.presenter

import com.kotlin.graphassignment.tree.Node
import com.kotlin.graphassignment.tree.TreeNodeIterator
import com.kotlin.graphassignment.view.MainView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainPresenterImplTest {
    private val presenter: MainPresenter = MainPresenterImpl()
    @Mock
    private lateinit var mockView: MainView

    @Mock
    private lateinit var mockTreeNodeIterator: TreeNodeIterator<Node>

    @Before
    fun setUp() {
        presenter.setView(mockView)
        presenter.setIterator(mockTreeNodeIterator)
    }

    @Test
    fun uiChangesTest() {
        val rootNode = Node("root", 0)
        val firstNode = Node("first", 0)

        Mockito.`when`(mockTreeNodeIterator.hasNext()).thenReturn(true)
        Mockito.`when`(mockTreeNodeIterator.next()).thenReturn(rootNode)

        Mockito.`when`(mockTreeNodeIterator.hasPrevious()).thenReturn(true)
        Mockito.`when`(mockTreeNodeIterator.previous()).thenReturn(firstNode)

        // call next
        presenter.next()

        Mockito.verify(mockView).nextButtonEnabled(true)
        Mockito.verify(mockView).display(rootNode)

        presenter.previous()
        Mockito.verify(mockView).previousButtonEnabled(true)

    }
}