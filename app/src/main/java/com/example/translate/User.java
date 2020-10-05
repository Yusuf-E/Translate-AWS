package com.example.translate;
    public class User {

        public   String email;
        public   String password;
        public String username;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String email, String username,String password) {
            this.username = username;
            this.email=email;
            this.password=password;

        }

    }


