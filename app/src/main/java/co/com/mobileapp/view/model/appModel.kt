package co.com.mobileapp.view.model

import java.io.Serializable

/**
 * @author Jaime Gamboa
 * @see Serializable
 */
data class appModel(var name: String, var image: String, var summary: String, var rights: String, var link: String, var artist : String) : Serializable