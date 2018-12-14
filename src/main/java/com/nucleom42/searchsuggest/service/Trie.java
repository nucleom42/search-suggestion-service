package com.nucleom42.searchsuggest.entity;

import com.nucleom42.searchsuggest.service.TrieNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {

  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public TrieNode getRoot() {
    return root;
  }

  public void add(String word, int requests) {
    if (word.equals("") || requests == 0) return;
    TrieNode current = root;
    word = word.toLowerCase();
    int len = word.length();
    for (int i = 0; i < len; i++) {
      int index = word.charAt(i) - 'a';
      if (current.getChildren() == null || current.getChildren().get(index) == null) {
        TrieNode nodeToInsert = new TrieNode(word.charAt(i));
        if (i == len - 1) {
          nodeToInsert.setWord(word);
          nodeToInsert.setRequests(requests); // TODO define how to get numbber of requests
        }
        if (current.getChildren() == null) current.setChildren(new HashMap<>());
        current.getChildren().put(index, nodeToInsert);
        current = current.getChildren().get(index);
      } else {
        current = current.getChildren().get(index);
      }
    }
  }

  public List<TrieNode> findMostPopular(String prefix) {
    TrieNode node = find(prefix);
    if (node == null) return new ArrayList<>();
    ArrayList<TrieNode> res = getChildrenWords(node, new ArrayList<>());
    res.sort((a, b) -> b.getRequests() - a.getRequests());
    return res;
  }

  private ArrayList<TrieNode> getChildrenWords(TrieNode node, ArrayList<TrieNode> result) {
    node.getChildren()
        .forEach(
            (k, v) -> {
              if (v.getWord() == null) {
                getChildrenWords(v, result);
              } else {
                result.add(v);
                if (v.getChildren() != null) {
                  getChildrenWords(v, result);
                }
              }
            });
    return result;
  }

  public void traverse(TrieNode current) {
    // TODO
  }

  private TrieNode find(String searchWord) {
    TrieNode current = root;
    int len = searchWord.length();
    for (int i = 0; i < len; i++) {
      int index = searchWord.charAt(i) - 'a';
      if (current.getChildren().get(index) == null) {
        return null;
      } else {
        current = current.getChildren().get(index);
      }
    }
    return current;
  }
}
