package com.diworksdev.webproj5.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.webproj5.dao.LoginDAO;
import com.diworksdev.webproj5.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
public class LoginAction extends ActionSupport implements SessionAware {

	//フィールド変数
	//JSPから受け取る値、ここではnameとpassword を定義
	//※必ずJSPでの定義と同じ名前にする
	private String username;
	private String password;

	//インスタンス化
	private List<LoginDTO> loginDTOList = new ArrayList<LoginDTO>();

	private Map<String, Object> session;

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//メソッドの戻り値「ret」 String ret = ERROR; を定義し、初期値としてERRORを代入
		String ret = ERROR;

		System.out.println(username);
		System.out.println(password);

		//②LoginDAOとLoginDTOのインスタンス化
		LoginDAO dao = new LoginDAO();

		//JSPから送られてきたnameとpasswordを引数として、
		//LoginDAOクラスのselectメソッドを呼び出す
		//その後、DAOで取得した結果をLoginDTOに代入する
		loginDTOList = dao.select(username, password);

		//aとbが共にtrueの時に処理を実行するそうでない場合はエラー
		if (this.username.equals(loginDTOList.get(0).getUsername()) && this.password.equals(loginDTOList.get(0).getPassword())) {

			session.put("loginDTOList", loginDTOList);

			ret = SUCCESS;

		} else {

			session.put("loginDTOList", loginDTOList);

			ret = ERROR;
		}

		//戻り値
		//retに入った値を呼び出し元であるActionクラスに渡す
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
	//Actionクラスから呼び出され、sessionフィールドの値をsessionに渡す
	public Map<String, Object> getSession() {
		return session;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のusernameフィールドに格納
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
