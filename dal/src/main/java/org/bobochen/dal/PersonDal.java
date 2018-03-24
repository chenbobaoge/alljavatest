package org.bobochen.dal;

import org.bobochen.dal.tool.MysqlJDBCHelper;
import org.bobochen.dbmodel.PersonDb;
import org.ini4j.Ini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;


public class PersonDal {

    public static List<PersonDb> getAllPerson() {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("");
            Ini ini = new Ini();
            File file = new File(url.getPath() + "/conf.ini");
            ini.load(new FileReader(file));
            Ini.Section dopey = ini.get("mysql");
            System.out.println(dopey.get("Host"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<PersonDb> plist = MysqlJDBCHelper.queryList("select * from `user`", PersonDb.class);
        return plist;
    }

    public static String getNameById(int id) {
        String name = MysqlJDBCHelper.queryObject("select name from `user` where id=" + id, String.class);
        return name;
    }

    public static int getIdById(int id) {
        int rid = MysqlJDBCHelper.queryObject("select id from `user` where id=" + id, Integer.class);
        return rid;
    }
}
