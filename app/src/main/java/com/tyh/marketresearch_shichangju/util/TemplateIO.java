package com.tyh.marketresearch_shichangju.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.independentsoft.office.word.WordDocument;
import com.independentsoft.xml.stream.XMLStreamException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;



public class TemplateIO {

    public static void saveAsTemplate(Context context, String templateName, Map dataMap, String targetUrl) throws IOException, XMLStreamException {

        AssetManager am = context.getResources().getAssets();
        //或者AssetManager am = this.getAssets();
        InputStream is = am.open(templateName);
        WordDocument doc = new WordDocument(is);

        Iterator<Map.Entry<String, String>> iterator = dataMap.entrySet().iterator();
        Map.Entry<String, String> entry = null;
        while(iterator.hasNext()){
            entry = iterator.next();
            doc.replace("$"+entry.getKey()+"$", entry.getValue());
        }
        doc.save(targetUrl, true);
    }

    public static String getTemplateTime(){
        String msg="";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd_HHmmss_SSS");
        msg+="["+sdf.format(date)+"]";

        return msg;
    }
}
