package com.leomurca.boat.data.mapper

interface Mapper<F, T> {
    fun map(from: F): T
}