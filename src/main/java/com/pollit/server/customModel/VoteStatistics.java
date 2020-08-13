package com.pollit.server.customModel;

public interface VoteStatistics {
    int getOptionId();

    int getVoteCount();

    int getTotalVote();

    int getPercentage();
}
