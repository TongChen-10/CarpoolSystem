package com.tongchen.carpool.enums;

/**
 * Created by tongchen on 2016-10-16.
 */
public enum CarpoolStatEnum {
    SUCCESS(1,"Success"),
    END(0,"End");

    private int state;
    private  String stateInfo;

    CarpoolStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static CarpoolStatEnum stateOf(int index){
        for(CarpoolStatEnum state: values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
