package com.example.myapplication.utils

fun updateCellValues(params: DashBoardValues) {
    with(params) {
        cell1Text.value = transformGeneFormat("${selectedParam1.value}/${selectedParam3.value}")
        cell2Text.value = transformGeneFormat("${selectedParam2.value}/${selectedParam3.value}")
        cell3Text.value = transformGeneFormat("${selectedParam4.value}/${selectedParam1.value}")
        cell4Text.value = transformGeneFormat("${selectedParam4.value}/${selectedParam2.value}")
        val textCells = listOf(cell1Text.value, cell2Text.value, cell3Text.value, cell4Text.value)
        val aaCount = textCells.sumOf { countSubstring(it.toString(), "a/a") }
        countSub.value = (4 - aaCount) / 4.0 * 100
        countNeSub.value = 100.0 - countSub.value
    }
}

fun transformGeneFormat(gene: String): String {
    return if (gene == "a/A") "A/a" else gene
}

fun countSubstring(str: String, substr: String): Int {
    var count = 0
    var idx = 0
    while (str.indexOf(substr, idx).also { idx = it } != -1) {
        idx += substr.length
        count++
    }
    return count
}
