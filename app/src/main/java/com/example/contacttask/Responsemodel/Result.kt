package com.example.contacttask.Responsemodel

data class Result(
    val cell: String,
    val email: String,
    val gender: String,
    val id: Id,
    val name: Name,
    val phone: String,
    val picture: Picture
)