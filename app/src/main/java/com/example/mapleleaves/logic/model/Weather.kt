package com.example.mapleleaves.logic.model

import com.example.mapleleaves.logic.model.response.DailyResponse
import com.example.mapleleaves.logic.model.response.RealtimeResponse

data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
