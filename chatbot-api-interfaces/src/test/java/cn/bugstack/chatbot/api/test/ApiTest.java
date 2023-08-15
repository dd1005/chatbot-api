package cn.bugstack.chatbot.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ApiTest {

    /**
     * 通过代码拿到请求
     *
     */
    @Test
    public void query_unanswered_questions() throws IOException {
        //1.封装数据信息
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        ///2.封装请求
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51112481218284/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie","zsxq_access_token=67D05276-2AD0-70E9-4FED-E5C1FA813358_F8B4364C165C1BB7; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585122148181484%22%2C%22first_id%22%3A%22189b04bf37852d-041c83c1e9f26-26031a51-1327104-189b04bf3799e%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg5YjA0YmYzNzg1MmQtMDQxYzgzYzFlOWYyNi0yNjAzMWE1MS0xMzI3MTA0LTE4OWIwNGJmMzc5OWUiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1ODUxMjIxNDgxODE0ODQifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585122148181484%22%7D%2C%22%24device_id%22%3A%22189b04bf37852d-041c83c1e9f26-26031a51-1327104-189b04bf3799e%22%7D; abtest_env=product; zsxqsessionid=af5bf6c941ad565747a0d103fb084c13");
        get.addHeader("Content-Type","application/json;charset=utf-8");
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String s = EntityUtils.toString(response.getEntity());
            System.out.println(s);


        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }



    }

    @Test
    public void  answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //https://api.zsxq.com/v2/groups/51112481218284/topics?scope=unanswered_questions&cou
        //"topic_id":211825852548441
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/188542524288842/answer");
        post.addHeader("cookie","zsxq_access_token=67D05276-2AD0-70E9-4FED-E5C1FA813358_F8B4364C165C1BB7; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22585122148181484%22%2C%22first_id%22%3A%22189b04bf37852d-041c83c1e9f26-26031a51-1327104-189b04bf3799e%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg5YjA0YmYzNzg1MmQtMDQxYzgzYzFlOWYyNi0yNjAzMWE1MS0xMzI3MTA0LTE4OWIwNGJmMzc5OWUiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI1ODUxMjIxNDgxODE0ODQifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22585122148181484%22%7D%2C%22%24device_id%22%3A%22189b04bf37852d-041c83c1e9f26-26031a51-1327104-189b04bf3799e%22%7D; abtest_env=product; zsxqsessionid=af5bf6c941ad565747a0d103fb084c13");
        post.addHeader("Content-Type","application/json;charset=utf-8");
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"我也不会啊\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String s = EntityUtils.toString(response.getEntity());
            System.out.println(s);


        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
