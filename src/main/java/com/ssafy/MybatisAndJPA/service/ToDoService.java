package com.ssafy.MybatisAndJPA.service;

import java.util.ArrayList;

import com.ssafy.MybatisAndJPA.dto.ToDo;

import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.PostList;

public interface ToDoService {
    ToDo createToDo(Post post);

    ArrayList<ToDo> createToDoList(PostList postList);

    String createNotice(ToDo todo);
}
