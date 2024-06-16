package com.diworksdev.webproj5.action;

import com.opensymphony.xwork2.ActionSupport;


//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//WelcomeAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class WelcomeAction extends ActionSupport {

	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {
		return SUCCESS;

	}

}
