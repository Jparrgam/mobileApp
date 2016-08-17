package co.com.mobileapp.presenter

import android.content.Context

/**
 * @author Jaime Gamboa
 */
interface GetListAppPresenter {

    /**
     * Get List App
     *
     * @param urlService: url service execute
     */
    fun GetListApp (urlService : String)

    /**
     * onDestroy interface
     */
    fun onDestroy()

    /**
     * context application
     */
    fun setContext (context: Context)
}