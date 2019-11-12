package pl.coderslab.app.comment;

import java.util.List;

public interface CommentService {

    void save(Comment comment, Long picId, String login);
    List<Comment> readAllByPictureId(Long id);
}
