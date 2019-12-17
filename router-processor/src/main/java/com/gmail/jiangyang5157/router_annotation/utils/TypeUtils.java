package com.gmail.jiangyang5157.router_annotation.utils;


import com.gmail.jiangyang5157.router_annotation.model.TypeKind;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.gmail.jiangyang5157.router_annotation.utils.Constant.BOOLEAN_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.BYTE_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.CHARACTER_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.DOUBLE_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.FLOAT_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.INTEGER_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.LONG_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.SHORT_CLASSNAME;
import static com.gmail.jiangyang5157.router_annotation.utils.Constant.STRING_CLASSNAME;

public class TypeUtils {

    private Types types;

    private TypeMirror serializableType;

    private TypeMirror parcelableType;

    public TypeUtils(Types types, Elements elements) {
        this.types = types;
        serializableType = elements.getTypeElement(TypeKind.SERIALIZABLE.getClassName()).asType();
        parcelableType = elements.getTypeElement(TypeKind.PARCELABLE.getClassName()).asType();
    }

    /**
     * Diagnostics out the true java type
     *
     * @param element Raw type
     * @return Type class of java
     */
    public int typeExchange(Element element) {
        TypeMirror typeMirror = element.asType();

        if (typeMirror.getKind().isPrimitive()) {
            return element.asType().getKind().ordinal();
        }

        switch (typeMirror.toString()) {
            case BYTE_CLASSNAME:
                return TypeKind.BYTE.ordinal();
            case SHORT_CLASSNAME:
                return TypeKind.SHORT.ordinal();
            case INTEGER_CLASSNAME:
                return TypeKind.INTEGER.ordinal();
            case LONG_CLASSNAME:
                return TypeKind.LONG.ordinal();
            case CHARACTER_CLASSNAME:
                return TypeKind.CHARACTER.ordinal();
            case STRING_CLASSNAME:
                return TypeKind.STRING.ordinal();
            case FLOAT_CLASSNAME:
                return TypeKind.FLOAT.ordinal();
            case DOUBLE_CLASSNAME:
                return TypeKind.DOUBLE.ordinal();
            case BOOLEAN_CLASSNAME:
                return TypeKind.BOOLEAN.ordinal();
            default:
                if (types.isSubtype(typeMirror, serializableType)) {
                    return TypeKind.SERIALIZABLE.ordinal();
                } else if (types.isSubtype(typeMirror, parcelableType)) {
                    return TypeKind.PARCELABLE.ordinal();
                } else {
                    return TypeKind.OBJECT.ordinal();
                }
        }
    }
}
