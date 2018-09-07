package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参加课程安排  startTime,endTime 看是否能参加所有课程
 */
public class kaluli_problem1 {
    public static void main(String... args) {
        System.out.println(new kaluli_problem1().dealwith());
    }
    public  boolean dealwith() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern compile = Pattern.compile("([0-9]+),([0-9]+)");
        Matcher matcher =
                compile.matcher(line);
        ArrayList<Node> nodes = new ArrayList<Node>();

        while (matcher.find()) {
            nodes.add(new Node(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
        }
        Collections.sort(nodes);


        for (int i=0;i<nodes.size()-1;i++) {

            if(nodes.get(i).y>nodes.get(i+1).x) return false;

        }
        return true;

    }
      class Node implements Comparable<Node>{
        int x;
        int y;
        public  Node(int x,int y) {
            this.x = x;
            this.y=y;
        }
        public int compareTo(Node o) {
            return this.x-o.x;
        }
    }


}


