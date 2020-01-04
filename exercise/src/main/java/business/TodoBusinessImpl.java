package business;

import data.TodoService;

import java.util.List;
import java.util.stream.Collectors;

public class TodoBusinessImpl {
    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {
        return this.todoService.retrieveTodos(user)
                .stream()
                .filter(todo -> todo.contains("Spring"))
                .collect(Collectors.toList());
    }

    public void deleteTodosNotRelatedToSpring(String user) {
        List<String> allTodos = todoService.retrieveTodos(user);
        for (String todo : allTodos) {
            if (!todo.contains("Spring")) {
                todoService.deleteTodo(todo);
            }
        }
    }
}