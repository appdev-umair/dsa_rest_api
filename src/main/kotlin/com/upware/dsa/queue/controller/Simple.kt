package com.upware.dsa.queue.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable

@RestController
@CrossOrigin
@RequestMapping("/queue")
class Simple {

    private var front: Int = -1
    private var back: Int = -1
    private  var size: Int = 5
    private var queue: Array<String?> = arrayOfNulls(size)

    @GetMapping("/all")
    fun display(): Array<String?> {
        return queue
    }

    @GetMapping("front")
    fun front(): String? {
        return queue[front]
    }

    @PostMapping("/{value}")
    fun insert(@PathVariable("value") value: String): Serializable {
        if (back < 4) {
            back++
            queue[back] = value
        } else {
            return "Queue is full!"
        }
        if (front == -1) {
            front = 0
        }
        return queue

    }

    @DeleteMapping("/delete")
    fun delete(): Serializable {
        if (front == -1) {
            return "Queue is empty!"
        }
        var rtn: String = queue[front].toString()
        queue[front] = null
        var i = 0
        queue.forEach {
            if(it != null){
                queue[i] = it
                i++
            }
        }
        for(i in back .. 4){
            queue[i] = null
        }
        front = 0
        back--
        if (front > back) {
            front = -1
            back = -1
        }
        return queue
    }
}