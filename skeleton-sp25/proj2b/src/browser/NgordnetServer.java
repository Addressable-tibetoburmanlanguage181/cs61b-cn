package browser;

import static spark.Spark.*;

/**
 * 由 hug 创建。
 */
public class NgordnetServer {
    public void register(String URL, NgordnetQueryHandler nqh) {
        get(URL, nqh);
    }

    public void startUp() {
        staticFiles.externalLocation("static");

        /* 允许所有来源的请求（因为这不是一个需要身份验证的服务器，所以我们不需要
         * 担心 CSRF 攻击）。 */
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });
    }
}
