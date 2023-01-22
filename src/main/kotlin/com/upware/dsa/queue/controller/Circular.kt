package com.upware.dsa.cqueue.controller

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
@RequestMapping("/cqueue")
class Circular {

    private var front: Int = -1
    private var back: Int = -1
    private  var size: Int = 5

    private var cqueue: Array<String?> = arrayOfNulls(size)

    @GetMapping("/all")
    fun display(): Array<String?> {
        return cqueue
    }

    @GetMapping("front")
    fun front(): String? {
        return cqueue[front]
    }

    @PostMapping("/{value}")
    fun insert(@PathVariable("value") value: String): Serializable {
        if (front == (back + 1)%size) {
            return "Queue is full!"
        } else {
            back = (back + 1)%size
            cqueue[back] = value
        }
        if (front == -1) {
            front = 0
        }
        return cqueue
    }

    @DeleteMapping("/delete")
    fun delete(): Serializable {
        if (front == -1 && back == -2) {
            return "Queue is empty!"
        }
        else{
            var rtn: String = cqueue[front].toString()
            cqueue[front] = null
        }
        if (front == back) {
            front = -1
            back = -1
        }
        else{
            front = (front + 1)%size
        }
        return cqueue
    }
}