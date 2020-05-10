package com.gmail.jiangyang5157.android.router.error

class RouterFragmentDslException : RouterException {

    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
