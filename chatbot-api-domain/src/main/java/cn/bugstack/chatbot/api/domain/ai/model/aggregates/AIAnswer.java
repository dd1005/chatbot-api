package cn.bugstack.chatbot.api.domain.ai.model.aggregates;

import cn.bugstack.chatbot.api.domain.ai.model.vo.Choices;
import cn.bugstack.chatbot.api.domain.ai.model.vo.Usage;

import java.awt.*;
import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description AI Answer
 * @github https://github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class AIAnswer {

    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choices> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
}
