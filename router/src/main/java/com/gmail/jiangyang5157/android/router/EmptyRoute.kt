package com.gmail.jiangyang5157.android.router

import com.gmail.jiangyang5157.android.router.core.Route

/**
 * # EmptyRoute
 * Object representing an "empty" [Route].
 * e.g. for transitions that expect a [Route] for either the "fromRoute" or "toRoute" param,
 * when there is no "fromRoute" or "toRoute" (because it may be the first route to be pushed)
 */
object EmptyRoute : Route
