package com.gmail.jiangyang5157.kotlin_core.utils

/**
 * Created by Yang Jiang on June 27, 2017
 */
object RegexUtils {

    // 19941115124526
    val DATE_FILE_NAME = "yyyyMMddHHmmss"

    // Nov 15, 1994 12:45:26
    val DATE_LABEL = "MMM dd, yyyy HH:mm:ss"

    // Tue, 15 Nov 1994 12:45:26 GMT
    val DATE_HTTP = "EEE, dd MMM yyyy HH:mm:ss zzz"

    /**
     * Accurate regex that captures the four parts of the IP Address
     */
    val IP_ADDRESS = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"

    /**
     * Accurate regex that captures the Port
     */
    val IP_PORT = "^([\\d]{1,5})$"

    val URL = "(https?:\\/\\/)+[\\w.:\\-]+(/[\\w.:\\-~!@#$%&+=|;?,]+)*"
}
