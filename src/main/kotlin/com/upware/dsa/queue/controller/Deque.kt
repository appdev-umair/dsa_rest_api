package com.upware.dsa.queue.controller

import org.springframework.web.bind.annotation.*
import java.io.Serializable

@RestController
@CrossOrigin
@RequestMapping("/deque")
class Deque {

    private var front: Int = -1
    private var back: Int = -1
    private  var size: Int = 5

    private var deque: Array<String?> = arrayOfNulls(size)

    @GetMapping("/all")
    fun display(): Array<String?> {
        return deque
    }

    @GetMapping("front")
    fun front(): String? {
        return deque[front]
    }

    @PostMapping("/ifront/{value}")
    fun insert_front(@PathVariable("value") value: String): Serializable {
        if (front == 0 && back == size-1) {
            return "Queue is full!"
        } 
        if (front == -1 && back == -1) {
            back =  0
            front = 0
            deque[front] = value
        } 
        
        else if(front > back){
            front--
            deque[front] = value
        }
        else{
            return "No space from front side."
        }
        return deque
    }

    @PostMapping("/iback/{value}")
    fun insert_back(@PathVariable("value") value: String): Serializable {
        if (front == 0 && back == size-1) {
            return "Queue is full!"
        }
        if (front == -1 && back == -1) {
            back =  0
            front = 0
            deque[front] = value
        }

        else if(front < size-1){
            back++
            deque[back] = value
        }
        else{
            return "No space from back side."
        }
        return deque
    }
    
    @DeleteMapping("/dfront")
    fun delete_front(): Serializable {
        if (front == -1 && back == -2) {
            return "Queue is empty!"
        }
        else{
            deque[front] = null
        }
        if (front == back) {
            front = -1
            back = -1
        }
        else if(front == size-1){
            front == -1
        }
        else{
            front++
        }
        return deque
    }    
    @DeleteMapping("/dback")
    fun delete_back(): Serializable {
        if (front == -1 && back == -2) {
            return "Queue is empty!"
        }
        else{
            deque[front] = null
        }
        if (front == back) {
            front = -1
            back = -1
        }
        else{
            back--
        }
        return deque
    }

}