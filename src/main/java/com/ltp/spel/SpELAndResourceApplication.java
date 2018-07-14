package com.ltp.spel;

import com.ltp.spel.entity.PropertyResourceBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.net.MalformedURLException;

public class SpELAndResourceApplication {

    /**
     * 获取资源文件方法2
     */
    @Test
    public void test_PropertyResource() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        PropertyResourceBean propertyResourceBean = context.getBean(PropertyResourceBean.class);
        System.out.println(propertyResourceBean);
    }

    @Test
    public void test_parseResource() {

        Resource cpResource = new ClassPathResource("classpath:\\db.properties");

        // 读取拷贝网络文件
        Resource urlResource = null;
        try {
            urlResource = new UrlResource("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531566924807&di=7b7b44f944d7429f193f0ef33d8aa44b&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2%2F5784bb78b7e78.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            copyFile(urlResource.getInputStream(), "D:\\testCopy\\test.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取拷贝磁盘文件
        Resource fsResource = new FileSystemResource("D:\\db.properties");
        try {
            copyFile(fsResource.getInputStream(), "D:\\testCopy\\db.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件拷贝
     * @param srcInputStream 输入流
     * @param destPath 输出地址,path/file.xxx
     */
    public void copyFile(InputStream srcInputStream, String destPath) {

        BufferedInputStream bufferedInputStream = new BufferedInputStream(srcInputStream);
        BufferedOutputStream bufferedOutputStream = null;

        try {
            File destFile = new File(destPath);

            if(!destFile.exists()) {
                try {
                    destFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[2048];

        int len = 0;
        try {
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(srcInputStream != null) {
                try {
                    srcInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
