package com.pollit.server.customModel;

import java.sql.Date;

public interface Trend {
    int getId ();
    String getTitle();
    String getUsername();
    String getExplanation();
    Date getPostDate();
    Date getDueDate();
    int getVoteCount();
    int getCommentCount();
}
