package com.manager.todo.Application.dao;

import com.manager.todo.Application.Entities.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TodoDao {
    Logger logger= LoggerFactory.getLogger(TodoDao.class);

    // with jdbc 

private JdbcTemplate template;

    public TodoDao(@Autowired  JdbcTemplate template) {
        this.template = template;

      // create if table if not exits

        String createTable="create table IF NOT EXISTS todos (id int primary key, title varchar(20) not null,description varchar(1000) not null)";
        template.update(createTable);
        logger.info("todo table created {}", createTable);




    }
    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // create  todo in database
    public Todo createTodo(Todo todo){

        String insertQuery="insert into todos(id, title, description) value(?,?,?)";

        // for insert method--> jo result na de tb update ka likehenge
        int rowUpdate = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getDescription());

        logger.info("JDBC operation {}", rowUpdate);
        return todo;
    }




    //get single todo from database
    public Todo getSingleTodo(int id){
        String select="select * from todos where id=?";

        // for select method--> jab select krenege to different method use krte hai
        Map<String, Object> todoData = template.queryForMap(select, id);

        Todo todo= new Todo();
        todo.setId((int)todoData.get("id"));
        // yha typecasting ki gyi hai to todoData.get("id")--> yeh ek object hai aur todo int hai to isliye int mai typecasting ki gyo hai
        todo.setTitle((String)todoData.get("title") );
        todo.setDescription((String)todoData.get("description"));
        return todo;
    }



    // get all todo from database
    public List<Todo> getAllTodo(){
        String select = "Select * from todos";
        List<Map<String, Object>> maps = template.queryForList(select);
        // yeh one by one todo ko map krega
      List<Todo> todos=  maps.stream().map((map)->{
            Todo todo= new Todo();
            todo.setId((int)map.get("id"));
            // yha typecasting ki gyi hai to todoData.get("id")--> yeh ek object hai aur todo int hai to isliye int mai typecasting ki gyo hai
            todo.setTitle((String)map.get("title") );
            todo.setDescription((String)map.get("description"));
            return todo;
        }).collect(Collectors.toList());
            return todos;
    }



    // update todo
    public  Todo updateTodo(int id, Todo newTodo){
        String query="update todos set title=?, description=? where id=? ";
        int update = template.update(query, newTodo.getTitle(), newTodo.getDescription(),id);
        logger.info("updated value {}", update);
        newTodo.setId(id);
        return newTodo;
    }


    // delete todo
    public  void deleteTodo(int id){
        String query="delete from todos where id=?";
        int delete = template.update(query, id);
        logger.info("Deleted todo {}",delete);
    }



}
