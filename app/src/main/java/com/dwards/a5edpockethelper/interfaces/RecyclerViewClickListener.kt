package com.dwards.a5edpockethelper.interfaces

import android.view.View

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClickListener(view: View, id: Int)

    fun onRecyclerViewItemLongClickListener(view: View, id: Int){

    }
}