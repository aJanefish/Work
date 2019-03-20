package zy.walk.com.thewalkers.dagger;

import javax.inject.Inject;

public class User {
    private String name;

    @Inject
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
