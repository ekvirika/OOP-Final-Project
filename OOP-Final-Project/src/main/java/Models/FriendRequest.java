package Models;

import DAO.FriendsDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class FriendRequest {
    private String FriendIdFrom;
    private String FriendIdTo;
    private boolean FriendToStatus;

    public FriendRequest(String friendIdFrom, String friendIdTo) throws SQLException {
        this.FriendIdFrom = friendIdFrom;
        this.FriendIdTo = friendIdTo;
        this.FriendToStatus = false;
    }

    public String getFriendIdFrom() {
        return FriendIdFrom;
    }

    public String getFriendIdTo() {
        return FriendIdTo;
    }

    public boolean isFriendToStatus() {
        return FriendToStatus;
    }

    public void setFriendToStatus(boolean friendToStatus, BasicDataSource dataSource) throws SQLException {
        FriendToStatus = friendToStatus;
        String status = friendToStatus ? "accepted" : "pending";
        updateFriendshipStatus(status, dataSource);
    }

    public void sendFriendRequest(BasicDataSource dataSource) throws SQLException {
        FriendsDAO friendsDAO = new FriendsDAO(dataSource);
        friendsDAO.addFriendship(this);
    }

    private void updateFriendshipStatus(String status, BasicDataSource dataSource) throws SQLException {
        FriendsDAO friendsDAO = new FriendsDAO(dataSource);
        friendsDAO.updateFriendshipStatus(FriendIdFrom, FriendIdTo, status);
    }
}
