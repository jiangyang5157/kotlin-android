package com.gmail.jiangyang5157.router_annotation.model;

import static com.gmail.jiangyang5157.router_annotation.utils.Constant.ACTIVITY_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.FRAGMENT_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.FRAGMENT_V4_CLASSNAME;

public enum RouteType {
    ACTIVITY(ACTIVITY_CLASSNAME),
    FRAGMENT(FRAGMENT_CLASSNAME),
    FRAGMENT_V4(FRAGMENT_V4_CLASSNAME);

    String className;

    public String getClassName() {
        return className;
    }

    public RouteType setClassName(String className) {
        this.className = className;
        return this;
    }

    RouteType(String className) {
        this.className = className;
    }

    public static RouteType parse(String className) {
        for (RouteType routeType : RouteType.values()) {
            if (routeType.getClassName().equals(className)) {
                return routeType;
            }
        }
        throw new IllegalArgumentException("Cannot create instance from " + className);
    }
}
