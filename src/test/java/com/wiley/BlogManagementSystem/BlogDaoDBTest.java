package com.wiley.BlogManagementSystem;

import com.wiley.BlogManagementSystem.dao.BlogDao;
import com.wiley.BlogManagementSystem.model.Blog;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class BlogDaoDBTest extends TestCase {

    @Autowired
    BlogDao blogDao;

    public BlogDaoDBTest() {
    }

    @Before
    public void setUp() {
        List<Blog> blogs = blogDao.getAllBlog();
        for (Blog blog : blogs) {
            blogDao.deleteBlogById(blog.getBlog_id());
        }
    }

    @Test
    public void testBlogAndGetBlog() {
        //create a new blog
        Blog blog = new Blog();
        blog.setBody("This is the body");
        //blog.setDate(" ");
        blog.setTitle("This is the title");
        blog.setIsApproved(true);
        blogDao.add(blog);
        Blog fromDao = blogDao.getBlogById(blog.getBlog_id());
        assertEquals(blog.getBlog_id(), fromDao.getBlog_id());
    }

    @Test
    public void testGetAllBlogs() {
        //create 2 blogs
        Blog blog = new Blog();
        blog.setBody("This is the body");
        //blog.setDate(" ");
        blog.setTitle("This is the title");
        blog.setIsApproved(true);
        blogDao.add(blog);

        Blog blog1 = new Blog();
        blog1.setBody("This is the body of the blog");
        //blog.setDate(" ");
        blog1.setTitle("title");
        blog1.setIsApproved(true);
        blogDao.add(blog1);

        List<Blog> blogs = blogDao.getAllBlog();

        assertEquals(2, blogs.size());
        assertTrue(blogs.contains(blog));
        assertTrue(blogs.contains(blog1));
    }

    @Test
    public void testUpdateBlog() {
        //create a new blog
        Blog blog = new Blog();
        blog.setBody("This is the body");
        //blog.setDate(" ");
        blog.setTitle("This is the title");
        blog.setIsApproved(true);
        blogDao.add(blog);
        //update one part
        blog.setTitle("Long title");
        blogDao.updateBlog(blog);

        //get the new update
        Blog updated = blogDao.getBlogById(blog.getBlog_id());

        //assert that the blog got updated
        assertEquals(blog, updated);
    }

    @Test
    public void testDeleteBlogById() {
        //create a new blog
        Blog blog = new Blog();
        blog.setBody("This is the body");
        //blog.setDate(" ");
        blog.setTitle("This is the title");
        blog.setIsApproved(true);
        blogDao.add(blog);
        //get the blog
        Blog fromDao = blogDao.getBlogById(blog.getBlog_id());

        //assert that the blog we created is equal to the one retrieved
        assertEquals(blog.getBlog_id(), fromDao.getBlog_id());

        //delete the blog
        blogDao.deleteBlogById(blog.getBlog_id());

        //get the new ID and assert that it is null
        fromDao = blogDao.getBlogById(blog.getBlog_id());
        assertNull(fromDao);
    }
}

