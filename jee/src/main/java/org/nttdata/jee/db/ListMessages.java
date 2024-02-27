package org.nttdata.jee.db;

import java.sql.ResultSet;

public class ListMessages extends DatabaseManager {
    private ResultSet messages;
    public ListMessages(int personId) {
       messages = runExecuteQuery("SELECT * FROM message WHERE person_id = " + personId);
    }

    public ResultSet getMessages() {
        return messages;
    }
}
