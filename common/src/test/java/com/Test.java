package com;

import com.alibaba.fastjson.JSON;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author LQW
 * @date 2021-04-25 22:22
 **/
public class Test {



    public static void main(String[] args) throws InterruptedException {

        List<Message> list=new ArrayList<>();

        list.add(new Message("open_mail_01","仅申购","本次开放接受申购。\n" +
                "\n" +
                "请于预约期末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_02","仅加仓","本次开放仅接受加仓。\n" +
                "\n" +
                "请于预约期末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_03","申购及赎回","本次开放接受申购，接受赎回。\n" +
                "\n" +
                "请于预约期末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_04","加仓及赎回","本次开放仅接受加仓，接受赎回。\n" +
                "\n" +
                "请于预约期末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_05","仅赎回","本次开放接受赎回。\n" +
                "\n" +
                "请于预约日末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_06","封闭期内盈利部分可赎回（线下）","本次开放接受申购，接受赎回。\n" +
                "\n" +
                "请于预约期末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。\n" +
                "\n" +
                "如需按比例赎回盈利部分，请提交纸质赎回申请表交由各网点OP办理，比例赎回暂不支持从系统提交，切勿通过系统操作！\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_07","特殊-歌斐三年期（带赎回）","本次开放接受申购，接受赎回。\n" +
                "\n" +
                "在每个自然年度，对于持有满1年（含）不满3年（不含）的份额，可赎回份额为不超过该笔份额初始份额的25%；持有满3年（含，每年以365天计算）的份额不受比例限制。\n" +
                "\n" +
                "请于预约申购日末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_08","特殊-歌斐传家（带赎回)","本次开放接受申购与赎回。\n" +
                "\n" +
                "对于持有满两年、不满3年的份额，本次可赎回份额为不超过此部分的15%；持有满3年的份额不受比例限制。\n" +
                "\n" +
                "请于预约日末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));

        list.add(new Message("open_mail_09","特殊-高毅邻山（仅赎回）","本次开发仅接受赎回，不接受新申购。\n" +
                "\n" +
                "单笔份额锁定1年，锁定期后每年可赎回1/3，但赎回后资产不得低于100万元，3年后可全部赎回。\n" +
                "\n" +
                "请于预约日末日15:00前提交材料，过期恕不接受。\n" +
                "\n" +
                "邮件通知内容仅供内部参考，具体开放日期、申赎费率信息请以合同约定为准。"));


        String s = JSON.toJSONString(list);

        System.out.println(s);
    }
}
class Message{
    private String code;
    private String title;

    private String message;

    public Message(String code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}