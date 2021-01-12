package com.xiaoniu.fuse.test.service;

/**
 * Created by Chris on 2017/9/24.
 */
public interface HelloSpeakerService {

    public String speak(String abc) throws InterruptedException;

    public String talk() throws InterruptedException;
}
