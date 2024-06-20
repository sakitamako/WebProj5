package com.diworksdev.webproj5.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.webproj5.dao.HelloStrutsDAO;
import com.diworksdev.webproj5.dto.HelloStrutsDTO;
import com.opensymphony.xwork2.ActionSupport;

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
//実際の処理を持たない、ちょっと変わったクラス=implements
//Java7までは実装は持てず、メソッドのシグニチャのみの定義
//interfaceを使って型宣言を行うことができますが、メソッドの定義がないとプログラムは実行できないので、そこで使うのがimplements
public class HelloStrutsAction extends ActionSupport implements SessionAware {

	//インスタンス化=コピーしたものを変数に代入
	//カプセル化、private=自分のクラスのみ
	//ArrayList=public ArrayList() 初期容量10で空のリストを作成
	//java.util.List<LoginDTO>順序付けられたコレクション。シーケンスとも呼ばれる。
	//このインタフェースのユーザーは、リスト内のどこに各要素が挿入されるかを精密に制御できる。
	//ユーザーは整数値のインデックス(リスト内の位置)によって要素にアクセスしたり、リスト内の要素を検索したりできる
	private List<HelloStrutsDTO> helloStrutsDTOList = new ArrayList<HelloStrutsDTO>();

	//session=あるグループが共通の活動を行う〕集まり、集会
	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです。
	private Map<String, Object> session;

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//メソッドの戻り値「ret」 String ret = ERROR; を定義し、初期値としてERRORを代入
		String ret = ERROR;

		//②HelloStrutsDAOとHelloStrutsDTOのインスタンス化
		HelloStrutsDAO dao = new HelloStrutsDAO();

		//HelloStrutsDTOはHelloStrutsDAOクラスのselectメソッドを呼び出す
		helloStrutsDTOList = dao.select();

		//もし下記の場合、ret=SUCCESS
		//そうでない場合、ERROR
		if (helloStrutsDTOList.size() > 0) {

			session.put("helloStrutsDTOList", helloStrutsDTOList);

			ret = SUCCESS;

		} else {

			ret = ERROR;
		}

		//retに入った値を呼び出し元であるActionクラスに渡す
		return ret;

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
