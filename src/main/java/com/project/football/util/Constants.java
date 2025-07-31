package com.project.football.util;

public class Constants {
    private Constants() { throw new AssertionError("Cannot instantiate constants class"); }

    public static final class Player {
        public static final String PLAYER_NOT_FOUND = "There is no player with such id: ";

        private Player() {
            throw new AssertionError("Cannot instantiate constants class"); }
    }

    public static final class Team {
        public static final String TEAM_NOT_FOUND = "There is no team with such id: ";

        private Team() {
            throw new AssertionError("Cannot instantiate constants class"); }
    }

    public static final class Transfer {
        public static final String NOT_ENOUGH_MONEY = "The team could not afford this purchase.";
        public static final String TEAM_ALREADY_HAVE_PLAYER = "The team has already got player with id: ";

        private Transfer() {
            throw new AssertionError("Cannot instantiate constants class"); }
    }
}
