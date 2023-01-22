package com.upware.dsa.stack.controller

import org.springframework.web.bind.annotation.*
import java.io.File
import java.io.Serializable

@RestController
@CrossOrigin
@RequestMapping("/stack")
class Stack {

    private var top: Int = -1
    private var size: Int = 5
    private var stack: Array<String?> = arrayOfNulls(size)

    @GetMapping("/all")
    fun display(): Array<String?> {
        return stack
    }

    @GetMapping("/top")
    fun top(): String? {
        if(top < 0){
            return "Stack is empty!"
        }
        if(top > size-1){
            return "Stack is full!"
        }
        return stack[top]
    }

    @PostMapping("/{value}")
    fun push(@PathVariable value: String): Serializable {
        if (top < size - 1) {
            top++
            stack[top] = value
            return stack
        }
        return "Stack is full!"
    }
   @DeleteMapping("/pop")
    fun pop(): Serializable {
        if (top >= 0) {
            var rtn: String = stack[top].toString()
            stack[top] = null
            top--
            return stack
        }
        return "Stack is empty!"
    }

}