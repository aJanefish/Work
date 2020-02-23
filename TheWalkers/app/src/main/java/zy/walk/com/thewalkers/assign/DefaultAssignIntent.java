package zy.walk.com.thewalkers.assign;

import android.content.Intent;

import java.util.HashMap;

import zy.walk.com.thewalkers.imagesandanimations.event.MainEvent;

public class DefaultAssignIntent implements IAssignIntent {

    private Class mTarget;
    private int mFlag;
    private HashMap<String, Object> mExtra;


    public DefaultAssignIntent(Builder builder) {
        this.mFlag = builder.mFlag;
        this.mTarget = builder.mTarget;
        this.mExtra = builder.mExtra;

    }

    @Override
    public LaunchIntentPraseData getLaunchIntentParseData() {
        return null;
    }

    @Override
    public Class target() {
        return null;
    }

    @Override
    public void start() {
        Intent intent = new Intent();
        intent.putExtra("sfa", 1);


    }


    public static class Builder {

        private Class mTarget;
        private int mFlag;
        private HashMap<String, Object> mExtra = new HashMap<>();

        public Builder() {
        }

        public Builder target(Class target) {
            this.mTarget = target;
            return this;
        }

        public Builder falg(int flag) {
            this.mFlag = flag;
            return this;
        }

        public Builder extra(String key, Object object) {
            this.mExtra.put(key, object);
            return this;
        }


        public DefaultAssignIntent build() {
            return new DefaultAssignIntent(this);
        }

    }
}
