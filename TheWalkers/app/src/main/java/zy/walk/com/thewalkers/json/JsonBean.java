package zy.walk.com.thewalkers.json;

import java.util.List;

public class JsonBean {

    /**
     * intent : special_attention
     * date : [{"location":"left","type":"qr","url":"xxxxxx"},{"location":"center","type":"image","url":"xxxxxx"},{"location":"right","type":"null","url":"null"}]
     */

    private String intent;
    private List<DateBean> date;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean {
        /**
         * location : left
         * type : qr
         * url : xxxxxx
         */

        private String location;
        private String type;
        private String url;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
