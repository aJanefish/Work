package zy.walk.com.thewalkers.event;

import java.io.Serializable;
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

    public static class ContentBean implements Serializable {
        /**
         * intention_name : visi_kidsWorld
         * auxiliary_info : {"zh":[{"location":"left","type":"qr","url":"w22","urlType":0},{"location":"center","type":"image","url":"http:\/\/encounter-msc.singou.mo\/res\/upload\/img\/15425118837ec6ac9e9c7222b698f6abed61d6cdd4.jpg","urlType":1},{"location":"right","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0}],"en":[{"location":"left_en","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0},{"location":"center_en","type":"image","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0},{"location":"right_en","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0}],"pt":[{"location":"left_pt","type":"image","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0},{"location":"center_pt","type":"qr","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0},{"location":"right_pt","type":"image","url":"我是空的，請手動輸入或選擇照片傳入","urlType":0}],"contentState":1}
         * questionList : [{"question_name":"345342ee","intention_answer":"423525","language":"pt"},{"question_name":"4242","intention_answer":"5345","language":"en"},{"question_name":"中文语料3233w","intention_answer":"32423","language":"zh"}]
         */

        private String intention_name;
        private String auxiliary_info;
        private List<QuestionListBean> questionList;

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

        public List<QuestionListBean> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<QuestionListBean> questionList) {
            this.questionList = questionList;
        }

        public static class QuestionListBean {
            /**
             * question_name : 345342ee
             * intention_answer : 423525
             * language : pt
             */

            private String question_name;
            private String intention_answer;
            private String language;

            public String getQuestion_name() {
                return question_name;
            }

            public void setQuestion_name(String question_name) {
                this.question_name = question_name;
            }

            public String getIntention_answer() {
                return intention_answer;
            }

            public void setIntention_answer(String intention_answer) {
                this.intention_answer = intention_answer;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            @Override
            public String toString() {
                return "QuestionListBean{" +
                        "question_name='" + question_name + '\'' +
                        ", intention_answer='" + intention_answer + '\'' +
                        ", language='" + language + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "intention_name='" + intention_name + '\'' +
                    ", auxiliary_info='" + auxiliary_info + '\'' +
                    ", questionList=" + questionList +
                    '}';
        }
    }
}
