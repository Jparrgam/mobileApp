package co.com.mobileapp.iterator


import org.json.JSONObject

/**
 * @author Jaime Gamboa
 */
interface GetListAppIterator {

    /**
     * This interface contains the methods of execution of a request
     */
    interface onRequestFinished {
        fun onFailureRequest (error: String)
        fun onSuccessRequest (jsResponse: JSONObject)
    }

    /**
     * method to perform services HTTPGet
     */
    fun onExecuteService (urlService: String, onRequestFinished: onRequestFinished)

    /**
     * method execute bd
     */
    fun OnExecutePersistent (onRequestFinished: onRequestFinished)
}
