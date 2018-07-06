package com.ivianuu.materialchangelog

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_changelog_release.*
import java.util.*

/**
 * Release model
 */
data class ReleaseModel(
    val headerText: CharSequence? = null,
    val headerTextRes: Int = 0,
    val models: List<EpoxyModel<*>>
) : EpoxyModelWithHolder<ChangelogEpoxyHolder>() {

    init {
        id(UUID.randomUUID().toString())
        layout(R.layout.item_changelog_release)
    }

    override fun bind(holder: ChangelogEpoxyHolder) {
        super.bind(holder)

        when {
            headerText != null -> holder.header_text.text = headerText
            headerTextRes != 0 -> holder.header_text.setText(headerTextRes)
        }
    }

    override fun unbind(holder: ChangelogEpoxyHolder) {
        super.unbind(holder)
        holder.header_text.text = null
    }

    override fun getDefaultLayout() = R.layout.item_changelog_release

    override fun createNewHolder() = ChangelogEpoxyHolder()

    class Builder(private val epoxyController: EpoxyController) {

        private var headerText: CharSequence? = null
        private var headerTextRes = 0
        var models = mutableListOf<EpoxyModel<*>>()

        fun headerText(headerText: CharSequence?) {
            this.headerText = headerText
        }

        fun headerTextRes(headerTextRes: Int) {
            this.headerTextRes = headerTextRes
        }

        fun addModels(vararg models: EpoxyModel<*>) {
            this.models.addAll(models)
        }

        fun addModels(models: Collection<EpoxyModel<*>>) {
            this.models.addAll(models)
        }

        fun build(): ReleaseModel {
            return ReleaseModel(headerText, headerTextRes, models)
        }

        fun buildAndAdd(): ReleaseModel {
            val model = build()
            model.addTo(epoxyController)
            models.forEach { it.addTo(epoxyController) }
            return model
        }
    }

}

inline fun EpoxyController.release(
    headerText: CharSequence,
    init: ReleaseModel.Builder.() -> Unit
) {
    ReleaseModel.Builder(this)
        .apply { headerText(headerText) }
        .apply(init)
        .buildAndAdd()
}

inline fun EpoxyController.release(headerTextRes: Int, init: ReleaseModel.Builder.() -> Unit) {
    ReleaseModel.Builder(this)
        .apply { headerTextRes(headerTextRes) }
        .apply(init)
        .buildAndAdd()
}