package com.sun.kmpstartertemplaterefined.starter_resources

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

object StarterStringRes {
    val empty = Res.string.empty_string
}

fun StringResource.isEmpty() = this == Res.string.empty_string

@Composable
fun StringResource.toActualString() = stringResource(resource = this)
// @Composable
// fun StringResource.toActualString(): String {
//     val locale = LocalAppLocale.current
//     return key(locale) {
//         stringResource(resource = this)
//     }
// }

@Composable
fun StringResource.toActualString(vararg formatArgs: Any) =
    stringResource(resource = this, formatArgs = formatArgs)

@Composable
fun StringResource.ifThen(condition: Boolean, block: () -> StringResource) =
    stringResource(resource = if (condition) block() else this)