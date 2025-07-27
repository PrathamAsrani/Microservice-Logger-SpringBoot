package com.logger.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger{
    public static <T> Logger getLogger(Class<T> clazz){
        return LoggerFactory.getLogger(clazz);
    }
}
