package org.ups.greensky.common

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<MODEL, INPUT_EVENT : BaseInputEvent>(itemView: View) : RecyclerView.ViewHolder(itemView),
    InputPublisher<INPUT_EVENT> {

    protected var model: MODEL? = null

    @CallSuper
    open fun bind(model: MODEL) {
        this.model = model
    }

}