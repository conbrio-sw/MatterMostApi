package com.ssafy.MybatisAndJPA.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bis5.mattermost.client4.ApiResponse;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.Channel;
import net.bis5.mattermost.model.ChannelList;
import net.bis5.mattermost.model.ChannelMembers;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.PostList;
import net.bis5.mattermost.model.PostType;
import net.bis5.mattermost.model.TeamList;
import net.bis5.mattermost.model.User;

@RestController
public class MMController {

	/* for eclipse development code */
	@Value("${app.mm.email}")
	String mmEmail;
//	@Value("${app.fileupload.uploadPath2}")
//	String uploadPath2;
	/*
	 * 업로드 후 upload 폴더 refresh 하거나 preferences / workspace - refresh... 2개 option
	 * check
	 */
	@Value("${app.mm.password}")
	String mmPassword;

	private MattermostClient client;
	@JsonIgnore
	ApiResponse<PostList> list;
	// 목록
	//Map<String, String>
	@GetMapping(value = "/login")
	public Map<String, String> login() {
		String Email = mmEmail;
		String password = mmPassword;
		Map<String, String> map = new HashMap<>();
		MattermostClient client;
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();	
		String name = client.login(Email, password).getUsername();
		if (name != null) {
			map.put("login", "SUCCESS");
			map.put("email", Email);
			map.put("password", password);
		} else
			map.put("login", "FAIL");
		return map;
		

	}

	@GetMapping(value = "/test")
	public ResponseEntity<PostList> test() {
		System.out.println(1);
		// Create client instance
		MattermostClient client;
		// case 1. use constructor - log disable and prohibit unknown properties
		// client = new MattermostClient("https://meeting.ssafy.com");

		System.out.println(2);
		// case 2. use builder
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();
		System.out.println("clinet : " + client.toString());
		// Login by id + password
		client.login(mmEmail, mmPassword);
		// Login by Personal Access Token
		System.out.println("뭘까요 getAllTeams" + client.getAllTeams().readEntity().toString());
		System.out.println("뭘까요 getChannelMembers" + client.getChannelMembers("qhmimsx573fnugb3yrjtkpokao").toString());
		client.getChannel("qhmimsx573fnugb3yrjtkpokao");
		client.getPostsForChannel("eoccnjbrai8a8q4kkuqt5zqyiw");
		long since = System.currentTimeMillis() - 3600000;
		String query = String.format("?since=%d", since);
		System.out.println("since : " + query);

		list = client.getPostsSince("eoccnjbrai8a8q4kkuqt5zqyiw", since);
		
		return new ResponseEntity<>(list.readEntity(), HttpStatus.OK);
	}

	@GetMapping(value = "/test2")
	public ResponseEntity<TeamList> test2() {
		System.out.println(1);
		// Create client instance
		MattermostClient client;
		// case 1. use constructor - log disable and prohibit unknown properties
		// client = new MattermostClient("https://meeting.ssafy.com");

		System.out.println(2);
		// case 2. use builder
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();
		System.out.println("clinet : " + client.toString());
		// Login by id + password
		client.login(mmEmail, mmPassword);

		return new ResponseEntity<>(client.getAllTeams().readEntity(), HttpStatus.OK);
	}

	@GetMapping(value = "/test3")
	public ResponseEntity<User> test3() {
		System.out.println(1);
		// Create client instance
		MattermostClient client;
		// case 1. use constructor - log disable and prohibit unknown properties
		// client = new MattermostClient("https://meeting.ssafy.com");

		System.out.println(2);
		// case 2. use builder
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();
		System.out.println("clinet : " + client.toString());
		// Login by id + password
		client.login(mmEmail, mmPassword);
		return new ResponseEntity<>(client.getMe().readEntity(), HttpStatus.OK);
	}

	@GetMapping(value = "/getMsg")
	public ResponseEntity<PostList> getMsg() {

		MattermostClient client;

		// case 2. use builder
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();

		client.login(mmEmail, mmPassword);

		long since = System.currentTimeMillis() - 377200000;

		// list = client.getPostsSince("eoccnjbrai8a8q4kkuqt5zqyiw", since);
		list = client.getPostsForChannel("eoccnjbrai8a8q4kkuqt5zqyiw");
		Map<String, Post> map = new HashMap<>();

		PostList postList = list.readEntity();

		map = postList.getPosts();
		List<String> orderList = postList.getOrder();
		System.out.println("orderList : " + orderList.toString());

		ArrayList<String> arr = new ArrayList<>();

		for (int i = 0; i < orderList.size(); i++) {

			String userId = map.get(orderList.get(i)).getUserId();
			String userName = client.getUser(userId).readEntity().getNickname();
			arr.add(userName + " : " + map.get(orderList.get(i)).getMessage());
		}

//		for(String str : map.keySet()) {
//			System.out.println("=============================");
//			System.out.println(str + " : " + map.get(str));
//			//map.get(str).getMessage();
//		}

		for (int i = arr.size() - 1; i > -1; i--) {
			System.out.println(arr.get(i));
			System.out.println("=============================");
		}

		return new ResponseEntity<>(postList, HttpStatus.OK);
	}

	@GetMapping(value = "/getChannelMember")
	public ResponseEntity<ChannelMembers> getChannelMember() {
		MattermostClient client;
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();

		client.login(mmEmail, mmPassword);

		long since = System.currentTimeMillis() - 3600000;

		list = client.getPostsSince("eoccnjbrai8a8q4kkuqt5zqyiw", since);

		ApiResponse<ChannelMembers> channelMembers = client.getChannelMembers("eoccnjbrai8a8q4kkuqt5zqyiw");

		ChannelMembers CMRE = channelMembers.readEntity();

		System.out.println(client.getUser(CMRE.get(0).getUserId()).readEntity().getUsername());
		return new ResponseEntity<>(CMRE, HttpStatus.OK);
	}

	@GetMapping(value = "/teams")
	public ResponseEntity<TeamList> teams() {
		MattermostClient client;
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();

		client.login(mmEmail, mmPassword);

		TeamList rst = client.getTeamsForUser(client.getMe().readEntity().getId()).readEntity();
		return new ResponseEntity<>(rst, HttpStatus.OK);
	}

	@GetMapping(value = "/channels")
	public ResponseEntity<ArrayList<Channel>> chennels() {
		MattermostClient client;
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();

		client.login(mmEmail, mmPassword);

		TeamList teamList = client.getTeamsForUser(client.getMe().readEntity().getId()).readEntity();
		ArrayList<Channel> rst = new ArrayList<>();

		for (int i = 0; i < teamList.size(); i++) {
			ChannelList channelList = (client.getChannelsForTeamForUser(teamList.get(i).getId(), client.getMe().readEntity().getId())
					.readEntity());
			for(int j = 0; j < channelList.size(); j++) {
				if(channelList.get(j).getTeamId().equals("")) continue;
				rst.add(channelList.get(j));
			}
		}

		return new ResponseEntity<>(rst, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/me")
	public ResponseEntity<User> me() {
		MattermostClient client;
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();

		client.login(mmEmail, mmPassword);
		User rst = client.getMe().readEntity();
		Post post = new Post("d7dad80e-9e79-4558-a37a-628aab3c2101", "될까?");
		return new ResponseEntity<>(rst, HttpStatus.OK);
	}
	
	@GetMapping(value = "/post")
	public ResponseEntity<Post> post() {
		MattermostClient client;
		client = MattermostClient.builder().url("https://meeting.ssafy.com").logLevel(Level.INFO)
				.ignoreUnknownProperties().build();

		client.login(mmEmail, mmPassword); 
		User rst = client.getMe().readEntity();
		Post post = new Post("qhmimsx573fnugb3yrjtkpokao", " :dongclap:");
		client.createPost(post);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
}
