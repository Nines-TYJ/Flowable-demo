package com.nines.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author tanyujie
 * @classname SendRejectionMail
 * @description TODO
 * @date 2022/7/18 17:29
 * @since 1.0
 */
public class SendRejectionMail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("员工 " + execution.getVariable("employee") + " 的假期申请被拒绝");
    }
}
