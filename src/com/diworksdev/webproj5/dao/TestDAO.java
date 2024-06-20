package com.diworksdev.webproj5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diworksdev.webproj5 .dto.LoginDTO;
import com.diworksdev.webproj5.util.DBConnector;

//DAOクラスでは、Actionから送られてきた情報を使ってDBへ問い合わせを行うファイル
//問い合わせて取得した値をDTOクラスに格納するファイル
public class TestDAO {

	//インスタンス化
	//ArrayList=public ArrayList() 初期容量10で空のリストを作成
	//java.util.List<LoginDTO>順序付けられたコレクション。シーケンスとも呼ばれる。
	//このインタフェースのユーザーは、リスト内のどこに各要素が挿入されるかを精密に制御できる。
	//ユーザーは整数値のインデックス(リスト内の位置)によって要素にアクセスしたり、リスト内の要素を検索したりできる
	public List<LoginDTO> loginDTOList = new ArrayList<LoginDTO>();

	//①クラス、メソッドの定義
	//LoginDTO型を最後に呼び出し元に渡すので、LoginDTO型を戻り値にしたメソッドを作る
	//Actionクラスの値を引数として受け取る
	public int insert(String username, String password) {

		int ret = 0;

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		//④sql文を書く：値は ? を入れておく（どんな値でも使いまわしできるようにするため）
		//usersに入っているデータuser_nameとpasswordに入る条件を満たしたデータがsqlに代入される
		String sql = "insert into users(user_name, password) values(?,?)";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			PreparedStatement ps = con.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			ps.setString(1, username);
			ps.setString(2, password);

			//⑦executeQuery()/executeUpdate()で実行
			//（select文の場合はexectuteQuery()を使う）
			// select文のSQL文を実行するメソッドで戻り値はResultSet
			int i = ps.executeUpdate();

			//もしiが0より大きい場合i件登録されました
			if (i > 0) {

				System.out.println(i + "件登録されました");

				ret = i;

			}

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (SQLException e) {
			e.printStackTrace();

		}

		//try.catchはjavaの例外処理のための構文
		try {

			//⑨con.close()で接続を切る
			//データベースとの接続をクローズ
			//これをしないとデータベースを接続したまま作業が実行されてしまってメモリに負荷がかかる
			con.close();

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return ret;
	}
	public List<LoginDTO> select(String username,String password) {

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		//sql文を書く
		//SELECT データを抽出する
		//＊ テーブルに含まれる項目全て
		//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
		//④sql文を書く：値は ? を入れておく（どんな値でも使いまわしできるようにするため
		//?に入る条件を満たしたデータがsqlに代入される
		String sql = "select * from users where user_name=? and password=?";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			PreparedStatement ps = con.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();


			//ここでは2つのことをしている
			//下に1行ずらすこと
			//データが存在する限り表示する
			while (rs.next()) {

				//インスタンス化
				LoginDTO dto = new LoginDTO();

				//⑥sql文の?に入れる値をsetする
				dto.setUsername(rs.getString("user_name"));
				dto.setPassword(rs.getString("password"));

				loginDTOList.add(dto);

			}

			//もしloginDTOList.size()が0以下の場合、インスタンス化して該当なしと表示する
			if (loginDTOList.size() <= 0) {

				//インスタンス化
				LoginDTO dto = new LoginDTO();

				dto.setUsername("該当なし");
				dto.setPassword("該当なし");

				loginDTOList.add(dto);

			}

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (SQLException e) {
			e.printStackTrace();

		}

		//try.catchはjavaの例外処理のための構文
		try {

			//⑨con.close()で接続を切る
			//データベースとの接続をクローズ
			//これをしないとデータベースを接続したまま作業が実行されてしまってメモリに負荷がかかる
			con.close();

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch (SQLException e) {
			e.printStackTrace();

		}

		//dtoに入った値を呼び出し元であるActionクラスに渡す
		return loginDTOList;
	}

}
