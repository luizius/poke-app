package com.lgvh.gf.pokeapp.home.model


class Pokemon(
    var id: Long,
    var name: String,
    var originaName: String,
    var url: String,
    val status: Boolean,
    var index: Int
)