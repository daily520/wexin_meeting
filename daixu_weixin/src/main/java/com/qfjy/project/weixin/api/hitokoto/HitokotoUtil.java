package com.qfjy.project.weixin.api.hitokoto;

import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author daily
 * @version 1.0
 * @date 2019/11/21 19:47
 */
@Service
public class HitokotoUtil {
    private static final String HITOKOTO_URL="https://v1.hitokoto.cn/?c=a";
    public String sendRequest(){
        JSONObject jsonObject=WeixinUtil.httpRequest(HITOKOTO_URL,"POST",null);
        String result= (String) jsonObject.get("hitokoto");
        return result;
    }
}
