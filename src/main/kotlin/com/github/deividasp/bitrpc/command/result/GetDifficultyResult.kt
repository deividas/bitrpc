package com.github.deividasp.bitrpc.command.result

import com.google.gson.annotations.SerializedName

class GetDifficultyResult(@SerializedName("result") val difficulty: Double, val error: Error) : Result(error)