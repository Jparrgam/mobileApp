package co.com.mobileapp.view

import org.json.JSONObject

/**
 * @author Jaime Gamboa
 */
interface View {

    /**
     * show progress bar
     */
    fun showProgress()

    /**
     * method execute http request failed

     * @param failure message failed
     */
    fun onFailureRequest(failure: String)

    /**
     * method execute http request success code 200

     * @param jResponse response api
     */
    fun onSuccessRequest(jResponse: JSONObject)
}
