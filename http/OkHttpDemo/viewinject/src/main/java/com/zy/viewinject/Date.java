package com.zy.viewinject;

public class Date {

    /**
     * status : 200
     * info : {"robotCode":"888888889","robotName":"天機一號 (小紫)","location":0}
     */

    private int status;
    private InfoBean info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * robotCode : 888888889
         * robotName : 天機一號 (小紫)
         * location : 0
         */

        private String robotCode;
        private String robotName;
        private int location;

        public String getRobotCode() {
            return robotCode;
        }

        public void setRobotCode(String robotCode) {
            this.robotCode = robotCode;
        }

        public String getRobotName() {
            return robotName;
        }

        public void setRobotName(String robotName) {
            this.robotName = robotName;
        }

        public int getLocation() {
            return location;
        }

        public void setLocation(int location) {
            this.location = location;
        }
    }
}
