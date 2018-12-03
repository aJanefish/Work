package zy.walk.com.thewalkers.event;

import java.util.List;

public class EncounterFaceEvent {
    /**
     * status : 1
     * content : [{"robotID":"5","robotCode":"15032047895680730","robotName":"天機一號 (小紫)","robotFrontCamera":"http://10.24.12.12:7080/mjpg.php/1/3/100","robotRearCamera":"http://10.24.12.12:7080/mjpg.php/2/3/100","robotFaceHost":"192.168.201.10","faceUser":"test@megvii.com","facePass":"123456","directionalControl":"","status":"1","createdAt":"1503205450","updatedAt":"1537167510"}]
     */

    private int status;
    private List<ContentBean> content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * robotID : 5
         * robotCode : 15032047895680730
         * robotName : 天機一號 (小紫)
         * robotFrontCamera : http://10.24.12.12:7080/mjpg.php/1/3/100
         * robotRearCamera : http://10.24.12.12:7080/mjpg.php/2/3/100
         * robotFaceHost : 192.168.201.10
         * faceUser : test@megvii.com
         * facePass : 123456
         * directionalControl :
         * status : 1
         * createdAt : 1503205450
         * updatedAt : 1537167510
         */

        private String robotID;
        private String robotCode;
        private String robotName;
        private String robotFrontCamera;
        private String robotRearCamera;
        private String robotFaceHost;
        private String faceUser;
        private String facePass;
        private String directionalControl;
        private String status;
        private String createdAt;
        private String updatedAt;

        public String getRobotID() {
            return robotID;
        }

        public void setRobotID(String robotID) {
            this.robotID = robotID;
        }

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

        public String getRobotFrontCamera() {
            return robotFrontCamera;
        }

        public void setRobotFrontCamera(String robotFrontCamera) {
            this.robotFrontCamera = robotFrontCamera;
        }

        public String getRobotRearCamera() {
            return robotRearCamera;
        }

        public void setRobotRearCamera(String robotRearCamera) {
            this.robotRearCamera = robotRearCamera;
        }

        public String getRobotFaceHost() {
            return robotFaceHost;
        }

        public void setRobotFaceHost(String robotFaceHost) {
            this.robotFaceHost = robotFaceHost;
        }

        public String getFaceUser() {
            return faceUser;
        }

        public void setFaceUser(String faceUser) {
            this.faceUser = faceUser;
        }

        public String getFacePass() {
            return facePass;
        }

        public void setFacePass(String facePass) {
            this.facePass = facePass;
        }

        public String getDirectionalControl() {
            return directionalControl;
        }

        public void setDirectionalControl(String directionalControl) {
            this.directionalControl = directionalControl;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
