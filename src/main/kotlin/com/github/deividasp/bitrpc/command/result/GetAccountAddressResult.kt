package com.github.deividasp.bitrpc.command.result

import com.google.gson.annotations.SerializedName

class GetAccountAddressResult(@SerializedName("result") val address: String, val error: Error) : Result(error)