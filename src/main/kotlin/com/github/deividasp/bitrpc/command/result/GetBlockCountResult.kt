package com.github.deividasp.bitrpc.command.result

import com.google.gson.annotations.SerializedName

class GetBlockCountResult(@SerializedName("result") val count: Int, val error: Error) : Result(error)