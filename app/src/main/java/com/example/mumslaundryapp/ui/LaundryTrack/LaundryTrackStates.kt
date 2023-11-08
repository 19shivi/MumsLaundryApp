package com.example.mumslaundryapp.ui.LaundryTrack

import com.example.mumslaundryapp.data.TrackData

data class LaundryTrackStates(
    val isLoading:Boolean=false,
    val error:String="",
    val data:TrackData=TrackData(
        0,0,0,0,0,0,0,0,0
    )
)
