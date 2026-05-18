/*
 *
 *  *
 *  *  * Copyright (c) 2026
 *  *  *
 *  *  * Author: Athar Gul
 *  *  * GitHub: https://github.com/DevAtrii/Kmp-Starter-Template
 *  *  * YouTube: https://www.youtube.com/@devatrii/videos
 *  *  *
 *  *  * All rights reserved.
 *  *
 *  *
 *
 */

package com.sun.kmpstartertemplaterefined.feature_purchases_presentation

import com.sun.kmpstartertemplaterefined.feature_purchases_domain.PurchaseException
import com.sun.kmpstartertemplaterefined.feature_purchases_domain.PurchaseExceptionReason
import com.sun.kmpstartertemplaterefined.starter_resources.Res
import com.sun.kmpstartertemplaterefined.starter_resources.error_generic
import com.sun.kmpstartertemplaterefined.starter_resources.starter_purchase_error_product_not_found
import com.sun.kmpstartertemplaterefined.starter_resources.starter_purchase_error_purchase_failed
import com.sun.kmpstartertemplaterefined.starter_resources.starter_purchase_error_restore_failed
import com.sun.kmpstartertemplaterefined.starter_resources.starter_purchase_error_user_not_signed_in
import org.jetbrains.compose.resources.StringResource

internal suspend fun Throwable.getPurchaseExceptionMessage(): StringResource {
    if (this !is PurchaseException)
        return Res.string.error_generic

    return when (this.reason) {
        PurchaseExceptionReason.ProductNotFound -> Res.string.starter_purchase_error_product_not_found
        is PurchaseExceptionReason.PurchaseFailed -> Res.string.starter_purchase_error_purchase_failed
        PurchaseExceptionReason.UserNotSignedIn -> Res.string.starter_purchase_error_user_not_signed_in
        is PurchaseExceptionReason.RestoreFailed -> Res.string.starter_purchase_error_restore_failed
    }
}