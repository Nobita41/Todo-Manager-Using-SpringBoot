package com.manager.todo.Application;

import com.manager.todo.Application.Entities.Todo;
import com.manager.todo.Application.dao.TodoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {
Logger logger= LoggerFactory.getLogger(TodoApplication.class);
	@Autowired
	private TodoDao todoDao;
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}


	// Commandline method
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Started");
		// create Todo
		Todo todo= new Todo();
		todo.setId(456);
		todo.setTitle("SQL development");
		todo.setDescription("Currently I am learning SQl database ");

		todoDao.createTodo(todo);
// get todo
//		List<Todo> allTodo = todoDao.getAllTodo(123);
//		logger.info("Todo select information {}", allTodo);
// update todo
//		Todo todo = todoDao.getSingleTodo(123);
		todo.setId(123);
		todo.setTitle("learnig spring boot");
		todo.setDescription("learning jdbc");
		todoDao.updateTodo(123, todo);

		// delete todo
		todoDao.deleteTodo(456);

	}


}
