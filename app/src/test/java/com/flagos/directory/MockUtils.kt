package com.flagos.directory

import com.flagos.directory.domain.model.EmployeeItem

object MockUtils {

    fun mockEmployee(): EmployeeItem = EmployeeItem(
        "0d8fcc12-4d0c-425c-8355-390b312b909c",
        "Justine Mason",
        "5553280123",
        "jmason.demo@squareup.com",
        "Engineer on the Point of Sale team.",
        "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
        "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
        "Point of Sale",
        "FULL_TIME"
    )

    fun mockEmployeeDirectoryList() = listOf(mockEmployee())
}