package ua.taras.kushmyruk.model;

public class Message {
  private long id;
  private String topic;
  private String tag;
  private String text;
  private boolean isViewed;
  private User user;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isViewed() {
    return isViewed;
  }

  public void setViewed(boolean viewed) {
    isViewed = viewed;
  }
}
