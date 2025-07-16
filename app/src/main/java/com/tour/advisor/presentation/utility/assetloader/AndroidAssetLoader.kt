package com.tour.advisor.presentation.utility.assetloader

import android.content.Context

class AndroidAssetLoader(private val context: Context) : AssetLoader {
    override fun loadJson(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}