package org.com.game.service;

import java.util.ArrayList;
import java.util.List;

import org.com.game.dao.UserProfileDao;
import org.com.game.model.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService{
	private UserProfileDao userProfileDao;

	public void setUserProfileDao (UserProfileDao userProfileDao){
		this.userProfileDao = userProfileDao;
	}
	
	@Transactional
	public void addPlayer(UserProfile userProfile) {
		// TODO Auto-generated method stub
		this.userProfileDao.addPlayer(userProfile);
	}
	@Transactional
	public void updatePlayer(UserProfile userProfile) {
		// TODO Auto-generated method stub
		this.userProfileDao.updatePlayer(userProfile);
	}
	@Transactional
	public void removePlayer(String userId) {
		// TODO Auto-generated method stub
		this.userProfileDao.removePlayer(userId);
	}
	@Transactional
	public List<UserProfile> getUserList() {
		// TODO Auto-generated method stub
		return this.userProfileDao.getUserList();
	}
	@Transactional
	public UserProfile getUserById(String userId) {
		// TODO Auto-generated method stub
		return this.userProfileDao.getUserById(userId);
	}

	public UserProfile getOpponent(String userId) {
		// TODO Auto-generated method stub
		List<UserProfile> activeUserList = userProfileDao.getActiveUserList();
		UserProfile user = userProfileDao.getUserById(userId);
		UserProfile opponent = null;
		List<UserProfile> eligibleOpponent = new ArrayList<UserProfile>();
		for (UserProfile userProfile : activeUserList) {
			if (user.getRank() == userProfile.getRank() && !org.apache.commons.lang3.StringUtils.equalsIgnoreCase(user.getUserId(), userProfile.getUserId())){
				eligibleOpponent.add(userProfile);
			}	
			int distance = 0;
			String str = null;
			for (UserProfile userProfile2 : eligibleOpponent){
				float cdistance = userProfile2.getWinProbability() - user.getWinProbability();
				if(cdistance < distance){
					str = userProfile2.getUserId();
					distance = (int) cdistance;
				}
			}
			opponent = userProfileDao.getUserById(str);
		}
		return opponent;
	}

}
