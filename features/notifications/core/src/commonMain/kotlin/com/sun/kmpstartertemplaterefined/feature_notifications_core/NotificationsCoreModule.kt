package com.sun.kmpstartertemplaterefined.feature_notifications_core

import com.tweener.alarmee.AlarmeeService
import com.tweener.alarmee.createAlarmeeService
import org.koin.dsl.module

val notificationsCoreModule = module {
    single {
        val alarmeeService = createAlarmeeService()
        alarmeeService.initialize(
            platformConfiguration = createAlarmeePlatformConfiguration(),
        )
        alarmeeService
    }
    single {
        val alarmeeService = get<AlarmeeService>()
        alarmeeService.local
    }
}