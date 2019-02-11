package data;

/**
 * 每一个族人的信息
 * */
public class ClanBean {

    String name;
    //农历 或者 阳历
    String birthday;
    //头像
    String pictures;

    //性别
    int gender;


    ClanBean father;
    ClanBean mother;

    ClanBean[] childs;

    public ClanBean(String name) {
        this.name = name;
    }


}
