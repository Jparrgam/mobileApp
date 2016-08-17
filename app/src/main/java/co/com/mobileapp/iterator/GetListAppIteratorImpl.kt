package co.com.mobileapp.iterator

import co.com.mobileapp.configuration.MainApplication
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

/**
 * @author Jaime Gamboa
 * @see GetListAppIterator
 */
class GetListAppIteratorImpl : GetListAppIterator {

    /**
     * method execute bd
     */
    override fun OnExecutePersistent(onRequestFinished: GetListAppIterator.onRequestFinished) {
        val cursor = MainApplication.getCursor()
        if(cursor.moveToFirst()) {
            do {
               val json = JSONObject(cursor.getString(0))
                onRequestFinished.onSuccessRequest(json)
            } while (cursor.moveToNext())
        } else {
            onRequestFinished.onFailureRequest("")
        }
    }

    /**
     * http get executor
     *
     * @param urlService, service execute
     */
    override fun onExecuteService(urlService: String, onRequestFinished: GetListAppIterator.onRequestFinished) {
        val httpClient = AsyncHttpClient()
        httpClient.get(urlService, object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                if (statusCode == 200) {
                    val response = JSONObject(kotlin.text.String(responseBody));
                    onRequestFinished.onSuccessRequest(response)
                    MainApplication.insertItem(response)
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable) {
                if(error.message != null) {
                    onRequestFinished.onFailureRequest(error.message.toString())
                }
            }
        })
    }
}
