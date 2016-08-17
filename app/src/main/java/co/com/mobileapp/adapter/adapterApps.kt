package co.com.mobileapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.mobileapp.R
import co.com.mobileapp.view.model.appModel
import co.com.mobileapp.view.onItemClickListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card_widget_apps.view.*
import java.util.*

/**
 * @author Jaime Gamboa
 * @see RecyclerView.Adapter
 */
class adapterApps : RecyclerView.Adapter<adapterApps.cardViewHolderApps>() {

    private var data_adapter: ArrayList<appModel>? = null
    private var context: Context? = null
    private var onItemListener: onItemClickListener? = null

    fun adapterApps(data_adapter: ArrayList<appModel>, context: Context, onItemListener: onItemClickListener) {
        this.data_adapter = data_adapter
        this.context = context
        this.onItemListener = onItemListener
    }

    override fun onBindViewHolder(holder: cardViewHolderApps?, position: Int) {
        val item = data_adapter!![position]
        val image = item.image
        Glide.with(context).load(image).into(holder!!.Image)
        holder.CompanyName.text = item.artist
        holder.NameApp.text = item.name

        holder.Image.setOnClickListener({
            view
            ->
            this.onItemListener!!.itemListener(item, holder.Image)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): cardViewHolderApps {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.card_widget_apps, parent, false)
        return cardViewHolderApps(v)
    }

    override fun getItemCount(): Int {
        return data_adapter!!.size
    }

    class cardViewHolderApps(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NameApp = itemView.nameApp!!
        val CompanyName = itemView.CompanyName!!
        val Image = itemView.imageApp!!
    }
}