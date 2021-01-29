package com.boucham.storeManagement.services

import javax.servlet.http.HttpServletResponse

// Retours api (pour les cas prévu actuellement)

fun <T> notFound(response: HttpServletResponse): T? {
    response.status = 404
    return null
}

fun added(id: String, response: HttpServletResponse): String {
    response.status = 201
    return "ADDED Item under id: $id"
}

fun ok(response: HttpServletResponse): String {
    response.status = 200
    return "OK"
}

fun visited(clientId: String, StoreId: String, response: HttpServletResponse): String {
    response.status = 200
    return "Client under id: $clientId visited Store under id: $StoreId"
}


fun deleted(id: String, response: HttpServletResponse): String {
    response.status = 200
    return "DELETED Item under id: $id"
}

fun badFormat(response: HttpServletResponse): String {
    response.status = 400
    return "Bad format"
}