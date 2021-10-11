package com.hithy.common.constant;

/**
 * 常量
 *
 * @author luozh
 * @date 2021/10/7 16:03
 */
public interface ApsConstant {
    /**
     * PASSWORD_MAX_LEN
     */
    Integer PASSWORD_MAX_LEN = 16;

    interface Redis {
        /**
         * redis-OK
         */
        String OK = "OK";

        /**
         * redis过期时间，以秒为单位，一分钟
         */
        int EXRP_MINUTE = 60;

        /**
         * redis过期时间，以秒为单位，一小时
         */
        int EXRP_HOUR = 60 * 60;

        /**
         * redis过期时间，以秒为单位，一天
         */
        int EXRP_DAY = 60 * 60 * 24;

        /**
         * redis-key-前缀-shiro:cache:
         */
        String PREFIX_APS_CACHE = "aps:cache:";

        /**
         * redis-key-前缀-shiro:access_token:
         */
        String PREFIX_APS_ACCESS_TOKEN = "aps:access_token:";

        /**
         * redis-key-前缀-shiro:refresh_token:
         */
        String PREFIX_APS_REFRESH_TOKEN = "aps:refresh_token:";
    }

    interface JWT {
        /**
         * JWT-account:
         */
        String ACCOUNT = "account";

        /**
         * JWT-currentTimeMillis:
         */
        String CURRENT_TIME_MILLIS = "currentTimeMillis";
    }


    interface Es {
        String APS_PROJECT = "aps_project";
        String APS_PROJECT_ALIAS = "aps_alias";
    }

    interface Register {
        /**
         * 手机
         */
        String PHONE = "phone";
        /**
         * 邮箱
         */
        String EMAIL = "email";
        /**
         *
         */
        String QQ = "qq";
    }


}
