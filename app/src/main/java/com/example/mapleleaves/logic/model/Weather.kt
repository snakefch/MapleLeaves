package com.example.mapleleaves.logic.model

import com.example.mapleleaves.logic.model.DailyResponse
import com.example.mapleleaves.logic.model.RealtimeResponse

data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
