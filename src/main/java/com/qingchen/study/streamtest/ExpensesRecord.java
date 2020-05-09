package com.qingchen.study.streamtest;

/**
 * @ClassName ExpensesRecord
 * @description: 用户消费记录
 * @author: WangChen
 * @create: 2020-04-20 13:15
 **/
public class ExpensesRecord {

    private Long id;
    private String name;
    private Long money;
    private String location;
    private Long time;

    public ExpensesRecord(Long id, String name, Long money, String location, Long time) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.location = location;
        this.time = time;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getMoney() { return money; }
    public void setMoney(Long money) { this.money = money; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Long getTime() { return time; }
    public void setTime(Long time) { this.time = time; }

    @Override
    public String toString() {
        return "ExpensesRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", location='" + location + '\'' +
                ", time=" + time +
                '}';
    }
}
