package com.gmail.jiangyang5157.android.router.error

import com.gmail.jiangyang5157.android.router.core.Route

class FragmentMappingMissingException(route: Route) : RouterException(
    """
    Missing fragment mapping for route $route.
    Consider implementing `FragmentRoute`, specifying a `FragmentMap` or declaring it via DSL:
    FragmentRouter { 
        routing {
            route<${route::class.java.simpleName}> { [TODO] }
        }
    }
""".trimIndent()
)
