package org.bobochen.dbmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PersonDb {
    @JsonProperty(value = "Id")
    private int id;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Age")
    private int age;

    @JsonProperty(value = "CreateTime")
    private LocalDateTime createtime;

    @JsonProperty(value = "IsUsed")
    private boolean isUsed;

    @JsonProperty(value = "Price")
    private BigDecimal price;

    @JsonProperty(value = "LongNum")
    private long longnum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getLongnum() {
        return longnum;
    }

    public void setLongnum(long longnum) {
        this.longnum = longnum;
    }
}
