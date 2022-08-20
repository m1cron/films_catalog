package ru.micron.dto

import javax.validation.ValidationException

data class PageableData(
    var totalCount: Long,
    var pageNum: Long,
    var pageSize: Long,
    var currentPageSize: Long
) {

    var totalPages: Long

    init {
        validateMinValue(pageNum, 1)
        validateMinValue(pageSize, 1)
        validateMinValue(totalCount, 0)
        totalPages =
            if (totalCount % pageSize == 0L)
                totalCount / pageSize
            else
                totalCount / pageSize + 1
    }

    private fun validateMinValue(value: Long, minValue: Int) {
        if (value < minValue) {
            throw ValidationException(
                String.format(
                    "The current value [%s] is less than min possible value [%s] ", value,
                    minValue
                )
            )
        }
    }
}