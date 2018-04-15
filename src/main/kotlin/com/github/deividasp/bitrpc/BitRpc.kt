package com.github.deividasp.bitrpc

import com.github.deividasp.bitrpc.command.Command
import com.github.deividasp.bitrpc.command.Parameter
import com.github.deividasp.bitrpc.command.result.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.asynchttpclient.AsyncHttpClient
import org.asynchttpclient.Dsl.*
import java.util.concurrent.CompletableFuture

class BitRpc(username: String, password: String, private val host: String, private val port: Int) {

    private val gson: Gson = Gson()
    private val httpClient: AsyncHttpClient = asyncHttpClient(config()
        .setRealm(basicAuthRealm(username, password))
        .build())

    private fun <T> execute(command: Command, resultType: Class<T>, vararg parameters: Parameter): CompletableFuture<T> {
        val json = JsonObject()
        json.addProperty("method", command.getName())

        parameters.forEach { json.addProperty(it.key, it.value) }

        return httpClient
            .preparePost("http://$host:$port")
            .setBody(json.toString())
            .execute()
            .toCompletableFuture()
            .thenApply { r -> gson.fromJson(r.responseBody, resultType) }
    }

    fun getDifficulty() =
        execute(Command.GET_DIFFICULTY, GetDifficultyResult::class.java)

    fun getBlockCount() =
        execute(Command.GET_BLOCK_COUNT, GetBlockCountResult::class.java)

    fun getAccountAddress(accountName: String) =
        execute(Command.GET_ACCOUNT_ADDRESS, GetAccountAddressResult::class.java, Parameter("account", accountName))

}