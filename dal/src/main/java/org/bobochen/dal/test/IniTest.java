package org.bobochen.dal.test;


import org.ini4j.Ini;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class IniTest {
    public static void main(String[] a) {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource("");
            Ini ini = new Ini();
            File file = new File(url.getPath()+ "/conf.ini");
            ini.load(new FileReader(file));
            Ini.Section dopey = ini.get("mysql");
            System.out.println(dopey.get("Host"));

        }catch (Exception ee)
        {
            ee.printStackTrace();

        }
    }
}
