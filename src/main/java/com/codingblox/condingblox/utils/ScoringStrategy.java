package com.codingblox.condingblox.utils;

import com.codingblox.condingblox.model.Contest;

public interface ScoringStrategy {
    void score(Contest contest);
}
