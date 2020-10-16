package cc.mrbird.febs.hkgf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {
    public DateUtils() {
    }

    public String createSJDH() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    public String createSJDH(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    public String createSJDH(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    public String createFPHM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    public Date parseSJDH2Date(String sjdh) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(sjdh);
        return date;
    }

    public Date parseString2Datetime(String sjdh) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Date date = format.parse(sjdh);
        return date;
    }

    public Date convertInfoDate(String sjdh) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = format.parse(sjdh);
        return date;
    }

    public Date parseSJDH2Datetime(String sjdh) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(sjdh);
        return date;
    }

    public String parseDate2Sjdh(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return format.format(new Date());
    }

    public static String getSubDate(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(6, i);
        Date dt1 = rightNow.getTime();
        return sdf.format(dt1);
    }
}

