package Models;

import DAO.FriendsDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class FriendRequest {
    private int FriendIdFrom;
    private int FriendIdTo;
    private boolean FriendToStatus;

    public FriendRequest(int friendIdFrom, int friendIdTo) throws SQLException {
        this.FriendIdFrom = friendIdFrom;
        this.FriendIdTo = friendIdTo;
        this.FriendToStatus = false;
        sendFriendRequest();
    }

    public int getFriendIdFrom() {
        return FriendIdFrom;
    }

    public int getFriendIdTo() {
        return FriendIdTo;
    }

    public boolean isFriendToStatus() {
        return FriendToStatus;
    }

    public void setFriendToStatus(boolean friendToStatus) {
        FriendToStatus = friendToStatus;
    }

    public void sendFriendRequest() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        FriendsDAO friendsDAO = new FriendsDAO(dataSource);
        friendsDAO.addFriendship(this);
    }

    public void updateFriendshipStatus(String status) throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        FriendsDAO friendsDAO = new FriendsDAO(dataSource);
        friendsDAO.updateFriendshipStatus(FriendIdFrom, FriendIdTo, status);
    }
}
