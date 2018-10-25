package demo.okhttp.zy.com.okhttpdemo.event;

public class ClientEvent {
    public String response;

    public ClientEvent(String response) {
        this.response = "short:"+response;
    }
}
