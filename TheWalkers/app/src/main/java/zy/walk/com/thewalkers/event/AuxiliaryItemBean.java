package zy.walk.com.thewalkers.event;

import java.util.List;

public class AuxiliaryItemBean {

    /**
     * zh : [{"location":"left","type":"qr","url":"2222","urlType":0},{"location":"center","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0},{"location":"right","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0}]
     * en : [{"location":"left_en","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0},{"location":"center_en","type":"image","url":"http://encounter-msc.singou.mo/res/upload/img/15424320167ec6ac9e9c7222b698f6abed61d6cdd4.jpg","urlType":1},{"location":"right_en","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0}]
     * pt : [{"location":"left_pt","type":"image","url":"http://encounter-msc.singou.mo/res/upload/img/1542432016acbb7d19ba7ba19e41ffc779ccb6ce3c.png","urlType":1},{"location":"center_pt","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0},{"location":"right_pt","type":"image","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0}]
     * contentState : 1
     */

    private int contentState; // 1 代表信息 0 代表没有信息
    private List<ZhBean> zh;
    private List<EnBean> en;
    private List<PtBean> pt;

    public int getContentState() {
        return contentState;
    }

    public void setContentState(int contentState) {
        this.contentState = contentState;
    }

    public List<ZhBean> getZh() {
        return zh;
    }

    public void setZh(List<ZhBean> zh) {
        this.zh = zh;
    }

    public List<EnBean> getEn() {
        return en;
    }

    public void setEn(List<EnBean> en) {
        this.en = en;
    }

    public List<PtBean> getPt() {
        return pt;
    }

    public void setPt(List<PtBean> pt) {
        this.pt = pt;
    }

    public static class ZhBean {
        public static final String CENTER = "center";
        public static final String LEFT = "left";
        public static final String RIGHT = "right";
        /**
         * location : left
         * type : qr
         * url : 2222
         * urlType : 0
         */

        private String location;
        private String type;
        private String url;
        private int urlType;

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

        public int getUrlType() {
            return urlType;
        }

        public void setUrlType(int urlType) {
            this.urlType = urlType;
        }
    }

    public static class EnBean {
        public static final String CENTER = "center_en";
        public static final String LEFT = "left_en";
        public static final String RIGHT = "right_en";
        /**
         * location : left_en
         * type : qr
         * url : 我是空的，請手動輸入或選擇照片傳入
         * urlType : 0
         */

        private String location;
        private String type;
        private String url;
        private int urlType;

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

        public int getUrlType() {
            return urlType;
        }

        public void setUrlType(int urlType) {
            this.urlType = urlType;
        }
    }

    public static class PtBean {
        public static final String CENTER = "center_pt";
        public static final String LEFT = "left_pt";
        public static final String RIGHT = "right_pt";
        /**
         * location : left_pt
         * type : image
         * url : http://encounter-msc.singou.mo/res/upload/img/1542432016acbb7d19ba7ba19e41ffc779ccb6ce3c.png
         * urlType : 1
         */

        private String location;
        private String type;
        private String url;
        private int urlType;

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

        public int getUrlType() {
            return urlType;
        }

        public void setUrlType(int urlType) {
            this.urlType = urlType;
        }
    }
}
