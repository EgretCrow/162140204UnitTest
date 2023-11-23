package org.apache.commons.mail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class MultiPartEmailTest {

    MultiPartEmail multiPartEmail = new MultiPartEmail();
    String path= "D:/work/vscode/Code/res/mypictures/Test1.svg";
    File file;
    @Before
    public void setUp() throws Exception {
        // 创建电子邮件
//        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName("smtp.qq.com");
        multiPartEmail.setSmtpPort(465);
        multiPartEmail.setAuthenticator(new DefaultAuthenticator("2398491106@qq.com", "hfadqgkbsroqebih"));
        multiPartEmail.setSSLOnConnect(true);
        multiPartEmail.addTo("2398491106@qq.com", "Me");
        multiPartEmail.setFrom("2398491106@qq.com", "TestRobot");
        multiPartEmail.setSubject("Test");
        multiPartEmail.setMsg("Here is a test. :D");
        file=new File(path);
        if (!file.exists()) {
            System.out.println("file\""+path+"\" is not exist");
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAttach0() throws Exception {
        // 创建并添加附件
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(path);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of Test");
        attachment.setName("Test1.svg");
        try{
            multiPartEmail.attach(attachment);  //添加附件
        }catch (EmailException e){
            assertEquals(false,file.exists());
        }
        assertEquals(true,file.exists());
        multiPartEmail.send();  //发送邮件
    }

    @Test
    public void testAttach1() throws Exception {
        try{
            multiPartEmail.attach(file);  //添加附件

        }catch (EmailException e){
            assertEquals(file.exists(),false);
        }
        assertEquals(file.exists(),true);
        multiPartEmail.send();  //发送邮件
    }

    @Test
    public void testAttach2() throws MalformedURLException,Exception {
        try {
            URL url=new URL("file:///"+path);
            multiPartEmail.attach(url,"Test1.svg","Picture of test",EmailAttachment.ATTACHMENT);
        }catch (EmailException e){
            assertEquals(file.exists(),false);
        }
        assertEquals(file.exists(),true);
        multiPartEmail.send();

    }
}