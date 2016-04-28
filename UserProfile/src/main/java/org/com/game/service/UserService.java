package org.com.game.service;

import java.util.List;

import org.com.game.model.UserProfile;

public interface UserService {
	public void addPlayer(UserProfile userProfile);
	public void updatePlayer(UserProfile userProfile);
	public void removePlayer(String userId);
	public List<UserProfile> getUserList();
	public UserProfile getUserById(String userId);
	public UserProfile getOpponent(String userId);
}
