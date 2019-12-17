package com.gmail.jiangyang5157.router_annotation.model;

import static com.gmail.jiangyang5157.router_annotation.utils.Constant.BOOLEAN_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.BYTE_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.CHARACTER_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.DOUBLE_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.FLOAT_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.INTEGER_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.LONG_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.OBJECT_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.PARCELABLE_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.SERIALIZABLE_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.SHORT_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.STRING_CLASSNAME;

public enum TypeKind {
    BYTE(BYTE_CLASSNAME),
    SHORT(SHORT_CLASSNAME),
    INTEGER(INTEGER_CLASSNAME),
    LONG(LONG_CLASSNAME),
    CHARACTER(CHARACTER_CLASSNAME),
    STRING(STRING_CLASSNAME),
    FLOAT(FLOAT_CLASSNAME),
    DOUBLE(DOUBLE_CLASSNAME),
    BOOLEAN(BOOLEAN_CLASSNAME),
    SERIALIZABLE(SERIALIZABLE_CLASSNAME),
    PARCELABLE(PARCELABLE_CLASSNAME),
    OBJECT(OBJECT_CLASSNAME);

    String className;

    public String getClassName() {
        return className;
    }

    public TypeKind setClassName(String className) {
        this.className = className;
        return this;
    }

    TypeKind(String className) {
        this.className = className;
    }

    public static TypeKind parse(String className) {
        for (TypeKind routeType : TypeKind.values()) {
            if (routeType.getClassName().equals(className)) {
                return routeType;
            }
        }
        throw new IllegalArgumentException("Cannot create instance from " + className);
    }
}



