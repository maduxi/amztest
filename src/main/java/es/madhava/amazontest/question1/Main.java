/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.madhava.amazontest.question1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author madhava
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(getRankedCourses("user"));
    }

    public static List<String> getRankedCourses(String user) {

        Set<String> friendsNetwork = getFriendsNetwork(user);
        Map<String, Integer> courses = getSuitableCourses(user, friendsNetwork);
        List<String> result = getSortedCourses(courses);
        return result;
    }

    private static List<String> getSortedCourses(Map<String, Integer> courses) {
        List<String> result = new ArrayList<>();
        ValueComparator bvc = new ValueComparator(courses);
        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(bvc);
        sortedMap.putAll(courses);
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    private static Map<String, Integer> getSuitableCourses(String user, Set<String> friendsNetwork) {
        Map<String, Integer> courses = new HashMap<String, Integer>();

        List<String> attendedCourses = getAttendedCoursesForUser(user);

        for (String friend : friendsNetwork) {
            List<String> attendedCoursesForFriend = getAttendedCoursesForUser(friend);
            for (String course : attendedCoursesForFriend) {
                if (!attendedCourses.contains(course)) {
                    if (courses.containsKey(course)) {
                        courses.put(course, courses.get(course) + 1);
                    } else {
                        courses.put(course, 1);
                    }
                }
            }
        }
        return courses;
    }

    private static Set<String> getFriendsNetwork(String user) {
        Set<String> friendsNetwork = new HashSet<String>();
        List<String> directFriendsForUser = getDirectFriendsForUser(user);
        friendsNetwork.addAll(directFriendsForUser);
        for (String friend : directFriendsForUser) {
            friendsNetwork.addAll(getDirectFriendsForUser(friend));
        }
        return friendsNetwork;
    }

    public static List<String> getAttendedCoursesForUser(String user) {
        List<String> result = new ArrayList<>();
        if (user.equalsIgnoreCase("a")) {
            result.add("c1");
            result.add("c2");
            result.add("c3");
        } else if (user.equalsIgnoreCase("b")) {
            result.add("c1");
            result.add("c4");
            result.add("c5");
        } else if (user.equalsIgnoreCase("c")) {
            result.add("c1");
            result.add("c4");
            result.add("c6");
        } else if (user.equalsIgnoreCase("d")) {
            result.add("c1");
            result.add("c7");
            result.add("c8");
        } else if (user.equalsIgnoreCase("user")) {
            result.add("c11");
            result.add("c7");
            result.add("c8");
        }

        return result;
    }

    public static List<String> getDirectFriendsForUser(String user) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        arrayList.add("u");
        return arrayList;
    }

}
