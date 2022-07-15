## 반드시 해야하는 것들

```java
MattermostClient client;
client = MattermostClient.builder()
		    .url("https://meeting.ssafy.com")
			.logLevel(Level.INFO)
			.ignoreUnknownProperties()
			.build()
client.login(mmEmail, mmPassword);
```

### 채널 포스트 (메시지들)

```java
ApiResponse<PostList> list = client.getPostsForChannel("eoccnjbrai8a8q4kkuqt5zqyiw");
PostList postList = list.readEntity();
List<String> orderList = postList.getOrder(); // post 순서대로 나옴 


```

### 유저 찾기

```java
client.getUser(userId).readEntity()
```
