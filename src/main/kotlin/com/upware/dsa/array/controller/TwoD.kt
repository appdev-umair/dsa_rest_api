package com.upware.dsa.array.controller

import org.springframework.web.bind.annotation.*
import java.io.File
import java.io.Serializable

@RestController
@CrossOrigin
@RequestMapping("/2d")
class TwoD {
    var row: Int = 5;
    var col: Int = 3;
    val file = File("D:2dArray.csv")
    var array: Array<Array<String?>> = Array(row) { arrayOfNulls(col) }

    @GetMapping("/getAll")
    fun getAll(): Array<Array<String?>> {
        getData()
        return array
    }

    @GetMapping("/{index}")
    fun getById(@PathVariable("index") index: Int): String {
        getData()
        var rec: String = ""
        for (i in 0 until row){
            if(i == index-1){
                for (j in 0 until col){
                    rec += array[i][j] + ","
                }
            }
        }
        if(rec == ""){
            return "No Record!"
        }
        return rec
    }

    @GetMapping("/search/{value}")
    fun search(@PathVariable("value") value: String): String {
        getData()
        var records = ""
        for (i in array.indices){
            for (j in array[i].indices){
                if(value == array[i][j]){
                    records += getById(i + 1)+"\n"
                }
            }
        }
        if(records != ""){
            return records
        }
        return "No Record!"
    }

    @PostMapping("/post")
    fun insert(@RequestBody value: String): Serializable {
        getData()
        for (i in array.indices){
            if(array[i][0] == null || array[i][0] == "null") {
                var k = 0
                for (j in array[i].indices) {
                    if (j == 0){
                        array[i][j] = (i+1).toString()
                    }
                    else {
                        var str = ""
                        while (k < value.length && value[k] != ',') {
                            if (value[k] != ' ')
                                str += value[k]
                            k++
                        }
                        array[i][j] = str
                        k++
                    }
                }
                insertToFile()
                getData()
                return array
            }
        }
        return "Not Inserted!"
    }

    private fun insertToFile() {
        file.bufferedWriter().flush()
        for (i in 0 until row){
            file.appendText(getById(i+1)+"\n")
        }
    }

    private fun getData() {
        var i: Int = 0
        file.forEachLine {
            if (i > row-1 ) return@forEachLine
            var j = 0
            var str = ""
            var c = 0
            while (j < it.length){
                str = ""
                while (j < it.length && it[j] != ','){
                    if(it[j] != ' ')
                    str += it[j]
                    j++
                }
                array[i][c] = str
                j++
                c++
            }
            i++
        }
    }

}