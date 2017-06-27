package com.gmail.jiangyang5157.kotlin.base.utils

import java.util.regex.Pattern

/**
 * Created by Yang Jiang on June 27, 2017
 */
object UrlUtils {

    /**
     * Returns the first found url in the src, returns null if cannot find
     */
    fun extract(src: CharSequence): CharSequence? {
        val pattern = Pattern.compile(RegexUtils.URL)
        val matcher = pattern.matcher(src)
        val find = matcher.find()
        if (!find) {
            return null
        }
        return src.subSequence(matcher.start(), matcher.end())
    }

    /**
     * Returns True if src contains url
     */
    fun contains(src: CharSequence): Boolean {
        return extract(src) != null
    }

}