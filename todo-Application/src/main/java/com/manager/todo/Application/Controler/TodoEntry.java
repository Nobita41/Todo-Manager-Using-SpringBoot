package com.manager.todo.Application.Controler;

import com.manager.todo.Application.Entities.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Entry")
public class TodoEntry {

    // without database
    private Map<Long, Todo> todoEntries= new HashMap<>();

    // create todo
    @PostMapping
    public String createTodo(@RequestBody Todo create){

        todoEntries.put(create.getId(),create);
        return "Todo created";
    }


    // get all todo
    @GetMapping
    public List<Todo> getAllTodo(){
        return new ArrayList<>(todoEntries.values());
    }


    //get a single todo
    @GetMapping("id/{myid}")
    public Todo getSingleTodo(@PathVariable Long myid){
       return todoEntries.get(myid);
    }


    //update Todo
    @PutMapping("id/{myid}")
    public Todo UpdateTodo(@PathVariable Long myid, @RequestBody Todo update){
        return todoEntries.put(myid,update);
    }


    // delete Todo
    @DeleteMapping("id/{myid}")
    public Todo DeleteTodo(@PathVariable Long myid){
        return todoEntries.remove(myid);
    }
}
