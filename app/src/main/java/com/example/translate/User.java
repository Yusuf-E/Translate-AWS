package com.example.translate;
    public class User {

        public   String email;
        public   String password;
        public String username;
        public int score;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public User(String email, String username, String password, int score) {
            this.username = username;
            this.email=email;
            this.password=password;
            this.score=score;
        }
    }


