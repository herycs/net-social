import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @ClassName Main
 * @Description []
 * @Author ANGLE0
 * @Date 2020/9/16 16:27
 * @Version V1.0
 **/
public class Main {
    public static void main(String[] args) throws ClientException {
        SmsUtil1 ssmsUtil = new SmsUtil1();
        String phone = "13619224369";
        ssmsUtil.sendSms(phone,"SMS_191801331","IS商城","{\"code\":\""+123445+"\"}");
    }
}
// 短信发送验证码
class SmsUtil1 {

    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * 发送短信
     *
     * @param mobile        手机号
     * @param template_code 模板号
     * @param sign_name     签名
     * @param param         参数
     * @return
     * @throws ClientException
     */
    public SendSmsResponse sendSms(String mobile, String template_code, String sign_name, String param) throws ClientException {
        String accessKeyId = "LTAI4GEfj8QsJ69EY4eWHhgU";
        String accessKeySecret = "7IgCNEvw1G1khyKvaRnGmFK0qvUxvA";
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(sign_name);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(template_code);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(param);
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
}