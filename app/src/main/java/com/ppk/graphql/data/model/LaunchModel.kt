package com.ppk.graphql.data.model

import java.io.Serializable

/**
 * @param name Name of the launch
 * @param date Launch date
 * @param photoUrl url of the photo
 */
data class LaunchModel(var name:String, var date: String, var photoUrl:String):Serializable
