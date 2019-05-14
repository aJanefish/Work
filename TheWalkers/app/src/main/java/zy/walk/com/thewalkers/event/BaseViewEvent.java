package zy.walk.com.thewalkers.event;

public class BaseViewEvent {

    public enum ViewEvent{
        Alpha,
        Background,
        Clickable,
        ContentDescription,
        Padding,
        Rotation,
        RotationX,
        RotationY,
        ScaleX,
        ScaleY
    }

    private String name;
    private ViewEvent event;
    private String des;

    public BaseViewEvent(ViewEvent event,String des) {
        this.event = event;
        this.name = event.name();
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public ViewEvent getEvent() {
        return event;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        return "BaseViewEvent{" +
                "name='" + name + '\'' +
                ", event=" + event +
                '}';
    }
}
