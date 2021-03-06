package by.training.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "topic")
public class TopicModel extends Model {

    private static final long      serialVersionUID = 8849827068678797244L;

    @Column(name = "name", nullable = false, length = 255)
    private String                 name;

    @Column(name = "path", unique = true, nullable = false, length = 255)
    private String                 path;

    @Column(name = "description", nullable = false, length = 255)
    private String                 description;

    @Column(name = "access", nullable = false)
    private boolean                access;

    @ManyToOne(targetEntity = UserModel.class, cascade = {CascadeType.DETACH}, optional = false)
    private UserModel              creator;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "topic")
    private Set<NotificationModel> notifications;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "topic")
    private Set<PostModel>         posts;

    @JsonIgnore
    @ManyToMany(targetEntity = UserModel.class, fetch = FetchType.EAGER)
    @JoinTable(name = "topic_user", joinColumns = @JoinColumn(name = "topic_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false))
    private Set<UserModel>         users;

    public TopicModel() {
        super();
    }

    public TopicModel(final String name, final String path, final String description,
            final boolean access, final UserModel creator) {
        super();
        this.name = name;
        this.path = path;
        this.description = description;
        this.access = access;
        this.creator = creator;
        users = new HashSet<>();
        users.add(creator);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(final boolean access) {
        this.access = access;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(final UserModel creator) {
        this.creator = creator;
    }

    public Set<NotificationModel> getNotifications() {
        return notifications;
    }

    public void setNotifications(final Set<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    public Set<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(final Set<PostModel> posts) {
        this.posts = posts;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(final Set<UserModel> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Topic [id=" + super.getId() + ", name=" + name + ", path=" + path + ", description="
                + description + ", access=" + access + ", creator=" + creator + "]";
    }

}
