package co.com.mobileapp.presenter

import android.content.Context
import co.com.mobileapp.iterator.GetListAppIterator
import co.com.mobileapp.iterator.GetListAppIteratorImpl
import co.com.mobileapp.receiver.WifiBroadcastReceiver
import org.json.JSONObject

/**
 * @author Jaime Gamboa
 * @see GetListAppPresenter
 * @see GetListAppIterator.onRequestFinished
 */
class GetListAppPresenterImpl(private var listAppView: co.com.mobileapp.view.View?) : GetListAppPresenter, GetListAppIterator.onRequestFinished  {


    private val getListIterator: GetListAppIterator
    private var contextApplication : Context? = null

    init {
        this.getListIterator = GetListAppIteratorImpl()
    }

    override fun GetListApp(urlService: String) {
        if(listAppView != null) {
            listAppView!!.showProgress()

            if(WifiBroadcastReceiver.isNetworkConnect(contextApplication!!)) {
                getListIterator.onExecuteService(urlService, this)
            } else {
                getListIterator.OnExecutePersistent(this)
            }
        }
    }

    override fun onDestroy() {
        listAppView = null
    }

    override fun onFailureRequest(error: String) {
        if(listAppView != null) {
            listAppView!!.onFailureRequest (error)
        }
    }

    override fun onSuccessRequest(jsResponse: JSONObject) {
        if(listAppView != null) {
            listAppView!!.onSuccessRequest (jsResponse)
        }
    }

    /**
     * context application
     */
    override fun setContext(context: Context) {
        contextApplication = context
    }
}
