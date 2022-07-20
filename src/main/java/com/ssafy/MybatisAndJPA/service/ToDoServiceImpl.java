package com.ssafy.MybatisAndJPA.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.ssafy.MybatisAndJPA.dto.ToDo;

import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.PostList;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Override
    public ToDo createToDo(Post post) {
        String message = post.getMessage();
        StringTokenizer st = new StringTokenizer(message, "\n");
        System.out.println(message);
        System.out.println("스트링토크나이저 시작");

        st.nextToken();
        String title = st.nextToken();
        String deadline = st.nextToken();
        int num = message.lastIndexOf(deadline);
        String content = message.substring(num);
        return new ToDo(title, deadline, content);
    }

    @Override
    public ArrayList<ToDo> createToDoList(PostList postList) {
        ArrayList<ToDo> rst = new ArrayList<ToDo>();

        Map<String, Post> map = postList.getPosts();
        List<String> orderList = postList.getOrder();

        for (int i = 0; i < orderList.size(); i++) {
            Post post = map.get(orderList.get(i));
            if (post.getMessage().length() > 7 && post.getMessage().substring(0, 6).equals("# 공지사항")) {
                rst.add(createToDo(post));
            }
        }

        return rst;
    }

    @Override
    public String createNotice(ToDo todo) {

        StringBuilder sb = new StringBuilder();

        sb.append("# 공지사항\n")
                .append("# " + todo.getTitle() + "\n")
                .append("# " + todo.getDeadline() + "\n")
                .append(todo.getContent());
        
        return sb.toString();

    }

}
