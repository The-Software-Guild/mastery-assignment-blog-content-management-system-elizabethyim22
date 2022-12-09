package com.wiley.BlogManagementSystem;

import com.wiley.BlogManagementSystem.dao.TagDao;
import com.wiley.BlogManagementSystem.model.Tag;
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
    public class TagDaoDBTest extends TestCase {

        @Autowired
        TagDao tagDao;

        public TagDaoDBTest(){}

        @Before
        public void setUp(){
            //deletes all entries of each table in the test database, so we can test properly
            List<Tag> tags = tagDao.getAllTags();
            for(Tag tag : tags){
                tagDao.deleteTagById(tag.getTag_id());
            }
        }

        @Test
        public void testTagAndGetTag(){
            //create a new Tag
            Tag tag = new Tag();
            tag.setTag_name("Winter");
            tagDao.add(tag);

            Tag fromDao = tagDao.getTagById(tag.getTag_id());
            assertEquals(tag.getTag_id(), fromDao.getTag_id());
        }

        @Test
        public void testGetAllTags(){
            Tag tag = new Tag();
            tag.setTag_name("Winter");
            tagDao.add(tag);

            Tag tag1 = new Tag();
            tag1.setTag_name("Sports");
            tagDao.add(tag1);

            List<Tag> tags = tagDao.getAllTags();

            assertEquals(2,tags.size());
            assertTrue(tags.contains(tag));
            assertTrue(tags.contains(tag1));
        }

        @Test
        public void testUpdateTag(){
            //create a new tag
            Tag tag = new Tag();
            tag.setTag_name("Winter");
            tagDao.add(tag);

            //update one part of it
            tag.setTag_name("Summer");
            tagDao.updateTag(tag);

            //get the new updated version
            Tag updated = tagDao.getTagById(tag.getTag_id());

            //assert that the tag got updated correctly and that they are equal now
            assertEquals(tag, updated);
        }

        @Test
        public void testDeleteAddressById(){
            //create a new tag
            Tag tag = new Tag();
            tag.setTag_name("Winter");
            tagDao.add(tag);

            //get the tag
            Tag fromDao = tagDao.getTagById(tag.getTag_id());

//assert that the tag we created is equal to the one retrieved
            assertEquals(tag.getTag_id(), fromDao.getTag_id());

            //delete tag
            tagDao.deleteTagById(tag.getTag_id());

            //get the new ID and assert that it is null
            fromDao = tagDao.getTagById(tag.getTag_id());
            assertNull(fromDao);
        }

    }

