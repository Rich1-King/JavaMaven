package com.systemDemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

/**
 * Created by rich1 on 12/24/16.
 */
public class SystemProcessDemo{
    public static void main(String[] args){
        System.out.println("====================");
        testProcess();
        System.out.println("====================");

        getIpConfig();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        getConfig();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Config();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        all();
    }

    public static List<String> testProcess(){
        Process p = null;
        List<String> address = new ArrayList<String>();
        try{
            //方法一
            //p = new ProcessBuilder("ifconfig").start();
            //方法二
            p = Runtime.getRuntime().exec("ifconfig");
        }catch (Exception e){
            return null;
        }

        StringBuffer sb = new StringBuffer();
        InputStream inputStream = p.getInputStream();
        System.out.println("process:" + p.toString());
        BufferedReader br = new BufferedReader(new InputStreamReader
                (inputStream));
        String s = "";
        try{
            while ((s = br.readLine()) != null){
                sb.append(s + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(sb);
        return null;
    }

    public static void getIpConfig(){
        Map<String, String> map = System.getenv();
        System.out.println("计算机用户名"+map.get("USERNAME"));//获取用户名
        System.out.println("计算机名"+map.get("COMPUTERNAME"));//获取计算机名
        System.out.println("计算机域名"+map.get("USERDOMAIN"));//获取计算机域名
    }

    //获取ip地址和mac地址
    public static void getConfig(){
        try{
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            String sMAC = "";
            Formatter formatter = new Formatter();
            for(int i= 0; i<mac.length; i++){
                sMAC = formatter.format(Locale.getDefault(), "%02X%s",
                        mac[i], (i < mac.length -1) ? "-":"").toString();
            }
            System.out.println("IP:"+sIP);
            System.out.println("MAC:"+sMAC);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //得到计算机的ip,名称, 操作系统名称, 操作系统版本
    public static void Config(){
        try{
            InetAddress addr = InetAddress.getLocalHost();
            String ip=addr.getHostAddress().toString(); //获取本机ip
            String hostName=addr.getHostName().toString(); //获取本机计算机名称
            System.out.println("本机IP："+ip+"\n本机名称:"+hostName);
            Properties props=System.getProperties();
            System.out.println("操作系统的名称："+props.getProperty("os.name"));
            System.out.println("操作系统的版本："+props.getProperty("os.version"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //java环境
    public static void all(){
        Properties props=System.getProperties();
        System.out.println("Java的运行环境版本："+props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商："+props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL："+props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径："+props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本："+props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商："+props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称："+props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本："+props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商："+props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称："+props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本："+props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商："+props.getProperty("java.specification.vender"));
        System.out.println("Java运行时环境规范名称："+props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号："+props.getProperty("java.class.version"));
        System.out.println("Java的类路径："+props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表："+props.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径："+props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径："+props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称："+props.getProperty("os.name"));
        System.out.println("操作系统的构架："+props.getProperty("os.arch"));
        System.out.println("操作系统的版本："+props.getProperty("os.version"));
        System.out.println("文件分隔符："+props.getProperty("file.separator"));
        System.out.println("行分割符:"+props.getProperty("line.separator"));
        System.out.println("路径分割符:"+props.getProperty("path.separator"));
        System.out.println("用户的主目录："+props.getProperty("user.home"));
        System.out.println("用户的当前工作目录："+props.getProperty("user.dir"));
        System.out.println("用户账户名称:"+props.getProperty("user.name"));
        System.out.println("JIT编译器名称:"+props.getProperty("java.compiler"));
    }



}