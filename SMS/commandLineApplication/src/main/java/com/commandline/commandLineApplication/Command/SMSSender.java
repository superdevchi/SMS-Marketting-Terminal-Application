package com.commandline.commandLineApplication.Command;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.*;
import java.util.*;

@ShellComponent
public class SMSSender {



    @Autowired
    ResourceLoader resourceLoader;



    ArrayList<String> Numbers = new ArrayList<String>();

    ArrayList<String> NumberList = new ArrayList<String>();

    ArrayList<String> MessageList = new ArrayList<String>();

    ArrayList<String> LinkList = new ArrayList<String>();



    //load up number list
    @ShellMethod(key = "Numbers", value = "STEP1: Confirm list of number in Numbers.txt in Resources folder")
    public ArrayList<String>list () throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/Numbers.txt");
        File resource_file = resource.getFile();

        Scanner scanresource = new Scanner(resource_file);

        while(scanresource.hasNextLine()){
            NumberList.add(scanresource.nextLine());
        }


        return NumberList;
    }


    // load up message list
    @ShellMethod(key = "Message", value = "STEP2: list Messages in Messages.txt in Resources folder")
    public ArrayList<String>MessageList () throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/Messages.txt");
        File resource_file = resource.getFile();

        Scanner scanresource = new Scanner(resource_file);

        while(scanresource.hasNextLine()){
            MessageList.add(scanresource.nextLine());
        }


        return MessageList;
    }


    //load up link list
    @ShellMethod(key = "Link", value = "STEP3: list Messages in Links.txt in Resources folder")
    public ArrayList<String>LinkList () throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/Links.txt");
        File resource_file = resource.getFile();

        Scanner scanresource = new Scanner(resource_file);

        while(scanresource.hasNextLine()){
            LinkList.add(scanresource.nextLine());
        }


        return LinkList;
    }



    // Send sms to canada numbers random sms and link rotation
    @ShellMethod(key = "CA", value = "SEND SMS CAMPAIGN TO CANADA: Confirm number list before broadcast to canada")
    public void sendCanada(){
         String ACCOUNT_SID = "AC3b928fd9fc48192bc43588c985fde852";
         String AUTH_TOKEN = "2aaf63ce63c564e3dd22daf55e84f9d8";
         String PHONE_NUMBER = "+16512178494";



        for (int index = 0; index<NumberList.size(); index++){

            Random rand = new Random();
            String messages = MessageList.get(rand.nextInt(MessageList.size()));
            String link = LinkList.get(rand.nextInt(LinkList.size()));

            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(NumberList.get(index)),
                    new PhoneNumber(PHONE_NUMBER),
                    messages+" "+ link
            ).create();

            System.out.println(message.getStatus() + "Message Response");
        }
    }

    @ShellMethod(key = "Uk", value = "SEND SMS CAMPAIGN TO UNITED KINGDOM: Confirm number before broadcasting, this broadcast to Uk")
    public void boradcast(){

         String ACCOUNT_SID = "ACf4a9a0ef5d7af8940c894123f6fdce4c";
         String AUTH_TOKEN = "d7953c7da074472dea22962ad3ccd43d";

          String PHONE_NUMBER = "+447723546461";



        for (int index = 0; index<NumberList.size(); index++){

            Random rand = new Random();
            String messages = MessageList.get(rand.nextInt(MessageList.size()));
            String link = LinkList.get(rand.nextInt(LinkList.size()));

            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(NumberList.get(index)),
                    new PhoneNumber(PHONE_NUMBER),
                    messages+" "+ link).create();

            System.out.println(message.getStatus() + "Message Response");
        }



    }


}
