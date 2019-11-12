package pl.coderslab.app.comment;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import pl.coderslab.app.picture.PictureRepositoryCustom;
import pl.coderslab.app.user.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PictureRepositoryCustom pictureRepositoryCustom;

    @Override
    public void save(Comment comment, Long picId, String login) {

        comment.setPicture(pictureRepositoryCustom.findById(picId));
        comment.setUser(userRepository.findByLogin(login).get());
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> readAllByPictureId(Long picId) {
        List<Comment> comments = commentRepository.readAllByPictureIdOrderByCreatedDesc(picId);
        comments.stream().forEach(c->Hibernate.initialize(c.getUser()));
        return comments;
    }
}
