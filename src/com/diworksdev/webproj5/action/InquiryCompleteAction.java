package com.diworksdev.webproj5.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.webproj5.dao.InquiryCompleteDAO;
import com.diworksdev.webproj5.dto.InquiryDTO;
import com.opensymphony.xwork2.ActionSupport;

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class InquiryCompleteAction extends ActionSupport implements SessionAware {

	private String name;
	private String qtype;
	private String body;

	//インスタンス化
	List<InquiryDTO> inquiryDTOList = new ArrayList<InquiryDTO>();

	private Map<String, Object> session;

	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		String ret = ERROR;

		//②インスタンス化
		//DAOと会話するためのコード
		InquiryCompleteDAO dao = new InquiryCompleteDAO();

		//①クラス、メソッドの定義
		int count = dao.insert(name, qtype, body);

		//もしcountが0より大きい場合ret=SUCCESS
		if (count > 0) {

			inquiryDTOList = dao.select();

			session.put("inquiryDTOList", inquiryDTOList);

			ret = SUCCESS;
	}

		//戻り値
		//処理結果を返す
		return ret;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、nameフィールドの値をActionに渡す
	public String getName() {
		return name;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のnameフィールドに格納
	public void setName(String name) {
		this.name = name;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、qtypeフィールドの値をActionに渡す
	public String getQtype() {
		return qtype;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のqtypeフィールドに格納
	public void setQtype(String qtype) {
		this.qtype = qtype;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、bodyフィールドの値をActionに渡す
	public String getBody() {
		return body;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のbodyフィールドに格納
	public void setBody(String body) {
		this.body = body;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、sessionフィールドの値をActionに渡す
	public Map<String, Object> getSession() {
		return session;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のsessionフィールドに格納
	public void setSession(Map<String, Object> session) {
		this.session = session ;

	}

}
