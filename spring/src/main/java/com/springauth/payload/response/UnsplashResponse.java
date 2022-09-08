package com.springauth.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
public class UnsplashResponse {
    public int total;
    public int total_pages;
    public ArrayList<Result> results;

    @Data
    @AllArgsConstructor
    public static class Result {
        public String id;
        public Date created_at;
        public Date updated_at;
        public Date promoted_at;
        public int width;
        public int height;
        public String color;
        public String blur_hash;
        public String description;
        public String alt_description;
        public Urls urls;
        public Links links;
        public ArrayList<Object> categories;
        public int likes;
        public boolean liked_by_user;
        public ArrayList<Object> current_user_collections;
        public Object sponsorship;
        public TopicSubmissions topic_submissions;
        public User user;
        public ArrayList<Tag> tags;


        @Data
        @AllArgsConstructor
        public static class Urls {
            public String raw;
            public String full;
            public String regular;
            public String small;
            public String thumb;
            public String small_s3;
        }

        @Data
        @AllArgsConstructor
        public static class Links {
            public String self;
            public String html;
            public String download;
            public String download_location;
            public String photos;
            public String likes;
            public String portfolio;
            public String following;
            public String followers;
        }

        @Data
        @AllArgsConstructor
        public static class TopicSubmissions {
            public Friends friends;

            @Data
            @AllArgsConstructor
            public static class Friends {
                public String status;
                public Date approved_on;
            }
        }

        @Data
        @AllArgsConstructor
        public static class User {
            public String id;
            public Date updated_at;
            public String username;
            public String name;
            public String first_name;
            public String last_name;
            public String twitter_username;
            public String portfolio_url;
            public String bio;
            public String location;
            public Links links;
            public ProfileImage profile_image;
            public String instagram_username;
            public int total_collections;
            public int total_likes;
            public int total_photos;
            public boolean accepted_tos;
            public boolean for_hire;
            public Social social;

            @Data
            @AllArgsConstructor
            public static class Links {
                public String self;
                public String html;
                public String download;
                public String download_location;
                public String photos;
                public String likes;
                public String portfolio;
                public String following;
                public String followers;
            }


            @Data
            @AllArgsConstructor
            public static class ProfileImage {
                public String small;
                public String medium;
                public String large;
            }

            @Data
            @AllArgsConstructor
            public static class Social {
                public String instagram_username;
                public String portfolio_url;
                public String twitter_username;
                public Object paypal_email;
            }
        }

        @Data
        @AllArgsConstructor
        public static class Tag {
            public String type;
            public String title;
        }
    }

}