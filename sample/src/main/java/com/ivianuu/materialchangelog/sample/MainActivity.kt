package com.ivianuu.materialchangelog.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.ivianuu.materialchangelog.change
import com.ivianuu.materialchangelog.fix
import com.ivianuu.materialchangelog.new
import com.ivianuu.materialchangelog.release
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.buildModelsWith2 {
            release("Version: 1.0.1") {
                change("Added Something")
                fix("Fixed a bug")
            }

            release("Version: 1.0.0") {
                new("Added something")
                fix("Fixed a bug")
            }
        }
    }

    fun EpoxyRecyclerView.buildModelsWith2(init: EpoxyController.() -> Unit) {
        buildModelsWith(init)
    }
}
