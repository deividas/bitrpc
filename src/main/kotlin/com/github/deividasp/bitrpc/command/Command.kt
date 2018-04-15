package com.github.deividasp.bitrpc.command

enum class Command {

    GET_DIFFICULTY,
    GET_BLOCK_COUNT,
    GET_ACCOUNT_ADDRESS;

    fun getName() = name.toLowerCase().replace("_", "")

}