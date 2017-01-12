package com.Mealpack;

/**
 * Created by yash on 11/1/17.
 */
public class MealSession {
    private long id;
    private int user1, user2, user3;

    public MealSession(long id, int user1, int user2, int user3) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public int getUser3() {
        return user3;
    }

    public void setUser3(int user3) {
        this.user3 = user3;
    }
}

