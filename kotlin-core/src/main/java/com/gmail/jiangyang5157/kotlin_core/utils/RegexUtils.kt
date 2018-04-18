package com.gmail.jiangyang5157.kotlin_core.utils

/**
 * Created by Yang Jiang on June 27, 2017
 */
object RegexUtils {

    // 19941115124526
    const val DATE_FILE_NAME: String = "yyyyMMddHHmmss"

    // Nov 15, 1994 12:45:26
    const val DATE_LABEL: String = "MMM dd, yyyy HH:mm:ss"

    // Tue, 15 Nov 1994 12:45:26 GMT
    const val DATE_HTTP: String = "EEE, dd MMM yyyy HH:mm:ss zzz"

    /**
     * Accurate regex that captures the four parts of the IP Address
     */
    const val IP_ADDRESS: String = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"

    /**
     * Accurate regex that captures the Port
     */
    const val IP_PORT: String = "^([\\d]{1,5})$"

    const val URL: String = "(https?:\\/\\/)+[\\w.:\\-]+(/[\\w.:\\-~!@#$%&+=|;?,]+)*"
}
