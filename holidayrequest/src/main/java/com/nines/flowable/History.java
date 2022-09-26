package com.nines.flowable;

import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.runtime.ProcessInstance;

import java.util.List;

/**
 * @author tanyujie
 * @classname HistoryService
 * @description 历史数据
 * @date 2022/7/18 17:34
 * @since 1.0
 */
public class History {

    public static void main(String[] args) {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/flowable?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai&useAffectedRows=true")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        // 从ProcessEngine获取HistoryService并创建历史活动查询: 仅针对一个特定流程实例的活动 只有已经完成的活动
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities  = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId("7504")
                .finished()
                .orderByHistoricActivityInstanceEndTime().asc()
                .list();
        activities.forEach(activity -> System.out.println(activity.getActivityId() + "使用" + activity.getDurationInMillis() + "毫秒"));
    }

}
