package co.com.mobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import co.com.mobileapp.view.model.appModel
import com.bumptech.glide.Glide

/**
 * @author Jaime Gamboa
 * @see AppCompatActivity
 */
class DetailsApp : AppCompatActivity() {

    var DetailAppName: TextView? = null
    var DetailCompanyName: TextView? = null
    var DetailSummary: TextView? = null
    var imgApp: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_app)

        DetailAppName = findViewById(R.id.DetailAppName) as TextView
        DetailCompanyName = findViewById(R.id.DetailCompanyName) as TextView
        DetailSummary = findViewById(R.id.DetailSummary) as TextView
        imgApp = findViewById(R.id.imgApp) as ImageView

        DetailSummary!!.movementMethod = ScrollingMovementMethod()
    }


    override fun onResume() {
        super.onResume()
        val appModelObject = intent.getSerializableExtra("ItemSelected") as appModel
        DetailAppName!!.text = appModelObject.name
        DetailCompanyName!!.text = appModelObject.rights
        DetailSummary!!.text = appModelObject.summary
        Glide.with(this@DetailsApp).load(appModelObject.image).into(imgApp)
    }
}
