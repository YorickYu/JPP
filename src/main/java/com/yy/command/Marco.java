package com.yy.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: Marco
 * @description: TODO 类描述
 * @author: yloopdaed
 * @date: 2021/11/23
 **/
public class Marco {
    private final List<Action> actions;

    public Marco() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void go() {
        actions.forEach(Action::perform);
    }
}
