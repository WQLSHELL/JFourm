package com.model;

/**
 * 头条分类
 */
//@Entity
//@Table(name = "head_line_category")
public class HeadLineCategory {

    /*private Integer id;
    private String name;    // 分类名称
    private String description; // 分类描述
    private Timestamp submitTime;   // 分类创建时间

    private Set<HeadLine> headLines = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "submit_time", columnDefinition = "timestamp default current_timestamp")
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @OneToMany(targetEntity = HeadLine.class)
    @JoinColumn(name = "category_id")
    public Set<HeadLine> getHeadLines() {
        return headLines;
    }

    public void setHeadLines(Set<HeadLine> headLines) {
        this.headLines = headLines;
    }*/
}
