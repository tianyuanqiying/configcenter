package com.cloud.study.constants;

/**
 * 时间常量
 * @author user
 */
public class Constants {
    public static final String FIXED_DELAY_TIME = "fixedDelayTime";
    /**
     * 默认500毫秒
     */
    public static final Integer FIXED_DELAY_TIME_DEFAULT_VAL = 500;

    /**
     * 长轮训key
     */
    public static final String LONG_POLLING_TIMEOUT = "Long-Pulling-Timeout";
    /**
     * 默认30s
     */
    public static final Integer LONG_POLLING_TIMEOUT_DEFAULT_VAL = 30 * 1000;


    /**
     * Http长轮训中 监听请求的配置；
     */
    public static final String PROBE_MODIFY_REQUEST = "Listening-Configs";


    public static final String CLIENT_APP_NAME = "Client-AppName";

    public static final String TEMP_APP_NAME = "TEMP_APP_NAME";

    public static final String QUERY_CONFIG = "/config/get";
    public static final String UPDATE_CONFIG = "/config/get";
    public static final String DELETE_CONFIG = "/config/delete";

}
