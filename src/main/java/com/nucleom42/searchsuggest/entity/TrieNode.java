package com.nucleom42.searchsuggest.service;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
  private String word;
  private Integer requests;
  private char chr;
  private Map<Integer, TrieNode> children;

  public TrieNode() {
    children = new HashMap<>();
  }

  public TrieNode(char chr) {
    this.chr = chr;
  }

  public int getRequests() {
    return requests;
  }

  public void setRequests(Integer requests) {
    this.requests = requests;
  }

  public Map<Integer, TrieNode> getChildren() {
    return children;
  }

  public void setChildren(Map<Integer, TrieNode> children) {
    this.children = children;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public void setRequests(int requests) {
    this.requests = requests;
  }

  public char getChr() {
    return chr;
  }

  public void setChr(char chr) {
    this.chr = chr;
  }
}
