package com.ivianuu.materialchangelog.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.airbnb.epoxy.EpoxyController
import com.ivianuu.materialchangelog.fix
import com.ivianuu.materialchangelog.new
import com.ivianuu.materialchangelog.release
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show.setOnClickListener {
            val controller = epoxyController {
                (0 until 20)
                    .reversed()
                    .forEach {
                        release("Version: 1.0.$it") {
                            new("Added Something")
                            new("Added Something different")
                            fix("Fixed a bug")
                        }
                    }
            }

            MaterialDialog.Builder(this)
                .title("Changelog")
                .positiveText("OK")
                .adapter(controller.adapter, LinearLayoutManager(this))
                .show()
        }
    }

    private inline fun epoxyController(crossinline buildModels: EpoxyController.() -> Unit) =
        object : EpoxyController() {
            override fun buildModels() {
                buildModels.invoke(this)
            }
        }.apply { requestModelBuild() }
}
