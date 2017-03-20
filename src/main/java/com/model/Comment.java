package com.model;


import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 评论类
 */
@Entity
@Table(name = "comment")
public class Comment {

    private Integer id;
    private String content; // 评论内容
    private Integer likeNum;    // 点赞数
    private Timestamp submitTime;   //  评论时间
    private User user;  // 评论者
    private Question question;  //  评论的问题
   /*private Comment comment;    // 父级评论*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "content", columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "like_num", columnDefinition = "int default 0")
    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    @Column(name = "submit_time", columnDefinition = "timestamp default current_timestamp")
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    /*@Transient
    @ManyToOne(targetEntity = Question.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id", nullable = false)
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }*/
}
