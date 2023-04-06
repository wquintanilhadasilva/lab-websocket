package com.example.websocket.server;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Content {
	private List<Activity> activity = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private String content = "";
}
