package org.ups.greensky.common

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<MODEL, INPUT_EVENT : BaseInputEvent>(val itemView: View) : RecyclerView.ViewHolder(itemView),
    InputPublisher<INPUT_EVENT> {

    @CallSuper
    abstract fun bind(model: MODEL)


}