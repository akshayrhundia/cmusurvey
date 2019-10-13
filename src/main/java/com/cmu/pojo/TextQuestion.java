package com.cmu.pojo;

import java.util.ArrayList;
import java.util.List;

public class TextQuestion {

  @Override
  public String toString() {
    return "Question [id=" + id + ", titletype=" + titletype + ", title=" + title + ", options="
           + options + "]";
  }

  public String getTitletype() {
    return titletype;
  }

  public void setTitletype(String titletype) {
    this.titletype = titletype;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  private Integer id;

  private String titletype;

  private String title;

  private List<String> options = new ArrayList<String>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}
