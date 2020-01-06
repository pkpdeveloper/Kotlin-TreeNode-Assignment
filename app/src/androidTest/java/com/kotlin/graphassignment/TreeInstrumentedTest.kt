package com.kotlin.graphassignment

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kotlin.graphassignment.tree.Node
import com.kotlin.graphassignment.tree.TreeNode
import com.kotlin.graphassignment.tree.processJsonArray
import com.kotlin.graphassignment.utils.JsonUtils
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TreeInstrumentedTest {
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun treeInitTest() {
        val treeNode = TreeNode<String, Int>("This is a string at ROOT LO", 0)
        assertNotNull(treeNode)
        assertEquals(0, treeNode.getChildren().size)

        treeNode.addChild(TreeNode("This is the first level 1 string", 1))
        assertEquals(1, treeNode.getChildren().size)

        val nodeList = mutableListOf<TreeNode<String, Int>>()
        nodeList.add(TreeNode("This is the first level 2 string", 2))
        nodeList.add(TreeNode("This is the second level 2 string", 2))
        treeNode.addChildren(nodeList, TreeNode("root L1", 1))

        assertEquals(3, treeNode.getChildren().size)
    }

    @Test
    fun treeJsonToTreeNodeInputTest() {

        val json = JsonUtils.loadJSONFromAsset(appContext, "input.json")
        assertNotNull(json)
        val level = 0
        val treeNode = Node(json.getString("uiValue"), level)
        assertNotNull(treeNode)

        val children = json.getJSONArray("children")
        assertNotNull(children)

        //process json data and add it to treeNode
        treeNode.processJsonArray(children, level)

        // Test root node
        testNodeExists(treeNode, "This is a string at ROOT level", 0)

        // Test first L1 node
        testNodeExists(treeNode, "This is the first level 1 string", 1)

        // Test second L1 node
        testNodeExists(treeNode, "This is the second level 1 string", 1)

        // Test first L2 node
        testNodeExists(treeNode, "This is the first level 2 string", 2)

        // Test second L2 node
        testNodeExists(treeNode, "This is the second level 2 string", 2)

        // Test first L3 node
        testNodeExists(treeNode, "This is the first level 3 string", 3)

        // Test second L3 node
        testNodeExists(treeNode, "This is the second level 3 string", 3)

    }

    @Test
    fun sortedTreeNodeInputTest() {

        val json = JsonUtils.loadJSONFromAsset(appContext, "input.json")
        assertNotNull(json)
        val level = 0
        val treeNode = Node(json.getString("uiValue"), level)
        assertNotNull(treeNode)

        val children = json.getJSONArray("children")
        assertNotNull(children)

        //process json data and add it to treeNode
        treeNode.processJsonArray(children, level)

        val sortedTreeNodeList = treeNode.getSortedTreeNodeList()

        // Test expected values
        assertEquals(0, sortedTreeNodeList[0].getLevel())
        assertEquals(1, sortedTreeNodeList[1].getLevel())
        assertEquals(1, sortedTreeNodeList[2].getLevel())
        assertEquals(2, sortedTreeNodeList[3].getLevel())
        assertEquals(2, sortedTreeNodeList[4].getLevel())
        assertEquals(3, sortedTreeNodeList[5].getLevel())
        assertEquals(3, sortedTreeNodeList[6].getLevel())
    }

    private fun testNodeExists(rootNode: Node, expectedValue: String, expectedLevel: Int) {
        val resultNode = rootNode.find(expectedValue, expectedLevel)
        assertEquals(expectedValue, resultNode?.getValue())
        assertEquals(expectedLevel, resultNode?.getLevel())
    }
}
