package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.JPost;

import com.mycompany.myapp.repository.JPostRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JPost.
 */
@RestController
@RequestMapping("/api")
public class JPostResource {

    private final Logger log = LoggerFactory.getLogger(JPostResource.class);

    private static final String ENTITY_NAME = "jPost";

    private final JPostRepository jPostRepository;

    public JPostResource(JPostRepository jPostRepository) {
        this.jPostRepository = jPostRepository;
    }

    /**
     * POST  /j-posts : Create a new jPost.
     *
     * @param jPost the jPost to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jPost, or with status 400 (Bad Request) if the jPost has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/j-posts")
    @Timed
    public ResponseEntity<JPost> createJPost(@Valid @RequestBody JPost jPost) throws URISyntaxException {
        log.debug("REST request to save JPost : {}", jPost);
        if (jPost.getId() != null) {
            throw new BadRequestAlertException("A new jPost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JPost result = jPostRepository.save(jPost);
        return ResponseEntity.created(new URI("/api/j-posts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /j-posts : Updates an existing jPost.
     *
     * @param jPost the jPost to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jPost,
     * or with status 400 (Bad Request) if the jPost is not valid,
     * or with status 500 (Internal Server Error) if the jPost couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/j-posts")
    @Timed
    public ResponseEntity<JPost> updateJPost(@Valid @RequestBody JPost jPost) throws URISyntaxException {
        log.debug("REST request to update JPost : {}", jPost);
        if (jPost.getId() == null) {
            return createJPost(jPost);
        }
        JPost result = jPostRepository.save(jPost);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jPost.getId().toString()))
            .body(result);
    }

    /**
     * GET  /j-posts : get all the jPosts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jPosts in body
     */
    @GetMapping("/j-posts")
    @Timed
    public List<JPost> getAllJPosts() {
        log.debug("REST request to get all JPosts");
        return jPostRepository.findAll();
        }

    /**
     * GET  /j-posts/:id : get the "id" jPost.
     *
     * @param id the id of the jPost to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jPost, or with status 404 (Not Found)
     */
    @GetMapping("/j-posts/{id}")
    @Timed
    public ResponseEntity<JPost> getJPost(@PathVariable Long id) {
        log.debug("REST request to get JPost : {}", id);
        JPost jPost = jPostRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(jPost));
    }

    /**
     * DELETE  /j-posts/:id : delete the "id" jPost.
     *
     * @param id the id of the jPost to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/j-posts/{id}")
    @Timed
    public ResponseEntity<Void> deleteJPost(@PathVariable Long id) {
        log.debug("REST request to delete JPost : {}", id);
        jPostRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
