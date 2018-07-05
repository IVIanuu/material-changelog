/*
 * Copyright 2018 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.materialchangelog

import android.graphics.Color
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_changelog_change.*
import java.util.*

/**
 * Change model
 */
data class ChangeModel(
    val changeText: CharSequence? = null,
    val changeTextRes: Int = 0
) : EpoxyModelWithHolder<ChangelogEpoxyHolder>() {

    init {
        id(UUID.randomUUID().toString())
        layout(R.layout.item_changelog_change)
    }

    override fun bind(holder: ChangelogEpoxyHolder) {
        super.bind(holder)

        when {
            changeText != null -> holder.change_text.text = changeText
            changeTextRes != 0 -> holder.change_text.setText(changeTextRes)
        }
    }

    override fun unbind(holder: ChangelogEpoxyHolder) {
        super.unbind(holder)
        holder.change_text.text = null
    }

    override fun getDefaultLayout() = R.layout.item_changelog_change

    override fun createNewHolder() = ChangelogEpoxyHolder()

    class Builder {

        private var changeText: CharSequence? = null
        private var changeTextRes = 0

        fun changeText(changeText: CharSequence?) {
            this.changeText = changeText
        }

        fun changeTextRes(changeTextRes: Int) {
            this.changeTextRes = changeTextRes
        }

        fun build() = ChangeModel(changeText, changeTextRes)
    }
}

inline fun EpoxyController.change(init: ChangeModel.Builder.() -> Unit) {
    ChangeModel.Builder()
        .apply(init)
        .build()
        .also { it.addTo(this) }
}

inline fun EpoxyController.change(changeText: CharSequence) {
    ChangeModel.Builder()
        .apply { changeText(changeText) }
        .build()
        .also { it.addTo(this) }
}

inline fun EpoxyController.change(changeTextRes: Int) {
    ChangeModel.Builder()
        .apply { changeTextRes(changeTextRes) }
        .build()
        .also { it.addTo(this) }
}

inline fun EpoxyController.new(text: CharSequence) {
    change(
        android.text.SpannableStringBuilder()
            .bold { color(Color.BLACK) { append("New: ") } }
            .append(text)
    )
}

inline fun EpoxyController.fix(text: CharSequence) {
    change(
        android.text.SpannableStringBuilder()
            .bold { color(Color.BLACK) { append("Fix: ") } }
            .append(text)
    )
}