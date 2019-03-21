package zy.walk.com.thewalkers.dagger;

/**
 * 学校
 * */
class School {

    String name;
    String address;
    String schoolPhone;

    public School(String name, String address, String schoolPhone) {
        this.name = name;
        this.address = address;
        this.schoolPhone = schoolPhone;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", schoolPhone='" + schoolPhone + '\'' +
                '}';
    }
}
