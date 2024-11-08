package com.example.myapplication

import androidx.annotation.StringRes

data class Person(
    @StringRes val nameId: Int,
    @StringRes val addressId: Int
)
