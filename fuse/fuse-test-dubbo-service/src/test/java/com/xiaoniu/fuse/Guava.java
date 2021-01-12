package com.xiaoniu.fuse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/22 18:18
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class Guava {

    public static void main(String[] args) throws IOException {
        Pack p = new Pack();
        p.getList().add("1");
        p.getList().add("2");
        p.getList().add("3");
        for (Iterator<String> it = p.getList().iterator(); it.hasNext(); ) {
            String s = it.next();
            if (s.equals("2"))
                it.remove();
        }
        System.out.println(p.getList());

    }
}

class Pack {
    private List<String> list = Arrays.asList("1");

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}