package com.brainstation.arturitos.dtos;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Comment")
@Table(name = "comment")
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "comment_text")
    private String commentText;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private UserDTO user;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private ProductDTO product;

    @Column(name = "created_date")
    private LocalDate cratedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public LocalDate getCratedDate() {
        return cratedDate;
    }

    public void setCratedDate(LocalDate cratedDate) {
        this.cratedDate = cratedDate;
    }
}
