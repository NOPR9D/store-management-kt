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

fun linked(clientId: String, StoreId: String, response: HttpServletResponse): String {
    response.status = 200
    return "Link client under id: $clientId  with Store under id: $StoreId"
}

fun unlinked(clientId: String, StoreId: String, response: HttpServletResponse): String {
    response.status = 200
    return "Unlink client under id: $clientId  with Store under id: $StoreId"
}


fun deleted(id: String, response: HttpServletResponse): String {
    response.status = 200
    return "DELETED Item under id: $id"
}

fun badFormat(response: HttpServletResponse): String {
    response.status = 400
    return "Bad format"
}

fun alreadyExist(response: HttpServletResponse): String {
    response.status = 409
    return "Data Already exist !"
}