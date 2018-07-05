package com.ivianuu.materialchangelog

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * @author Manuel Wrage (IVIanuu)
 */
class ChangelogEpoxyHolder : EpoxyHolder(), LayoutContainer {

    override lateinit var containerView: View

    override fun bindView(itemView: View) {
        containerView = itemView
    }
}