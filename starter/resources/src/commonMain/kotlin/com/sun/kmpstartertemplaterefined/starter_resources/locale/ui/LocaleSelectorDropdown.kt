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

package com.sun.kmpstartertemplaterefined.starter_resources.locale.ui

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sun.kmpstartertemplaterefined.starter_resources.Res
import com.sun.kmpstartertemplaterefined.starter_resources.locale.StarterLocales
import com.sun.kmpstartertemplaterefined.starter_resources.locale_selector_dd_label
import com.sun.kmpstartertemplaterefined.starter_resources.toActualString
import com.sun.kmpstartertemplaterefined.ui_components.material_cupertino.dropdown.CupertinoDropdownItem
import com.sun.kmpstartertemplaterefined.ui_components.material_cupertino.sections.CupertinoSectionRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaleSelectorDropdown(
    modifier: Modifier = Modifier,
    isLast: Boolean = false,
) {
    LocaleSelectorContainer { args ->

        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }

        CupertinoSectionRow(
            modifier = modifier,
            isExpanded = isExpanded,
            dropdownMenuModifier = Modifier.heightIn(max = 300.dp),
            label = Res.string.locale_selector_dd_label.toActualString(),
            value = args.currentLocale.displayName.toActualString(),
            icon = Icons.Default.Language,
            isLast = isLast,
            onExpandedChange = {
                isExpanded = it
            }
        ) {
            StarterLocales.entries.forEachIndexed { index, locale ->
                CupertinoDropdownItem(
                    text = locale.displayName.toActualString(),
                    onClick = {
                        args.onLocaleSelected(locale)
                        isExpanded = false
                    },
                    isSelected = locale == args.currentLocale,
                    showDivider = index != StarterLocales.entries.lastIndex
                )
            }
        }


    }
}