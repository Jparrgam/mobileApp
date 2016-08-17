package co.com.mobileapp.view

import android.view.View
import co.com.mobileapp.view.model.appModel

/**
 * @author Jaime Gamboa
 */
interface onItemClickListener {
    /**
     * method callBack selected object
     *
     * @param model selected in recycleView
     */
    fun itemListener (model: appModel, view: View)
}