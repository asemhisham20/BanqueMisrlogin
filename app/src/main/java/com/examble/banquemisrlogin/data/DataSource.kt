package com.examble.banquemisrlogin.data

import com.examble.banquemisrlogin.R
import com.examble.banquemisrlogin.model.ServiceItem

class DataSource {
    fun getServicesData()= listOf(
        ServiceItem(R.drawable.our_products,"Our Products"),
        ServiceItem(R.drawable.exchange_rate,"Exchange Rate"),
        ServiceItem(R.drawable.security_tips,"Security Tips"),
        ServiceItem(R.drawable.nearest_branch_or_atm,"Nearst Branch or atm"),
        )

}