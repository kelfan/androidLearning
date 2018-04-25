package com.kelfan.utillibrary;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListString implements List<String> {

    private List<String> strList;

    public ListString(String inStr) {
        this.strList = getTokenList(inStr);
    }

    public static ListString set(String inStr){
        return new ListString(inStr);
    }

    Boolean contain(String[] list, String item) {
        return Arrays.asList(list).contains(item);
    }

    public static String list2str(List<String> list, String delimiter){
        String out = "";
        for (String item: list){
            out += item + delimiter;
        }
        out = out.substring(0, out.length()-delimiter.length());
        return out;
    }

    public static List<String> getPatternList(String inStr, String patternIn) {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile(patternIn).matcher(inStr);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return allMatches;
    }

    public static List<String> getTokenList(String inStr) {
        return getPatternList(inStr, "[^(a-zA-Z0-9\\\\u4e00-\\\\u9fa5)]*[(a-zA-Z0-9\\\\u4e00-\\\\u9fa5_)]+[^(a-zA-Z0-9\\\\u4e00-\\\\u9fa5)]*");
    }

    @Override
    public String toString() {
        return list2str(strList, "");
    }

    @Override
    public int size() {
        return strList.size();
    }

    @Override
    public boolean isEmpty() {
        return strList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return strList.contains(o);
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return strList.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return strList.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        return strList.toArray(ts);
    }

    @Override
    public boolean add(String s) {
        return strList.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return strList.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return strList.containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends String> collection) {
        return strList.addAll(collection);
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends String> collection) {
        return strList.addAll(collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        return strList.removeAll(collection);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return strList.retainAll(collection);
    }

    @Override
    public void clear() {
        strList.clear();
    }

    @Override
    public String get(int i) {
        return strList.get(i);
    }

    @Override
    public String set(int i, String s) {
        return strList.set(i, s);
    }

    @Override
    public void add(int i, String s) {
        strList.add(i, s);
    }

    @Override
    public String remove(int i) {
        return strList.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return strList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return strList.lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<String> listIterator() {
        return strList.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<String> listIterator(int i) {
        return strList.listIterator(i);
    }

    @NonNull
    @Override
    public List<String> subList(int i, int i1) {
        return strList.subList(i, i1);
    }
}
