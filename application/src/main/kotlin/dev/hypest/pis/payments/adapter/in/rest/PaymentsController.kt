@file:Suppress("InvalidPackageDeclaration")
package dev.hypest.pis.payments.adapter.`in`.rest

import io.micronaut.http.annotation.Controller
import dev.hypest.pis.payments.PaymentsApi

@Controller("/payments")
class PaymentsController : PaymentsApi
