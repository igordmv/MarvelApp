package com.mobile.mavelapp.presenter.encryption

import android.util.Log
import java.security.NoSuchAlgorithmException

class Md5 {
    fun generateMd5FromString(s: String): String {
        val md5 = "MD5"
        try {

            val digest = java.security.MessageDigest
                .getInstance(md5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }
}