package com.example.lawyerly.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Lawyer(
    val firstName: String,
    val lastName: String,
    val speciality: String,
    val hourlyRate: Float,
    var rating: Float,
    val verified: Boolean,
    @DrawableRes val image: Int,
    val freeConsultationAvailable: Boolean,
    val memberSince: Date,
    val numberOfReviews: Int,
    val ranking: String,
    val avgResponseTimeDays: Int,
    val description: String,
    val otherInfo: String
): Parcelable