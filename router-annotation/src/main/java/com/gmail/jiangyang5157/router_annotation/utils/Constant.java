package com.gmail.jiangyang5157.router_annotation.utils;

public class Constant {


    // Generate
    public static final String PROJECT = "Router";
    public static final String SEPARATOR = "::";
    public static final String TAG = PROJECT + SEPARATOR + "####";


    public static final String WARNING_TIPS = "DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY ROUTER.";
    public static final String METHOD_LOAD_INTO = "loadInto";
    public static final String METHOD_INJECT = "inject";
    public static final String NAME_OF_ROOT = PROJECT + SEPARATOR + "Root";
    public static final String NAME_OF_PROVIDER = PROJECT + SEPARATOR + "Providers";
    public static final String NAME_OF_GROUP = PROJECT + SEPARATOR + "Group" + SEPARATOR;
    public static final String NAME_OF_INTERCEPTOR = PROJECT + SEPARATOR + "Interceptors";
    public static final String NAME_OF_AUTOWIRED = SEPARATOR + PROJECT + SEPARATOR + "Autowired";

    // System interface
    public static final String ACTIVITY = "android.app.Activity";
    public static final String FRAGMENT = "android.app.Fragment";
    public static final String FRAGMENT_V4 = "android.support.v4.app.Fragment";
    public static final String SERVICE = "android.app.Service";
    public static final String PARCELABLE = "android.os.Parcelable";

    // Java type
    public static final String BYTE = "java.lang.Byte";
    public static final String SHORT = "java.lang.Short";
    public static final String INTEGER = "java.lang.Integer";
    public static final String LONG = "java.lang.Long";
    public static final String FLOAT = "java.lang.Float";
    public static final String DOUBEL = "java.lang.Double";
    public static final String BOOLEAN = "java.lang.Boolean";
    public static final String CHAR = "java.lang.Character";
    public static final String STRING = "java.lang.String";
    public static final String SERIALIZABLE = "java.io.Serializable";

    // Custom interface
    private static final String FACADE_PACKAGE = "com.alibaba.android.arouter.facade";
    private static final String TEMPLATE_PACKAGE = ".template";
    private static final String SERVICE_PACKAGE = ".service";
    private static final String MODEL_PACKAGE = ".model";
    public static final String IPROVIDER = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IProvider";
    public static final String IPROVIDER_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IProviderGroup";
    public static final String IINTERCEPTOR = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IInterceptor";
    public static final String IINTERCEPTOR_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IInterceptorGroup";
    public static final String ITROUTE_ROOT = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IRouteRoot";
    public static final String IROUTE_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IRouteGroup";
    public static final String ISYRINGE = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".ISyringe";
    public static final String JSON_SERVICE = FACADE_PACKAGE + SERVICE_PACKAGE + ".SerializationService";
    public static final String TYPE_WRAPPER = FACADE_PACKAGE + MODEL_PACKAGE + ".TypeWrapper";

    // Log
    static final String PREFIX_OF_LOGGER = PROJECT + "::Compiler ";
    public static final String NO_MODULE_NAME_TIPS = "These no module name, at 'build.gradle', like :\n" +
        "android {\n" +
        "    defaultConfig {\n" +
        "        ...\n" +
        "        javaCompileOptions {\n" +
        "            annotationProcessorOptions {\n" +
        "                arguments = [ROUTER_MODULE_NAME: project.getName()]\n" +
        "            }\n" +
        "        }\n" +
        "    }\n" +
        "}\n";

    // Options of processor
    public static final String KEY_MODULE_NAME = "ROUTER_MODULE_NAME";
    public static final String KEY_GENERATE_DOC_NAME = "ROUTER_GENERATE_DOC";

    public static final String VALUE_ENABLE = "enable";

    // Annotation type
    public static final String ANNOTATION_TYPE_INTECEPTOR = FACADE_PACKAGE + ".annotation.Interceptor";
    public static final String ANNOTATION_TYPE_ROUTE = FACADE_PACKAGE + ".annotation.Route";
    public static final String ANNOTATION_TYPE_AUTOWIRED = FACADE_PACKAGE + ".annotation.Autowired";
}
