package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JBlogApp;

import com.mycompany.myapp.domain.JPost;
import com.mycompany.myapp.repository.JPostRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JPostResource REST controller.
 *
 * @see JPostResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JBlogApp.class)
public class JPostResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    @Autowired
    private JPostRepository jPostRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJPostMockMvc;

    private JPost jPost;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JPostResource jPostResource = new JPostResource(jPostRepository);
        this.restJPostMockMvc = MockMvcBuilders.standaloneSetup(jPostResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JPost createEntity(EntityManager em) {
        JPost jPost = new JPost()
            .title(DEFAULT_TITLE)
            .body(DEFAULT_BODY)
            .author(DEFAULT_AUTHOR);
        return jPost;
    }

    @Before
    public void initTest() {
        jPost = createEntity(em);
    }

    @Test
    @Transactional
    public void createJPost() throws Exception {
        int databaseSizeBeforeCreate = jPostRepository.findAll().size();

        // Create the JPost
        restJPostMockMvc.perform(post("/api/j-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jPost)))
            .andExpect(status().isCreated());

        // Validate the JPost in the database
        List<JPost> jPostList = jPostRepository.findAll();
        assertThat(jPostList).hasSize(databaseSizeBeforeCreate + 1);
        JPost testJPost = jPostList.get(jPostList.size() - 1);
        assertThat(testJPost.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testJPost.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testJPost.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
    }

    @Test
    @Transactional
    public void createJPostWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jPostRepository.findAll().size();

        // Create the JPost with an existing ID
        jPost.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJPostMockMvc.perform(post("/api/j-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jPost)))
            .andExpect(status().isBadRequest());

        // Validate the JPost in the database
        List<JPost> jPostList = jPostRepository.findAll();
        assertThat(jPostList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = jPostRepository.findAll().size();
        // set the field null
        jPost.setTitle(null);

        // Create the JPost, which fails.

        restJPostMockMvc.perform(post("/api/j-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jPost)))
            .andExpect(status().isBadRequest());

        List<JPost> jPostList = jPostRepository.findAll();
        assertThat(jPostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBodyIsRequired() throws Exception {
        int databaseSizeBeforeTest = jPostRepository.findAll().size();
        // set the field null
        jPost.setBody(null);

        // Create the JPost, which fails.

        restJPostMockMvc.perform(post("/api/j-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jPost)))
            .andExpect(status().isBadRequest());

        List<JPost> jPostList = jPostRepository.findAll();
        assertThat(jPostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJPosts() throws Exception {
        // Initialize the database
        jPostRepository.saveAndFlush(jPost);

        // Get all the jPostList
        restJPostMockMvc.perform(get("/api/j-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jPost.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY.toString())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR.toString())));
    }

    @Test
    @Transactional
    public void getJPost() throws Exception {
        // Initialize the database
        jPostRepository.saveAndFlush(jPost);

        // Get the jPost
        restJPostMockMvc.perform(get("/api/j-posts/{id}", jPost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jPost.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY.toString()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJPost() throws Exception {
        // Get the jPost
        restJPostMockMvc.perform(get("/api/j-posts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJPost() throws Exception {
        // Initialize the database
        jPostRepository.saveAndFlush(jPost);
        int databaseSizeBeforeUpdate = jPostRepository.findAll().size();

        // Update the jPost
        JPost updatedJPost = jPostRepository.findOne(jPost.getId());
        // Disconnect from session so that the updates on updatedJPost are not directly saved in db
        em.detach(updatedJPost);
        updatedJPost
            .title(UPDATED_TITLE)
            .body(UPDATED_BODY)
            .author(UPDATED_AUTHOR);

        restJPostMockMvc.perform(put("/api/j-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedJPost)))
            .andExpect(status().isOk());

        // Validate the JPost in the database
        List<JPost> jPostList = jPostRepository.findAll();
        assertThat(jPostList).hasSize(databaseSizeBeforeUpdate);
        JPost testJPost = jPostList.get(jPostList.size() - 1);
        assertThat(testJPost.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testJPost.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testJPost.getAuthor()).isEqualTo(UPDATED_AUTHOR);
    }

    @Test
    @Transactional
    public void updateNonExistingJPost() throws Exception {
        int databaseSizeBeforeUpdate = jPostRepository.findAll().size();

        // Create the JPost

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJPostMockMvc.perform(put("/api/j-posts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jPost)))
            .andExpect(status().isCreated());

        // Validate the JPost in the database
        List<JPost> jPostList = jPostRepository.findAll();
        assertThat(jPostList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJPost() throws Exception {
        // Initialize the database
        jPostRepository.saveAndFlush(jPost);
        int databaseSizeBeforeDelete = jPostRepository.findAll().size();

        // Get the jPost
        restJPostMockMvc.perform(delete("/api/j-posts/{id}", jPost.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JPost> jPostList = jPostRepository.findAll();
        assertThat(jPostList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JPost.class);
        JPost jPost1 = new JPost();
        jPost1.setId(1L);
        JPost jPost2 = new JPost();
        jPost2.setId(jPost1.getId());
        assertThat(jPost1).isEqualTo(jPost2);
        jPost2.setId(2L);
        assertThat(jPost1).isNotEqualTo(jPost2);
        jPost1.setId(null);
        assertThat(jPost1).isNotEqualTo(jPost2);
    }
}
