package com.qfjy.project.weixin.api.tuling;

import com.qfjy.project.weixin.api.tuling.bean.InputText;
import com.qfjy.project.weixin.api.tuling.bean.Perception;
import com.qfjy.project.weixin.api.tuling.bean.TulingBean;
import com.qfjy.project.weixin.api.tuling.bean.UserInfo;
import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/21 11:20
 */
@Service
public class TulingUtil {
    private static final String TULING_API_URL="http://openapi.tuling123.com/openapi/api/v2";

    public String sendMessage(String msg,String apiKey){
        //System.out.println(apiKey);
        JSONObject jsonObject=sendJSONObject(msg,apiKey);
        JSONObject object= WeixinUtil.httpRequest(TULING_API_URL,"POST",jsonObject.toString());
        //System.out.println("响应消息"+object.toString());
        JSONArray array= (JSONArray) object.get("results");
        JSONObject object1=(JSONObject) array.get(0);
        JSONObject object2=(JSONObject)object1.get("values");
        String result=(String) object2.get("text");
        return result;
    }
    public JSONObject sendJSONObject(String msg,String apiKey){
        InputText inputText=new InputText();
        inputText.setText(msg);
        Perception perception=new Perception();
        perception.setInputText(inputText);
        UserInfo userInfo=new UserInfo();
        userInfo.setApiKey(apiKey);
        userInfo.setUserId("java1903");
        TulingBean tulingBean=new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);

        JSONObject jsonObject=JSONObject.fromObject(tulingBean);

        return jsonObject;
    }

    public static List<String> getKeys(){
        List<String> list =new ArrayList<>();
        list.add("57fa800fae0d44c3985a7671ee4172a1");
        list.add("4356fe89d4e44a47a224f1f5063b6170");
        list.add("47dbce3e636d44169aade8cf23b01dbb");
        list.add("1418b4ac64b14d7e96b22c4b0586c9f5");
        list.add("920bb0265a164e568e5f812932666176");
        list.add("6e7a7a95d11f46f8a018bb25da5ac16d");
        return list;
    }

}
