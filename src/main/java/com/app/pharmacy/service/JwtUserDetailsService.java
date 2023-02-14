package com.app.pharmacy.service;

import com.app.pharmacy.entity.Comment;
import com.app.pharmacy.repository.ICommentRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
  private ICommentRepository iCommentRepository;

  @Autowired
  public JwtUserDetailsService(ICommentRepository iCommentRepository) {
    this.iCommentRepository = iCommentRepository;
  }
  @Autowired
  private PasswordEncoder bcryptEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Comment comment=iCommentRepository.findByPersonName(username);
    if (comment.getPersonName().equals(username)) {
      return new User(comment.getPersonName(), bcryptEncoder.encode(comment.getPassword()),
          new ArrayList<>());
    } else {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }
}
