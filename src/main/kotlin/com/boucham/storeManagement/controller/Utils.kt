package com.boucham.storeManagement.controller

import javax.servlet.http.HttpServletResponse

fun <T> notFound(response: HttpServletResponse): T? {
    response.status = 404
    return null
}

fun added(response: HttpServletResponse): String {
    response.status = 201
    return "ADDED"
}

fun ok(response: HttpServletResponse): String {
    response.status = 200
    return "OK"
}


fun deleted(response: HttpServletResponse): String {
    response.status = 200
    return "DELETED"
}