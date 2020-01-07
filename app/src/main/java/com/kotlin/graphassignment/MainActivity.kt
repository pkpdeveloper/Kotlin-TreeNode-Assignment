package com.kotlin.graphassignment

import android.os.Bundle
import android.widget.Button
import android.widget.TextSwitcher
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.kotlin.graphassignment.presenter.MainPresenter
import com.kotlin.graphassignment.presenter.MainPresenterImpl
import com.kotlin.graphassignment.tree.Node
import com.kotlin.graphassignment.tree.processJsonArray
import com.kotlin.graphassignment.utils.JsonUtils
import com.kotlin.graphassignment.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    @BindView(R.id.btnPrevious)
    lateinit var btnPrevious: Button

    @BindView(R.id.btnNext)
    lateinit var btnNext: Button

    @BindView(R.id.textSwitcher)
    lateinit var textSwitcher: TextSwitcher

    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        presenter = MainPresenterImpl()
        presenter.setView(this)
        val json = JsonUtils.loadJSONFromAsset(this, "input.json")
        val level = 0
        val treeNode = Node(json.getString("uiValue"), level)
        val children = json.getJSONArray("children")

        //process json data and add it to treeNode
        treeNode.processJsonArray(children, level)

        presenter.setIterator(treeNode.getIterator())

        btnNext.setOnClickListener {
            presenter.next()
        }

        btnPrevious.setOnClickListener {
            presenter.previous()
        }
        presenter.next()
    }

    override fun display(node: Node) {
        textSwitcher.setText(node.getValue())
    }

    override fun previousButtonEnabled(isEnabled: Boolean) {
        btnPrevious.isEnabled = isEnabled
    }

    override fun nextButtonEnabled(isEnabled: Boolean) {
        btnNext.isEnabled = isEnabled
    }
}
