package cn.bugstack.chatbot.api.domain.ai;

import java.io.IOException;

/**
 *
 *
 * CHATGPT open ai 接口
 *
 *
*
*/
public interface IOpenAI {

    String doChatGPT(String openAiKey,String question) throws IOException;
}
