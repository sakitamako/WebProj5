package com.diworksdev.webproj5.action;

imp ort java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.webproj5.dao.TestDAO;
import com.diworksdev.webproj5.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class TestAction extends ActionSupport implements SessionAware {

	private String username;
	private String password;

	//インスタンス化
	private List<LoginDTO> loginDTOList = new ArrayList<LoginDTO>();

	private Map<String, Object> session;

	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		String ret = ERROR;

		//②インスタンス化
		//DAOと会話するためのコード
		TestDAO dao = new TestDAO();

		//①クラス、メソッドの定義
		int count = dao.insert(username, password);


		//もしcountが0より大きい場合ret=SUCCESS
		if ( count > 0 ) {
			ret = SUCCESS;

		//そうでない場合ERROR
		} else {
			ret = ERROR;

		}

		loginDTOList = dao.select(username, password);

		session.put("loginDTOList", loginDTOList);

		//戻り値
		//処理結果を返す
		return ret;
	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、usernameフィールドの値をActionに渡す
	public String getUsername() {
		return username;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のusernameフィールドに格納
	public void setUsername(String username) {
		this.username = username;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、passwordフィールドの値をActionに渡す
	public String getPassword() {
		return password;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のpasswordフィールドに格納
	public void setPassword(String password) {
		this.password = password;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、sessionフィールドの値をActionに渡す
	public Map<String, Object> getSession() {
		return session;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のsessionフィールドに格納
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
