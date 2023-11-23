package org.apache.commons.mail;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class MultiPartEmailTestNG {

    @Parameters({"path"})
    @Test
    public void testAttach(String path) throws Exception {
        MultiPartEmail multiPartEmail = new MultiPartEmail();
//        String path= "D:/work/vscode/Code/res/mypictures/Test1.svg";
        File file;
        multiPartEmail.setHostName("smtp.qq.com");
        multiPartEmail.setSmtpPort(465);
        multiPartEmail.setAuthenticator(new DefaultAuthenticator("2398491106@qq.com", "hfadqgkbsroqebih"));
        multiPartEmail.setSSLOnConnect(true);
        multiPartEmail.addTo("2398491106@qq.com", "Me");
        multiPartEmail.setFrom("2398491106@qq.com", "TestRobot");
        multiPartEmail.setSubject("Test");
        multiPartEmail.setMsg("Here is a test. :D");
        file=new File(path);
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(path);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of Test");
        attachment.setName("Test1.svg");
        try{
            multiPartEmail.attach(attachment);  //添加附件
        }catch (EmailException e){
            assertEquals(file.exists(),false);
        }
        assertEquals(file.exists(),true);
        multiPartEmail.send();  //发送邮件
    }

}