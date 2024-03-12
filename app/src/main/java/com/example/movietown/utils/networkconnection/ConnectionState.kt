package com.example.movietown.utils.networkconnection

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}