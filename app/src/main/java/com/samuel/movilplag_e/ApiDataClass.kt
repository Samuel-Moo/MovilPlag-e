package com.samuel.movilplag_e

data class UserRobotDataClass(
    val code: String,
    val name: String,
    val waste: Int,
    val locationX: Double,
    val locationY: Double
)


data class postRobot(
    val code: String,
    val userId: String
)