package Bean;

public class DB {
    // mysql 5.x
    public static final String driver = "com.mysql.cj.jdbc.Driver";

    // mysql 8.x+
//    public static final String driver = "com.mysql.cj.jdbc.Driver";

    // remote configuration
//        static final String url = "jdbc:mysql://138.197.223.75:3306/underground?useUnicode=true&characterEncoding=utf-8";
//        static final String user = "user";
//        static final String password = "password";

    // local configuration
//    public static final String url = "jdbc:mysql://localhost:3306/underground?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
//    public static final String user = "root";
//    public static final String password = "hjxmt007";

    // xinwei configuration
    public static final String url = "jdbc:mysql://localhost:3306/lbs?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL&allowPublicKeyRetrieval=true&ONLY_FULL_GROUP_BY=false";
    public static final String user = "xzz";
    public static final String password = "xzzxwmsl";
}
