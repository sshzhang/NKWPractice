package com.leetcode.domain;

import java.util.*;

public class Twitter355 {

    // key 表示对应的用户id, value表示此用户关注的用户, 当然包括自己这个数据
    public HashMap<Integer, List<Integer>> followedIds;
    // value表示推特数据
    public HashMap<Integer, List<TwitterInfo>> twitterInfos;

    public static int count = 0;

    class TwitterInfo implements Comparable<TwitterInfo> {
        int userId;
        int tweetId;
        long currTimeMillions;

        public TwitterInfo() {
        }

        public TwitterInfo(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.currTimeMillions = count++;
        }

        @Override
        public int compareTo(TwitterInfo o) {
            return this.currTimeMillions - o.currTimeMillions > 0 ? -1 : 1;
        }
    }


    public Twitter355() {
        followedIds = new HashMap<>();
        twitterInfos = new HashMap<>();
    }

    /**
     * 创建一个推文
     *
     * @param userId
     * @param tweetId
     */
    public void postTweet(int userId, int tweetId) {

        TwitterInfo twitterInfo = new TwitterInfo(userId, tweetId);
        if (twitterInfos.containsKey(userId)) {
            twitterInfos.get(userId).add(twitterInfo);
        } else {
            List<TwitterInfo> twitterInfoList = new ArrayList<>();
            twitterInfoList.add(twitterInfo);
            twitterInfos.put(userId, twitterInfoList);
        }
    }

    /**
     * 某个用户最近的十条推文， 包括朋友圈和自己
     *
     * @param userId
     * @return
     */
    public List<Integer> getNewsFeed(int userId) {

        List<Integer> allFollowIds = followedIds.get(userId);
        List<TwitterInfo> allTwtterInfos = new ArrayList<>();
        List<TwitterInfo> twitterInfoList = twitterInfos.get(userId);
        if(twitterInfoList!=null)
        allTwtterInfos.addAll(twitterInfoList);
        if(allFollowIds!=null)
        for (Integer id : allFollowIds) {
            if (twitterInfos.get(id) != null)
            allTwtterInfos.addAll(twitterInfos.get(id));
        }
        Collections.sort(allTwtterInfos);
        List<Integer> twtterIds = new ArrayList<>();
        int len = allTwtterInfos.size();
        len = len > 10 ? 10 : len;
        for (int i = 0; i < len; i++) {
            twtterIds.add(allTwtterInfos.get(i).tweetId);
        }
        return twtterIds;
    }


    public void follow(int followerId, int followeeId) {
        if (followeeId==followerId)return;
        List<Integer> fValue = this.followedIds.get(followerId);
        if (fValue != null) {
            if(fValue.contains(followeeId)) return;
             fValue.add(followeeId);
        }else{
            List<Integer> initValue = new ArrayList<>();
            initValue.add(followeeId);
            this.followedIds.put(followerId, initValue);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        List<Integer> allFollowers = this.followedIds.get(followerId);
        if (allFollowers != null && allFollowers.contains(followeeId)) {
            allFollowers.remove((Integer) followeeId);
        }
    }

    public static void main(String... args) {

        Twitter355 twitter = new Twitter355();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);
// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//        System.out.println(twitter.getNewsFeed(1));

// 用户1关注了用户2.
        twitter.follow(1, 2);

// 用户2发送了一个新推文 (推文id = 6).
//        twitter.postTweet(2, 6);

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
//        System.out.println(twitter.getNewsFeed(1));

// 用户1取消关注了用户2.
        twitter.follow(2, 1);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.

        System.out.println(twitter.getNewsFeed(2));

    }


}
