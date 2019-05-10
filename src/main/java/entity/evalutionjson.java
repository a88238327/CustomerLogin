package entity;

import java.util.ArrayList;

public class evalutionjson {
    public String touxiang;
    public String name;
    public String content;

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public ArrayList<imgjson> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<imgjson> imgs) {
        this.imgs = imgs;
    }

    public evalutionjson(String touxiang, String name, String content, String time, String servicename) {
        this.touxiang = touxiang;
        this.name = name;
        this.content = content;
        this.time = time;
        this.servicename = servicename;
        imgs=new ArrayList<>();
    }
//time 格式：2019/05/05
    public String time;
    public String servicename;
    public ArrayList<imgjson> imgs;
}
