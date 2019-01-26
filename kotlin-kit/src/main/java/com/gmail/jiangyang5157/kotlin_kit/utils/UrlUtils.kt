package com.gmail.jiangyang5157.kotlin_kit.utils

import java.util.regex.Pattern

/**
 * Created by Yang Jiang on June 27, 2017
 */
object UrlUtils {

    /**
     * Returns True if src is url
     */
    fun matches(src: CharSequence): Boolean {
        val pattern = Pattern.compile(RegexUtils.URL)
        val matcher = pattern.matcher(src)
        return matcher.matches()
    }

    /**
     * Returns True if src contains url
     */
    fun contains(src: CharSequence): Boolean {
        val pattern = Pattern.compile(RegexUtils.URL)
        val matcher = pattern.matcher(src)
        return matcher.find()
    }

    /**
     * Returns the first found url in the src, returns null if cannot find
     */
    fun extractFirst(src: CharSequence): CharSequence? {
        val pattern = Pattern.compile(RegexUtils.URL)
        val matcher = pattern.matcher(src)
        val find = matcher.find()
        if (find) {
            return src.subSequence(matcher.start(), matcher.end())
        } else {
            return null
        }
    }
}