package co.com.mobileapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import co.com.mobileapp.util.util

/**
 * @author Jaime Gamboa
 * @see BroadcastReceiver
 */
class WifiBroadcastReceiver : BroadcastReceiver() {

    companion object {
        /**
         * method validate status wifi

         * @param context status actual of this application
         * *
         * @return validate network state
         */
        fun isNetworkConnect(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            if (info == null || !info.isConnected || !info.isAvailable) {
                return false
            }
            return true
        }
    }

    /**
     * onReceive method action settings
     *
     * @param context status actual of this application
     * @param intent call BroadcastReceiver
     */
    override fun onReceive(context: Context, intent: Intent?) {
        if(intent != null) {
            if(intent.action.equals(util.constants.WIFI_STATE_CHANGED) || intent.action.equals(util.constants.CONNECTIVITY_CHANGE) || intent.action.equals(util.constants.STATE_CHANGE)) {
                val isConnect = isNetworkConnect(context)
                if(!isConnect) {
                    Toast.makeText(context,"No tienes conexion a internet o datos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}