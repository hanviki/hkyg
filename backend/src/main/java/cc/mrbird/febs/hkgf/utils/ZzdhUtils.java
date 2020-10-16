package cc.mrbird.febs.hkgf.utils;

import java.util.Calendar;
import org.springframework.stereotype.Component;

@Component
public class ZzdhUtils {
    public ZzdhUtils() {
    }

    public String createZzdh() {
        Calendar calendar = Calendar.getInstance();
        String zzdNo = "hk" + calendar.get(1) + (calendar.get(2) + 1) + calendar.get(5) + calendar.get(10) + calendar.get(12) + calendar.get(13) + "xh" + this.random();
        return zzdNo;
    }

    public String random() {
        String num = "";

        for(int i = 0; i < 4; ++i) {
            num = num + (int)Math.floor(Math.random() * 10.0D);
        }

        return num;
    }

    public static void main(String[] args) {
        ZzdhUtils zzdhUtils = new ZzdhUtils();
        System.out.println(zzdhUtils.createZzdh());
    }
}

