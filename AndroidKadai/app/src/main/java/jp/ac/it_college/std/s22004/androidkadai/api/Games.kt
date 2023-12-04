package jp.ac.it_college.std.s22004.androidkadai.api

import io.ktor.client.call.body
import jp.ac.it_college.std.s22004.androidkadai.BuildConfig
import jp.ac.it_college.std.s22004.androidkadai.BuildConfig.APP_ID
import jp.ac.it_college.std.s22004.androidkadai.model.Weather

object Games {
    /**
     * /generation エンドポイントへパラメータなしだと
     * [NamedApiResourceList] 型で取得できる。
     */
    public suspend fun getGenerations(q: String): Weather {
        return ApiClient.get("/forecast?lang=ja&units=metric&q=$q&appid=$APP_ID").body()   //"/forecast?id=$q&appid=$APP_ID" Tokyoで取るとき
    }
}