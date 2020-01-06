package com.kotlin.graphassignment

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kotlin.graphassignment.utils.JsonUtils
import junit.framework.Assert.assertNotNull
import org.json.JSONException
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileNotFoundException

@RunWith(AndroidJUnit4::class)
class LoadJsonInstrumentedTest {
    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun loadJsonFromAssetsTest() {
        val json = JsonUtils.loadJSONFromAsset(appContext, "input.json")
        assertNotNull(json)
    }

    @Test(expected = JSONException::class)
    fun loadJsonFromAssetsInvalidFileTest() {
        val json = JsonUtils.loadJSONFromAsset(appContext, "test_invalid_input.json")
        assertNotNull(json)
    }

    @Test(expected = FileNotFoundException::class)
    fun loadJsonFromAssetsErrorTest() {
        val json = JsonUtils.loadJSONFromAsset(appContext, "abc.json")
        assertNotNull(json)
    }
}