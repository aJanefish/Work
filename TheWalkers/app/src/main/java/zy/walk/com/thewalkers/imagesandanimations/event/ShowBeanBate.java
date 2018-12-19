package zy.walk.com.thewalkers.imagesandanimations.event;

import java.util.List;

public class ShowBeanBate {

    /**
     * 数据地址:http://encounter-msc.singou.mo/api/tool/getAuxiliaryAll
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
         * intention_name : visit_msc
         * auxiliary_info : []
         * questionList : []
         */

        private String intention_name;
        private String auxiliary_info;
        private List<?> questionList;

        public String getIntention_name() {
            return intention_name;
        }

        public void setIntention_name(String intention_name) {
            this.intention_name = intention_name;
        }

        public String getAuxiliary_info() {
            return auxiliary_info;
        }

        public void setAuxiliary_info(String auxiliary_info) {
            this.auxiliary_info = auxiliary_info;
        }

        public List<?> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<?> questionList) {
            this.questionList = questionList;
        }
    }





}
