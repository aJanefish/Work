package zy.walk.com.thewalkers.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.event.AuxiliaryItemBean;
import zy.walk.com.thewalkers.event.ShowBeanBate;

public class ShowMessageAdapter extends  RecyclerView.Adapter<ShowMessageAdapter.ViewHolder> {


    private final List<ShowBeanBate.ContentBean> contentBeanList;

    public ShowMessageAdapter(List<ShowBeanBate.ContentBean> contentBeanList) {
        this.contentBeanList = contentBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_auxiliary_info_show_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShowBeanBate.ContentBean contentBean = contentBeanList.get(position);
        holder.intent.setText(contentBean.getIntention_name());

        AuxiliaryItemBean auxiliaryItemBean = new Gson().fromJson(contentBean.getAuxiliary_info(), AuxiliaryItemBean.class);
        List<AuxiliaryItemBean.ZhBean> zhBeans = auxiliaryItemBean.getZh();
        StringBuilder auxStringBuilder = new StringBuilder();
        auxStringBuilder.append("CN\n");
        for (AuxiliaryItemBean.ZhBean zhBean : zhBeans) {
            auxStringBuilder.append(zhBean.getLocation()+":"+zhBean.getType()+":"+zhBean.getUrl()+"\n");
        }
        List<AuxiliaryItemBean.EnBean> enBeans = auxiliaryItemBean.getEn();
        auxStringBuilder.append("EN\n");
        for (AuxiliaryItemBean.EnBean enBean : enBeans) {
            auxStringBuilder.append(enBean.getLocation()+":"+enBean.getType()+":"+enBean.getUrl()+"\n");
        }
        List<AuxiliaryItemBean.PtBean> ptBeans = auxiliaryItemBean.getPt();
        auxStringBuilder.append("PT\n");
        for (AuxiliaryItemBean.PtBean ptBean : ptBeans) {
            auxStringBuilder.append(ptBean.getLocation()+":"+ptBean.getType()+":"+ptBean.getUrl()+"\n");
        }
        holder.auxiliary.setText(auxStringBuilder.toString());

        List<ShowBeanBate.ContentBean.QuestionListBean> questionList = contentBean.getQuestionList();
        StringBuilder questionListBeanStringBuilder = new StringBuilder();
        questionListBeanStringBuilder.append("Question\n");
        for (ShowBeanBate.ContentBean.QuestionListBean questionListBean : questionList) {
            questionListBeanStringBuilder.append(questionListBean.getLanguage()+":"+questionListBean.getQuestion_name()+"\t:\t"+questionListBean.getIntention_answer()+"\n");
        }
        holder.question.setText(questionListBeanStringBuilder.toString());
    }

    @Override
    public int getItemCount() {
        if(contentBeanList == null){
            return 0;
        }
        return contentBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView intent;
        private TextView auxiliary;
        private TextView question;

        public ViewHolder(View itemView) {
            super(itemView);
            intent = itemView.findViewById(R.id.activity_auxiliary_info_show_adapter_intent);
            auxiliary = itemView.findViewById(R.id.activity_auxiliary_info_show_adapter_auxiliary);
            question = itemView.findViewById(R.id.activity_auxiliary_info_show_adapter_question);


        }
    }
}
