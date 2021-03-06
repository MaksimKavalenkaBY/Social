package by.training.controller.rest;

import static by.training.constants.CountConstants.MAX_POST_LEVEL;
import static by.training.constants.MessageConstants.LEVEL_ERROR;
import static by.training.constants.MessageConstants.OPERATION_PERMISSIONS_ERROR;
import static by.training.constants.MessageConstants.PAGE_PERMISSIONS_ERROR;
import static by.training.constants.MessageConstants.VALIDATION_ERROR;
import static by.training.constants.UrlConstants.ID_KEY;
import static by.training.constants.UrlConstants.PAGE_KEY;
import static by.training.constants.UrlConstants.PATH_KEY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.training.bean.ErrorMessage;
import by.training.bean.PostWithCommentsCount;
import by.training.database.dao.PostDAO;
import by.training.database.dao.TopicDAO;
import by.training.model.PostModel;
import by.training.model.TopicModel;
import by.training.model.UserModel;
import by.training.utility.Validator;

@RestController
@RequestMapping("/posts")
public class PostRestController extends by.training.controller.rest.RestController {

    private PostDAO  postDAO;
    private TopicDAO topicDAO;

    public PostRestController(final PostDAO postDAO, final TopicDAO topicDAO) {
        this.postDAO = postDAO;
        this.topicDAO = topicDAO;
    }

    @RequestMapping(value = "/create/{text}" + PATH_KEY + "/{parentPostId}"
            + JSON_EXT, method = RequestMethod.POST)
    public ResponseEntity<Object> createPost(@PathVariable("text") final String text,
            @PathVariable("path") final String path,
            @PathVariable("parentPostId") final long parentPostId) {
        if (!Validator.allNotNull(text, parentPostId)) {
            return new ResponseEntity<Object>(new ErrorMessage(VALIDATION_ERROR),
                    HttpStatus.CONFLICT);
        }

        TopicModel topic = topicDAO.getTopicByPath(path);
        UserModel user = getLoggedUser();

        if (!topic.getUsers().contains(user)) {
            return new ResponseEntity<Object>(new ErrorMessage(OPERATION_PERMISSIONS_ERROR),
                    HttpStatus.CONFLICT);
        }

        if ((parentPostId > 0) && (postDAO.getPostLevel(parentPostId) >= MAX_POST_LEVEL)) {
            return new ResponseEntity<Object>(new ErrorMessage(LEVEL_ERROR), HttpStatus.CONFLICT);
        }

        PostModel parentPost = null;
        if (parentPostId > 0) {
            parentPost = postDAO.getPostById(parentPostId);
        }
        PostModel post = postDAO.createPost(text, user, topic, parentPost);
        return new ResponseEntity<Object>(post, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}/{text}" + JSON_EXT, method = RequestMethod.POST)
    public ResponseEntity<Object> updatePost(@PathVariable("id") final long id,
            @PathVariable("text") final String text) {
        if (!Validator.allNotNull(id, text)) {
            return new ResponseEntity<Object>(new ErrorMessage(VALIDATION_ERROR),
                    HttpStatus.CONFLICT);
        }

        if (postDAO.getPostById(id).getCreator().getId() != getLoggedUser().getId()) {
            return new ResponseEntity<Object>(new ErrorMessage(OPERATION_PERMISSIONS_ERROR),
                    HttpStatus.CONFLICT);
        }

        PostModel post = postDAO.updatePost(id, text);
        return new ResponseEntity<Object>(post, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete/{id}" + PATH_KEY + JSON_EXT, method = RequestMethod.POST)
    public ResponseEntity<Object> deleteNotification(@PathVariable("id") final long id,
            @PathVariable("path") final String path) {
        if (!Validator.allNotNull(id)) {
            return new ResponseEntity<Object>(new ErrorMessage(VALIDATION_ERROR),
                    HttpStatus.CONFLICT);
        }

        if (topicDAO.getTopicByPath(path).getCreator().getId() != getLoggedUser().getId()) {
            return new ResponseEntity<Object>(new ErrorMessage(OPERATION_PERMISSIONS_ERROR),
                    HttpStatus.CONFLICT);
        }

        postDAO.deletePost(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = PATH_KEY + ID_KEY + JSON_EXT, method = RequestMethod.GET)
    public ResponseEntity<Object> getPostById(@PathVariable("path") final String path,
            @PathVariable("id") final long id) {
        TopicModel topic = topicDAO.getTopicByPath(path);
        if (!topic.isAccess() && !topic.getUsers().contains(getLoggedUser())) {
            return new ResponseEntity<Object>(new ErrorMessage(PAGE_PERMISSIONS_ERROR),
                    HttpStatus.CONFLICT);
        }

        PostModel post = postDAO.getPostById(id);
        return checkEntity(post);
    }

    @RequestMapping(value = "/topic" + PATH_KEY + "/" + PAGE_KEY
            + JSON_EXT, method = RequestMethod.GET)
    public ResponseEntity<List<PostWithCommentsCount>> getTopicPosts(
            @PathVariable("path") final String path, @PathVariable("page") final int page) {
        List<PostModel> posts = postDAO.getTopicPosts(path, page);

        if (posts == null) {
            return new ResponseEntity<List<PostWithCommentsCount>>(HttpStatus.NO_CONTENT);
        }

        List<PostWithCommentsCount> postWithCommentsCounts = new ArrayList<>(posts.size());
        for (PostModel post : posts) {
            long id = post.getId();
            String text = post.getText();
            Date date = post.getDate();
            UserModel creator = post.getCreator();
            TopicModel topic = post.getTopic();
            long commentsCount = postDAO.getPostCommentsCount(id);

            postWithCommentsCounts
                    .add(new PostWithCommentsCount(id, text, date, creator, topic, commentsCount));
        }
        return new ResponseEntity<List<PostWithCommentsCount>>(postWithCommentsCounts,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/feed" + PAGE_KEY + JSON_EXT, method = RequestMethod.GET)
    public ResponseEntity<List<PostWithCommentsCount>> getFeedPosts(
            @PathVariable("page") final int page) {
        List<PostModel> posts = postDAO.getFeedPosts(getLoggedUser().getId(), page);

        if (posts == null) {
            return new ResponseEntity<List<PostWithCommentsCount>>(HttpStatus.NO_CONTENT);
        }

        List<PostWithCommentsCount> postWithCommentsCounts = new ArrayList<>(posts.size());
        for (PostModel post : posts) {
            long id = post.getId();
            String text = post.getText();
            Date date = post.getDate();
            UserModel creator = post.getCreator();
            TopicModel topic = post.getTopic();
            long commentsCount = postDAO.getPostCommentsCount(id);

            postWithCommentsCounts
                    .add(new PostWithCommentsCount(id, text, date, creator, topic, commentsCount));
        }
        return new ResponseEntity<List<PostWithCommentsCount>>(postWithCommentsCounts,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/topic" + PATH_KEY + "/page_count"
            + JSON_EXT, method = RequestMethod.GET)
    public ResponseEntity<Long> getTopicPostsPageCount(@PathVariable("path") final String path) {
        long pageCount = postDAO.getTopicPostsPageCount(path);
        return new ResponseEntity<Long>(pageCount, HttpStatus.OK);
    }

    @RequestMapping(value = "/feed/page_count" + JSON_EXT, method = RequestMethod.GET)
    public ResponseEntity<Long> getFeedPostsPageCount() {
        long pageCount = postDAO.getFeedPostsPageCount(getLoggedUser().getId());
        return new ResponseEntity<Long>(pageCount, HttpStatus.OK);
    }

}
