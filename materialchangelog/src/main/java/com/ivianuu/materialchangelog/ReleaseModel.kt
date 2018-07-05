package com.ivianuu.materialchangelog

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_changelog_release.*
import java.util.*

/**
 * Release model
 */
data class ReleaseModel(
    val headerText: CharSequence? = null,
    val headerTextRes: Int = 0
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

    class Builder {

        private var headerText: CharSequence? = null
        private var headerTextRes = 0

        fun headerText(headerText: CharSequence?) {
            this.headerText = headerText
        }

        fun headerTextRes(headerTextRes: Int) {
            this.headerTextRes = headerTextRes
        }

        fun build() = ReleaseModel(headerText, headerTextRes)
    }

}

inline fun EpoxyController.release(init: ReleaseModel.Builder.() -> Unit) {
    ReleaseModel.Builder()
        .apply(init)
        .build()
        .also { it.addTo(this) }
}

inline fun EpoxyController.release(headerText: CharSequence) {
    ReleaseModel.Builder()
        .apply { headerText(headerText) }
        .build()
        .also { it.addTo(this) }
}

inline fun EpoxyController.release(headerTextRes: Int) {
    ReleaseModel.Builder()
        .apply { headerTextRes(headerTextRes) }
        .build()
        .also { it.addTo(this) }
}