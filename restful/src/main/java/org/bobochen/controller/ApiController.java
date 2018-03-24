package org.bobochen.controller;

import com.github.brainlag.nsq.NSQProducer;
import net.weedfs.client.RequestResult;
import net.weedfs.client.WeedFSClient;
import org.bobochen.controller.base.Result;
import org.bobochen.dal.PersonDal;
import org.bobochen.dbmodel.PersonDb;
import org.bobochen.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.List;


@Controller
@RequestMapping("/api")
public class ApiController {
    @RequestMapping(value = "/Hello", method = {RequestMethod.POST})
    @ResponseBody
    public Person Hello(@RequestBody Person p) {

        List<PersonDb> plist = PersonDal.getAllPerson();
        return p;
    }

    @RequestMapping(value = "/GetAllPerson", method = {RequestMethod.POST})
    @ResponseBody
    public Result GetAllPerson() {

        List<PersonDb> plist = PersonDal.getAllPerson();
        return new Result(200, "", plist);
    }


    @RequestMapping(value = "/getNameById", method = {RequestMethod.POST})
    @ResponseBody
    public Result getNameById(int id) {

        String name = PersonDal.getNameById(id);

        return new Result(200, "", name);
    }


    @RequestMapping(value = "/testRedis", method = {RequestMethod.POST})
    @ResponseBody
    public Result testRedis(int id) {
        Jedis jedis = new Jedis("192.168.178.128", 6379);
        //权限认证
        jedis.auth("123456");
        jedis.set("name", id + "");
        String gid = "fsdfsfsf  " + jedis.get("name");

        return new Result(200, "", gid);
    }

    @RequestMapping(value = "/testNsqPro", method = {RequestMethod.POST})
    @ResponseBody
    public Result testNsqPro(String word) {
        try {
            NSQProducer producer = new NSQProducer().addAddress("192.168.178.128", 4150);
            producer.start();

            producer.produce("JavaTopic", (word).getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(200, "", word);
    }


    @RequestMapping(value = "/testWeedfs", method = {RequestMethod.POST})
    @ResponseBody
    public Result testWeedfs() {

            WeedFSClient client = new WeedFSClient("192.168.178.128", "9333");
            RequestResult rr = client.write("d:/3csharp.docx");


        return new Result(200, "", rr.getFid());
    }

    @RequestMapping(value = "/getIdById", method = {RequestMethod.POST})
    @ResponseBody
    public Result getIdById(int id) {

        int rid = PersonDal.getIdById(id);

        return new Result(200, "", rid);
    }

    @RequestMapping(value = "/index")
    public String index(@RequestBody Person p) {
        //do something
        return "testpage";
    }
}
