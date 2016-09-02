package com.csf.common;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description:
 * <p>
 * Author: johnny01.yang
 * Date  : 2016-08-29 16:57
 */
public class TikaTest {

    @Test
    public void testMimeType() {
        FileInputStream fis = null;
        try {
            File file = new File("D:\\accountFreezeAudit.js");
            fis = new FileInputStream(file);

            ContentHandler contentHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
            Parser parser = new AutoDetectParser();
            parser.parse(fis, contentHandler, metadata, new ParseContext());

            System.out.println("Mime:" + metadata.get(Metadata.CONTENT_TYPE));

        } catch (FileNotFoundException e) {

        } catch (Exception e) {

        }
    }

    @Test
    public void test11() throws IOException {
        String name = "aaa.txt";
        System.out.println("SeparatorChar :" + File.separatorChar);
        System.out.println("SeparatorChar :" + File.pathSeparatorChar);
        System.out.println("SeparatorChar :" + File.separator);
        System.out.println("SeparatorChar :" + File.pathSeparator);
        System.out.println(name.indexOf("p"));
        System.out.println(name.substring(name.indexOf("p") + 1));
    }


}
