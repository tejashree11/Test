package com.example.randomdogassignment

import android.graphics.Bitmap
import androidx.collection.LruCache


class MyCache private constructor() {
    val lru: LruCache<Any, Any>

    fun saveBitmapToCahche(key: String, bitmap: Bitmap) {
        try {
            instance!!.lru.put(key, bitmap)
        } catch (e: Exception) {
        }
    }

    fun retrieveBitmapFromCache(key: String): Bitmap? {
        try {
            return instance!!.lru[key] as Bitmap?
        } catch (e: Exception) {
        }
        return null
    }

    companion object {
        var instance: MyCache? = null
            get() {
                if (field == null) {
                    field = MyCache()
                }
                return field
            }
            private set
    }

    init {
        lru = LruCache(1024)
    }
}