package com.gmail.jiangyang5157.router_annotation.utils;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class ProcessorLogger {

    private Messager messager;

    public ProcessorLogger(Messager messager) {
        this.messager = messager;
    }

    public void info(CharSequence info) {
        if (StringUtils.isNotEmpty(info)) {
            messager.printMessage(Diagnostic.Kind.NOTE, formatString(info));
        }
    }

    public void warning(CharSequence warning) {
        if (StringUtils.isNotEmpty(warning)) {
            messager.printMessage(Diagnostic.Kind.WARNING, formatString(warning));
        }
    }

    public void error(CharSequence error) {
        if (StringUtils.isNotEmpty(error)) {
            messager.printMessage(Diagnostic.Kind.ERROR, formatString(error));
        }
    }

    public void error(Throwable error) {
        if (error != null) {
            messager.printMessage(Diagnostic.Kind.ERROR, formatString(error.getMessage()) + formatStackTrace(error.getStackTrace()));
        }
    }

    public String formatString(CharSequence charSequence) {
        return String.format("router-processor:: >>> %s <<<\n", charSequence);
    }

    private String formatStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stackTrace) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
