package co.com.mobileapp

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import co.com.mobileapp.adapter.adapterApps
import co.com.mobileapp.presenter.GetListAppPresenter
import co.com.mobileapp.presenter.GetListAppPresenterImpl
import co.com.mobileapp.util.util
import co.com.mobileapp.view.View
import co.com.mobileapp.view.model.appModel
import co.com.mobileapp.view.onItemClickListener
import com.afollestad.materialdialogs.MaterialDialog
import org.json.JSONObject
import top.wefor.circularanim.CircularAnim
import top.wefor.circularanim.CircularAnim.OnAnimationEndListener
import java.util.*

/**
 * @author Jaime Gamboa
 * @see AppCompatActivity
 * @see View
 * @see GetListAppPresenter
 * @see GetListAppPresenterImpl
 * @see onItemClickListener
 */
class ListApp : AppCompatActivity(), View, onItemClickListener {

    var recyclerViewApps: RecyclerView? = null
    val itemToShow: ArrayList<appModel> = ArrayList<appModel>()
    var adapterItem : adapterApps? = null

    //local variables
    private var dialog : MaterialDialog? = null
    private var getListAppPresenter : GetListAppPresenter? = null
    private var recyclerLinearManager : LinearLayoutManager? = null
    private var recyclerGridManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_app)

        recyclerViewApps = findViewById(R.id.recyclerViewApps) as RecyclerView

        getListAppPresenter = GetListAppPresenterImpl (this)
        getListAppPresenter!!.setContext(this)

        if(recyclerViewApps != null) {
            recyclerViewApps!!.setHasFixedSize(true)
        }

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerGridManager = GridLayoutManager(this, 4)
            recyclerViewApps!!.layoutManager = recyclerGridManager
        } else {
            recyclerLinearManager = LinearLayoutManager(this)
            recyclerViewApps!!.layoutManager = recyclerLinearManager
        }
    }

    override fun onResume() {
        super.onResume()
        getListAppPresenter!!.GetListApp(util.constants.urlService)
    }

    override fun onDestroy() {
        super.onDestroy()
        getListAppPresenter!!.onDestroy()
    }

    /**
     * show progress bar
     */
    override fun showProgress() {
        dialog = MaterialDialog.Builder(this).content(R.string.progress_dialog).progress(true, 0).cancelable(false).show()
    }

    /**
     * method execute http request failed
     * @param failure message failed
     */
    override fun onFailureRequest(failure: String) {
        dialog!!.dismiss()
        Log.e(ListApp::class.java.toString(), "error http request "+ failure)
        Toast.makeText(this,"Error inesperado por favor intentalo de nuevo",Toast.LENGTH_LONG).show()
    }

    /**
     * method execute http request success code 200
     * @param jResponse response api
     */
    override fun onSuccessRequest(jResponse: JSONObject) {
        dialog!!.dismiss()
        val jEntry = jResponse.getJSONObject("feed").getJSONArray("entry")
        for (i in 0..(jEntry.length() - 1)) {
            val item = jEntry.getJSONObject(i)
            itemToShow.add(appModel(
                    item.getJSONObject("im:name").getString("label"),
                    item.getJSONArray("im:image").getJSONObject(0).getString("label"),
                    item.getJSONObject("summary").getString("label"),
                    item.getJSONObject("rights").getString("label"),
                    item.getString("link"),
                    item.getJSONObject("im:artist").getString("label")
            ))
        }
        adapterItem = adapterApps()
        adapterItem!!.adapterApps(itemToShow, this, this)
        recyclerViewApps!!.adapter = adapterItem

        Toast.makeText(this, "Click sobre la imagen para ver el detalle de la aplicación", Toast.LENGTH_LONG).show()
    }

    /**
     * method callBack selected object
     *
     * @param model selected in recycleView
     */
    override fun itemListener(model: appModel, view: android.view.View) {
        CircularAnim.fullActivity(this, view).go {
            startActivity(Intent(this@ListApp, DetailsApp::class.java).putExtra("ItemSelected",model))
        }
    }
}
