package com.sun.kmpstartertemplaterefined.feature_auth_domain.di

import com.sun.kmpstartertemplaterefined.feature_auth_domain.logics.RegisterUserLogic
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDomainModule = module {
    singleOf(::RegisterUserLogic)
}