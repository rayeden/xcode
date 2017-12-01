package xml;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
import org.json.XML;

public class XMLUtil {

    public static void XMLTest4() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<data>\n" +
                "\t<request_type>native_order_notice</request_type>\n" +
                "\t<req_seq>17110317260361017259</req_seq>\n" +
                "\t<sign>7E4994DF2D11E29F5267171E8BEB51CE</sign>\n" +
                "\t<trans_seq>11711031726030167905</trans_seq>\n" +
                "\t<chan_trans_id>4200000022201711032200187735</chan_trans_id>\n" +
                "\t<total_fee>1</total_fee><status>S</status>\n" +
                "\t<channel>W</channel>\n" +
                "\t<pay_time>2017-11-03 17:26:10</pay_time>\n" +
                "</data>";
        JSONObject xmlJsonObjext = XML.toJSONObject(xml);
        String s = xmlJsonObjext.toString();
        System.out.println(s);
        Object so = JSON.parse(s);
        System.out.println(((YstSO)so).getData());
//        System.out.println(so.get("data"));
//        System.out.println(so.getData().getChannel());
    }

    public static void main(String[] args) {
        XMLTest4();
    }
}
