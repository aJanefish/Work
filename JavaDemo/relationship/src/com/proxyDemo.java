package com;

import com.utils.Print;
import sun.nio.ch.Net;

interface Network{
    public void browse();
}

class Real implements Network{

    @Override
    public void browse() {
        Print.println("上网浏览信息");
    }
}


class Proxy implements Network{

    private Network network;

    public Proxy(Network network) {
        this.network = network;
    }

    public void check(){
        Print.println("检查用户是否合法");
    }

    @Override
    public void browse() {
        this.check();
        this.network.browse();
    }
}


public class proxyDemo {

    public static void main(String[] args){
        Network network = null;
        network = new Proxy(new Real());
        network.browse();
    }
}
