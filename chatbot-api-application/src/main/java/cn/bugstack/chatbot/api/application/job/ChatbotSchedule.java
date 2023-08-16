package cn.bugstack.chatbot.api.application.job;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.zsxq.IZsxqApi;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


/**
*
 * 问题任务
 *
 *
* */
@EnableScheduling
@Configuration
public class ChatbotSchedule {
    /*
    * 这行代码创建了一个名为 "logger" 的日志记录器（Logger）实例，用于在 Java 类中记录日志。LoggerFactory 是用于创建日志记录器的工厂类，而 getLogger() 方法则用于获取指定名称的日志记录器。在这里，你指定了一个名为 "ChatbotSchedule" 的类作为日志记录器的名称。

通常情况下，日志记录器用于在应用程序中输出各种级别的日志信息，以便在不同情况下进行调试、监控和错误追踪。你可以使用日志记录器的不同方法，如 info()、debug()、error() 等，根据需要输出不同级别的日志信息。
    * */

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    //定时任务，多长时间去轮询操作一下
    //方法将每隔 1 分钟执行一次，并在每次执行时输出一条日志信息。
    //cron.qqe2.com
    @Scheduled(cron = "0/5 * * * * ?")
    public void run(){
        try{
            //1分钟一次回答问题太规律，容易被检测到封控
            if (new Random().nextBoolean()){
                logger.info("{} 随机打烊中...");
                // 条件满足时，直接返回，不执行后续代码
                return;
            }

            //模拟人回答问题，半夜不回答问题，防止被封控（谁大半夜回答啊）
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("{} 打烊时间不工作，AI 下班了！");

                return;
            }

            //1.检索问题，查询提出的问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnswerQuestionsTopicId(groupId,cookie);
            logger.info("检索结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();

            //2.做个异常判断
            if (topics == null || topics.isEmpty()){
                logger.info("本次检索未查询到待回答问题");
            }

            //3.一次只回答一个问题，防止被封控起来
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(openAiKey,topic.getQuestion().getText());

            //4.问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("{} 编号：{} 问题：{} 回答：{} 状态：{}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);


        }catch(Exception e){

            logger.error("{} 自动回答问题异常",  e);


        }

    }







}
