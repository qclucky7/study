package com.qingchen.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GithubRepoPageProcessor
 * @description:
 * @author: WangChen
 * @create: 2020-05-28 18:56
 **/
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    private static List<Blog> list = new ArrayList<>();

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        if (page.getUrl().regex("https://www.jianshu.com/u/").match()){
            page.addTargetRequests(page.getHtml().xpath("//*[@class='note-list']/li/div")
                    .xpath("[@class='title']")
                    .links()
                    .all()
            );
        } else {

            System.out.println(page.getHtml());

            Blog blog = new Blog();
            blog.setAuthor(page.getHtml().xpath("[@class='_22gUMi']/text()").get());
            blog.setBlogName(page.getHtml().xpath("h1[@class='_1RuRku']/text()").get());


            list.add(blog);

        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://www.jianshu.com/p/467719df7418")
                //.addUrl("https://www.jianshu.com/u/acabf768ee68")
                .thread(1).run();

        System.out.println(list.toString());
    }


    static class Blog{

        private String author;
        private String blogName;
        private String readCount;
        private String worldCount;
        private String commentCount;
        private String createTime;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getBlogName() {
            return blogName;
        }

        public void setBlogName(String blogName) {
            this.blogName = blogName;
        }

        public String getReadCount() {
            return readCount;
        }

        public void setReadCount(String readCount) {
            this.readCount = readCount;
        }

        public String getWorldCount() {
            return worldCount;
        }

        public void setWorldCount(String worldCount) {
            this.worldCount = worldCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "Blog{" +
                    "author='" + author + '\'' +
                    ", blogName='" + blogName + '\'' +
                    ", readCount='" + readCount + '\'' +
                    ", worldCount='" + worldCount + '\'' +
                    ", commentCount='" + commentCount + '\'' +
                    ", createTime='" + createTime + '\'' +
                    '}';
        }
    }
}
