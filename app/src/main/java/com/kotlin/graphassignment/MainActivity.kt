package com.kotlin.graphassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextSwitcher
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {

    @BindView(R.id.btnPrevious)
    lateinit var btnPrevious: Button

    @BindView(R.id.btnNext)
    lateinit var btnNext: Button

    @BindView(R.id.textSwitcher)
    lateinit var textSwitcher: TextSwitcher


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }
}
