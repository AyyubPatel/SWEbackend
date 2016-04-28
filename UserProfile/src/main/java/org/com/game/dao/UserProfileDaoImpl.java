package org.com.game.dao;

import java.util.List;

import org.com.game.model.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileDaoImpl implements UserProfileDao{

    private static final Logger logger = LoggerFactory.getLogger(UserProfileDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    public void addPlayer(UserProfile userProfile) {
		// TODO Auto-generated method stub
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(userProfile);
        logger.info("Player saved successfully, Player Details="+userProfile);
	}

	public void updatePlayer(UserProfile userProfile) {
		// TODO Auto-generated method stub
        Session session = this.sessionFactory.getCurrentSession();
        session.update(userProfile);
        logger.info("Player saved successfully, Player Details="+userProfile);
	}

	public void removePlayer(String userId) {
		// TODO Auto-generated method stub
	    Session session = this.sessionFactory.getCurrentSession();      
	    UserProfile userProfile = (UserProfile) session.load(UserProfile.class, userId);
		if(null != userProfile){
            session.delete(userProfile);
        }
        logger.info("Player deleted successfully, Player details="+userProfile);
	}

	@SuppressWarnings("unchecked")
	public List<UserProfile> getUserList() {
		// TODO Auto-generated method stub
        Session session = this.sessionFactory.getCurrentSession();
        List<UserProfile> userList = session.createQuery("from USER_PROFILE").list();
        for(UserProfile ul : userList){
            logger.info("User List::"+ul);
        }
        return userList;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> getActiveUserList() {
		// TODO Auto-generated method stub
        Session session = this.sessionFactory.getCurrentSession();
        List<UserProfile> userList = session.createQuery("from USER_PROFILE where status = 'Y'").list();
        for(UserProfile ul : userList){
            logger.info("User List::"+ul);
        }
        return userList;
	}

	
	public UserProfile getUserById(String userId) {
		// TODO Auto-generated method stub
	     Session session = this.sessionFactory.getCurrentSession();      
	     UserProfile userProfile = (UserProfile) session.load(UserProfile.class, userId);
	        logger.info("Player loaded successfully, Player details="+userId);
	        return userProfile;
	   	}

	public UserProfile getOpponent(String userId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();      
	     UserProfile userProfile = (UserProfile) session.load(UserProfile.class, userId);
	        logger.info("Player loaded successfully, Player details="+userId);
	        return userProfile;
	}

}
