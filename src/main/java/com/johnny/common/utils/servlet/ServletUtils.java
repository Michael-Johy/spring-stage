package com.johnny.common.utils.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: This ServletUtils is used to process with HttpServletRequest/HttpServletResponse related
 * <p>
 * Author: johnny01.yang
 * Date  : 2016-09-08 14:30
 */
public final class ServletUtils {

    private ServletUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);

    // text/plain;charset=UTF-8
    private static final String TEXT_PLAIN_UTF8 = "text/plain;charset=UTF-8";
    private static final String UTF8 = "UTF-8";

    /**
     * 使用HttpServletResponse对象向客户端输出字符串
     */
    public static void responseStr(String strData, HttpServletResponse response) {
        response.setContentType(TEXT_PLAIN_UTF8);
        response.setCharacterEncoding(UTF8);
        response.setHeader("Pragma", "no-cache"); //http1.0 + http 1.1
        response.setHeader("Cache-Control", "no-cache"); //http1.1(新添加)
        response.setDateHeader("Expires", 0L);
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.write(strData);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

}
