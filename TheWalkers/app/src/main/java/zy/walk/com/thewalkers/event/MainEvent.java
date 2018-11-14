package zy.walk.com.thewalkers.event;

public class MainEvent {

    public String title;
    public String content;
    public String packageName;
    public String className;

    public MainEvent(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.packageName = builder.packageName;
        this.className = builder.className;
    }

    @Override
    public String toString() {
        return "MainEvent{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", packageName='" + packageName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public static class Builder{

        public Builder() {

        }

        private String title;
        private String content;
        private String packageName;
        private String className;



        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder content(String content){
            this.content = content;
            return this;
        }
        public Builder packageName(String packageName){
            this.packageName = packageName;
            return this;
        }

        public Builder className(String className){
            this.className = className;
            return this;
        }

        public MainEvent bulde(){
            return new MainEvent(this);
        }

    }


}
