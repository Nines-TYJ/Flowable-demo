package com.nines.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author tanyujie
 * @classname CallExternalSystemDelegate
 * @description 通过回调
 * @date 2022/7/18 17:21
 * @since 1.0
 */
public class CallExternalSystemDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("通知员工 " + execution.getVariable("employee") + " 假期批准");
    }
}
