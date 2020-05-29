package com.etoak.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	//收件人
	private String receiver;
	//发件人
	//private String sender;
	//邮件主题
	private String subject;
	//邮件内容
	private String content;
	//附件 抄送人
}
