package org.apache.commons.mail;

import org.apache.commons.mail.*;

import java.io.File;

public class TestMultiPartEmail {
    public static void main(String[] args) throws EmailException {
        // 创建电子邮件
        MultiPartEmail multiPartEmail = new MultiPartEmail();
        multiPartEmail.setHostName("smtp.qq.com");
        multiPartEmail.setSmtpPort(465);
        multiPartEmail.setAuthenticator(new DefaultAuthenticator("2398491106@qq.com", "hfadqgkbsroqebih"));
        multiPartEmail.setSSLOnConnect(true);
        multiPartEmail.addTo("2398491106@qq.com", "Me");
        multiPartEmail.setFrom("2398491106@qq.com", "TestRobot");
        multiPartEmail.setSubject("Test");
        multiPartEmail.setMsg("Here is a test. :D");
        // 创建并添加附件
        EmailAttachment attachment = new EmailAttachment();
        String path="D:/work/vscode/Code/res/mypictures/Test1.svg";
        File file=new File(path);
        if (!file.exists()) {
            System.out.println("file\""+path+"\" is not exist");
            return ;
        }
        attachment.setPath(path);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of Test");
        attachment.setName("Test1.svg");
        try{
            multiPartEmail.attach(attachment);
            // 发送邮件
            multiPartEmail.send();
        }catch (EmailException e){
            System.err.println("Error:"+e);
        }


    }
}
