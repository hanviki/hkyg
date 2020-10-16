package cc.mrbird.febs.hkgf.utils;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

public class XmlConvertEntityUtils {
    public XmlConvertEntityUtils() {
    }

    public static String readStringXml(String xml) {
        xml = xml.replace("&nbsp;", " ");
        Document doc = null;
        HashMap result = new HashMap();

        try {
            doc = DocumentHelper.parseText(xml);
            Element rootElt = doc.getRootElement();
            System.out.println("根节点：" + rootElt.getName());
            Iterator iter = rootElt.elementIterator("Item");

            while(iter.hasNext()) {
                Element recordEle = (Element)iter.next();
                if (!StringUtils.isEmpty(recordEle.attributeValue("Value"))) {
                    result.put(recordEle.attributeValue("Key"), recordEle.attributeValue("Value"));
                }
            }
        } catch (DocumentException var6) {
            var6.printStackTrace();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return (new Gson()).toJson(result);
    }
}

