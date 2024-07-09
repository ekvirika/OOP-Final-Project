package Controllers.Managers;

import DAO.FriendsDAO;
import Models.FriendRequest;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.ArrayList;

public class FriendManager {
    public static final String ATTRIBUTE_NAME = "FriendManager";
    private FriendsDAO friendsDAO;
    private final SQLConnector sqlConnector;

    public FriendManager() {
        sqlConnector = new SQLConnector();
        friendsDAO = new FriendsDAO(sqlConnector.dataSource);
    }

    public void sendFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        FriendRequest friendRequest = new FriendRequest(usernameFrom, usernameTo);
        friendsDAO.addFriendship(friendRequest);
    }

    public void acceptFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        friendsDAO.updateFriendshipStatus(usernameFrom, usernameTo, true);
    }

    public void rejectFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        friendsDAO.updateFriendshipStatus(usernameFrom, usernameTo, false);
    }

    public FriendRequest viewFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        return friendsDAO.getFriendRequest(usernameFrom, usernameTo);
    }

    public ArrayList<FriendRequest> getAllFriendRequestsFrom(String usernameFrom) throws SQLException {
        return friendsDAO.getAllFriendRequestsFrom(usernameFrom);
    }

    public ArrayList<FriendRequest> getAllFriendRequestsTo(String usernameTo) throws SQLException {
        return friendsDAO.getAllFriendRequestsTo(usernameTo);
    }

    public int getFriendRequestID(String usernameFrom, String usernameTo) throws SQLException {
        return friendsDAO.getFriendRequestId(usernameFrom, usernameTo);
    }

    public ArrayList<String> getAcceptedFriendRequests(String usernameFrom) throws SQLException {
        return (ArrayList<String>) friendsDAO.getAcceptedFriends(usernameFrom);
    }
}