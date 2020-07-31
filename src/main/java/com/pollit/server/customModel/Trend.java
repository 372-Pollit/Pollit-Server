package com.pollit.server.customModel;

import java.sql.Date;

public interface Trend {
    int getId ();
    String getExplanation();
    Date getPostDate();
    Date getDueDate();
    int getVoteCount();
    int getCommentCount();
}
