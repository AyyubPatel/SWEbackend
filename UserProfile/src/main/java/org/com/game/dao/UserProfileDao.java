package org.com.game.dao;

import java.util.List;

import org.com.game.model.UserProfile;

public interface UserProfileDao {
	public void addPlayer(UserProfile userProfile);
	public void updatePlayer(UserProfile userProfile);
	public void removePlayer(String userId);
	public List<UserProfile> getUserList();
	public UserProfile getUserById(String userId);
	public UserProfile getOpponent(String userId);
	public List<UserProfile> getActiveUserList();
}
